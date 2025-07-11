package com.kmptest.auth.domain

import com.kmptest.auth.data.AuthRepositoryImpl

internal class LoginUseCase(private val authRepositoryImpl: AuthRepositoryImpl) {
    suspend operator fun invoke(login: String, password: String) {
        authRepositoryImpl.login(login, password)
    }
}