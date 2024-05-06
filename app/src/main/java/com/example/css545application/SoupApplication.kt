package com.example.css545application

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.css545application.data.PreferencesRepository

private const val SOUP_PREFERENCE_NAME = "user_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = SOUP_PREFERENCE_NAME
)
class SoupApplication: Application() {
    lateinit var preferencesRepository: PreferencesRepository

    override fun onCreate() {
        super.onCreate()
        preferencesRepository = PreferencesRepository(dataStore)
    }
}