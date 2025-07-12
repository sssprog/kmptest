package com.kmptest.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.koin.compose.scope.KoinScope
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.qualifier.named
import org.koin.mp.KoinPlatformTools
import org.koin.mp.generateId

object HomeScreen

@Composable
fun HomeScreen() {
    KoinScope(remember { KoinPlatformTools.generateId() }, named<HomeScreen>()) {
        val viewModel = koinViewModel<HomeViewModel>()
        Box(
                modifier = Modifier.safeDrawingPadding().fillMaxSize()
        ) {
            Text("Home screen")
        }
    }
}