package com.example.css545application.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class PreferencesRepository private constructor(
    private val dataStore: DataStore<Preferences>
) {
    val storedImageURI: Flow<String?> = dataStore.data.map {
        it[IMAGE_URI_KEY]
    }.distinctUntilChanged()

    val storedUserName: Flow<String?> = dataStore.data.map {
        it[USER_NAME_KEY]
    }.distinctUntilChanged()

    val storedSoupCount: Flow<Int?> = dataStore.data.map {
        it[SOUP_COUNT_KEY] ?.toInt()
    }.distinctUntilChanged()

    val storedMaxSoupCount: Flow<Int?> = dataStore.data.map {
        it[MAX_SOUP_COUNT_KEY] ?.toInt()
    }.distinctUntilChanged()

    suspend fun setStoredImageURI(uri: String) {
        dataStore.edit {preferences ->
            preferences[IMAGE_URI_KEY] = uri
        }
    }

    suspend fun setStoredUserName(userName: String) {
        dataStore.edit {preferences ->
            preferences[IMAGE_URI_KEY] = userName
        }
    }

    suspend fun setStoredSoupCount(count: Int) {
        dataStore.edit { preferences ->
            preferences[SOUP_COUNT_KEY] = count.toString()
        }
    }

    suspend fun setStoredMaxSoupCount(count: Int) {
        dataStore.edit { preferences ->
            preferences[MAX_SOUP_COUNT_KEY] = count.toString()
        }
    }

    companion object {
        private val IMAGE_URI_KEY = stringPreferencesKey("image_uri")
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
        private val SOUP_COUNT_KEY = stringPreferencesKey("soup_count")
        private val MAX_SOUP_COUNT_KEY = stringPreferencesKey("max_soup_count")
        private var INSTANCE: PreferencesRepository? = null

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
}