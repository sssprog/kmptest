package com.kmptest.auth.data

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.kmptest.data.dataStoreFilePath
import kotlinx.coroutines.flow.first
import okio.Path.Companion.toPath

class AuthPreferences {
    private val preferences = PreferenceDataStoreFactory.createWithPath(
            produceFile = { dataStoreFilePath("auth.preferences_pb").toPath() }
    )
    private val tokenKey = stringPreferencesKey("token")

    suspend fun getToken(): String = preferences.data.first()[tokenKey] ?: ""

    suspend fun setToken(token: String) {
        preferences.edit { settings ->
            settings[tokenKey] = token
        }
    }
}