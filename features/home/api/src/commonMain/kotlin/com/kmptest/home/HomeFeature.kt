package com.kmptest.home

import androidx.compose.runtime.Composable

interface HomeFeature {
    val homeScreen: @Composable () -> Unit
}