package com.kmptest.splash

import com.kmptest.auth.AuthRepository

internal class IsAuthorizedUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): Boolean = authRepository.isAuthorized()
}