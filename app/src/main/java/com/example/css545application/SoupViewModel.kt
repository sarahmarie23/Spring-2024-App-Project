package com.example.css545application

import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SoupViewModel (private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _count = mutableIntStateOf(savedStateHandle["count"] ?: 0)
    val count: State<Int> = _count

    private val _maxCount = mutableIntStateOf(savedStateHandle["maxCount"] ?: 0)
    val maxCount: State<Int> = _maxCount

    private val _recentDate = mutableStateOf<String?>(savedStateHandle.get<String?>("recentDate"))
    val recentDate: State<String?> = _recentDate

    init {
        if (_count.value == 0) {
            _recentDate.value = "N/A - Start eating soup!"
        }
    }

    fun incrementCount() {
        _count.value++
        if (_count.value > _maxCount.value) {
            _maxCount.value = _count.value
        }
        updateRecentDate()
        saveState()
    }

    fun resetCount() {
        _count.value = 0
        saveState()
    }

    fun resetMaxCount() {
        _maxCount.value = 0
        resetCount()
    }

    private fun updateRecentDate() {
        val dateFormat = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault())
        _recentDate.value = dateFormat.format(Date())
    }

    private fun saveState() {
        savedStateHandle["count"] = _count.value
        savedStateHandle["maxCount"] = _maxCount.value
        savedStateHandle["recentDate"] = _recentDate.value
    }
}
