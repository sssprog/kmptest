package com.kmptest.auth

import androidx.compose.runtime.Composable

interface AuthFeature {
    val authScreen: @Composable () -> Unit
}