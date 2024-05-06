package com.example.css545application.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.css545application.ui.SoupUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {

    val storedImageURI: Flow<String?> = dataStore.data.map {
        it[IMAGE_URI_KEY]
    }.distinctUntilChanged()

    val storedUserName: Flow<String?> = dataStore.data.map {
        it[USER_NAME_KEY]
    }.distinctUntilChanged()

    val storedSoupCount: Flow<Int> = dataStore.data.map { preferences ->
        preferences[SOUP_COUNT_KEY] ?: 0
    }

    val storedMaxSoupCount: Flow<Int?> = dataStore.data.map {
        it[MAX_SOUP_COUNT_KEY] ?.toInt()
    }.distinctUntilChanged()

    val storedRecentDate: Flow<Date?> = dataStore.data.map {
        val dateString = it[RECENT_DATE_KEY]
        dateString?.let {
            try {
                SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).parse(dateString)
            } catch (e: Exception) {
                null
            }
        }
    }.distinctUntilChanged()

    suspend fun setStoredImageURI(uri: String) {
        dataStore.edit { preferences ->
            preferences[IMAGE_URI_KEY] = uri
        }
    }

    suspend fun setStoredUserName(userName: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = userName
        }
    }

    suspend fun setStoredSoupCount(storedSoupCount: Int) {
        dataStore.edit { preferences ->
            preferences[SOUP_COUNT_KEY] = storedSoupCount
        }
    }

    suspend fun setStoredMaxSoupCount(count: Int) {
        dataStore.edit { preferences ->
            preferences[MAX_SOUP_COUNT_KEY] = count
        }
    }

    suspend fun setStoredRecentDate(date: Date) {
        val dateString = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(date)
        dataStore.edit { preferences ->
            preferences[RECENT_DATE_KEY] = dateString
        }
    }

    companion object {
        val IMAGE_URI_KEY = stringPreferencesKey("image_uri")
        val USER_NAME_KEY = stringPreferencesKey("user_name")
        val SOUP_COUNT_KEY = intPreferencesKey("soup_count")
        val MAX_SOUP_COUNT_KEY = intPreferencesKey("max_soup_count")
        val RECENT_DATE_KEY = stringPreferencesKey("recent_date")
        //private var INSTANCE: PreferencesRepository? = null

        /*
        fun getInstance(dataStore: DataStore<Preferences>): PreferencesRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: create(dataStore).also { INSTANCE = it }
            }
        }

        private fun create(dataStore: DataStore<Preferences>): PreferencesRepository {
            return PreferencesRepository(dataStore)
        }

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                val dataStore = PreferenceDataStoreFactory.create {
                    context.preferencesDataStoreFile("user_preferences")
                }

                INSTANCE = PreferencesRepository(dataStore)
            }
        }

        fun get(): PreferencesRepository {
            return INSTANCE ?: throw IllegalStateException(
                "PreferencesRepository must be initialized"
            )
        }
    }
    suspend fun getSoupUiState(): Flow<SoupUiState> {
        return dataStore.data.map { dataStore ->
            SoupUiState(
                countState = dataStore[SOUP_COUNT_KEY] ?: 0,
                maxCountState = dataStore[MAX_SOUP_COUNT_KEY] ?: 0,
                recentDateState = dataStore[RECENT_DATE_KEY]?.let { dateString ->
                    parseDate(dateString)
                }
            )
        }

         */
    }
}