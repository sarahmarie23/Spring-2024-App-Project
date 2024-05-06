package com.example.css545application.ui

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.css545application.SoupApplication
import com.example.css545application.data.PreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SoupViewModel (private val preferencesRepository: PreferencesRepository) : ViewModel() {

    val uiState: StateFlow<SoupUiState> =
        combine(
            preferencesRepository.storedSoupCount,
            preferencesRepository.storedMaxSoupCount,
            preferencesRepository.storedRecentDate
        ) { storedSoupCount, storedMaxSoupCount, storedRecentDate ->
            SoupUiState(
                countState = storedSoupCount,
                maxCountState = storedMaxSoupCount ?: 0,
                recentDateState = storedRecentDate
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SoupUiState()
        )

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SoupApplication)
                SoupViewModel(application.preferencesRepository)
            }
        }
    }

    fun incrementCount() {
        viewModelScope.launch {
            uiState.value.let { uiState ->
                val updatedCount = uiState.countState + 1
                val updatedMaxCount = if (updatedCount > uiState.maxCountState) updatedCount else uiState.maxCountState
                val updatedUiState = uiState.copy(countState = updatedCount, maxCountState = updatedMaxCount)
                preferencesRepository.setStoredSoupCount(updatedCount)
                preferencesRepository.setStoredMaxSoupCount(updatedMaxCount)
                uiState.recentDateState?.let { recentDate ->
                    preferencesRepository.setStoredRecentDate(recentDate)
                }
            }
        }
    }

    fun resetCount() {
        viewModelScope.launch {
            uiState.value.let { uiState ->
                val updatedUiState = uiState.copy(countState = 0)
                preferencesRepository.setStoredSoupCount(0)
            }
        }
    }

    fun resetMaxCount() {
        viewModelScope.launch {
            uiState.value.let { uiState ->
                val updatedUiState = uiState.copy(countState = 0, maxCountState = 0)
                preferencesRepository.setStoredSoupCount(0)
                preferencesRepository.setStoredMaxSoupCount(0)
            }
        }
    }

    fun saveState() {
        Log.e("saveState", "saving $uiState.countState")
        viewModelScope.launch {
            uiState.value.let { uiState ->
                preferencesRepository.setStoredSoupCount(uiState.countState)
                preferencesRepository.setStoredMaxSoupCount(uiState.maxCountState)
                uiState.recentDateState?.let { recentDate ->
                    preferencesRepository.setStoredRecentDate(recentDate)
                }
            }
        }
    }

    private fun parseDate(dateString: String): Date? {
        return if (dateString == "N/A - Start eating soup!") {
            null
        } else {
            val dateFormat = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault())
            return dateFormat.parse(dateString) ?: Date()
        }
    }

}

data class SoupUiState(
    var countState: Int = 0,
    var maxCountState: Int = 0,
    var recentDateState: Date? = null
)
