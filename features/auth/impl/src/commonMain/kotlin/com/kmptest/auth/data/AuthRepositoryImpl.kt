package com.kmptest.auth.data

import com.kmptest.auth.AuthRepository
import com.kmptest.data.ApiException

class AuthRepositoryImpl(
        private val preferences: AuthPreferences
) : AuthRepository {

    override suspend fun isAuthorized(): Boolean = preferences.getToken().isNotEmpty()

    suspend fun login(login: String, password: String) {
        if (login == "qwe" && password == "qwe") {
            preferences.setToken("1")
            return
        }
        throw ApiException("", "Wrong login or password", "Wrong login or password")
    }
}