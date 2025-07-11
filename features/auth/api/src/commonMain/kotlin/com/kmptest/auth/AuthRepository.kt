package com.kmptest.auth

interface AuthRepository {
    suspend fun isAuthorized(): Boolean
}