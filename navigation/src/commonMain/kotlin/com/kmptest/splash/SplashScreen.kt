package com.kmptest.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kmptest.core.resources.generated.resources.Res
import kmptest.core.resources.generated.resources.loading
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.scope.KoinScope
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.qualifier.named
import org.koin.mp.KoinPlatformTools
import org.koin.mp.generateId

object SplashScreen

@OptIn(KoinExperimentalAPI::class)
@Composable
fun SplashScreen(
        openApp: () -> Unit,
        openAuth: () -> Unit
) {
    KoinScope(remember { KoinPlatformTools.generateId() }, named<SplashScreen>()) {
        val viewModel = koinViewModel<SplashViewModel>()
        Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                        .safeDrawingPadding()
                        .fillMaxSize()
                        .padding(16.dp)
        ) {
            Text("Splash screen...")
        }

        val lifecycleOwner = LocalLifecycleOwner.current
        LaunchedEffect(Unit) {
            launch {
                lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.action.collect { action ->
                        when {
                            action == SplashAction.OPEN_APP -> openApp()
                            action == SplashAction.OPEN_AUTH -> openAuth()
                        }
                    }
                }
            }
        }
    }
}