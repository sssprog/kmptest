package com.kmptest.auth.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.kmptest.core.ui.ErrorState
import kmptest.core.resources.generated.resources.Res
import kmptest.core.resources.generated.resources.login
import kmptest.core.resources.generated.resources.password
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.scope.KoinScope
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.qualifier.named
import org.koin.mp.KoinPlatformTools
import org.koin.mp.generateId

internal object AuthScreen

@OptIn(KoinExperimentalAPI::class)
@Composable
internal fun AuthScreen(onLoginSuccess: () -> Unit) {
    KoinScope(remember { KoinPlatformTools.generateId() }, named<AuthScreen>()) {
        val viewModel = koinViewModel<AuthViewModel>()
        val snackbarHostState = remember { SnackbarHostState() }
        Scaffold(
                snackbarHost = {
                    SnackbarHost(hostState = snackbarHostState)
                },
                modifier = Modifier
                        .safeDrawingPadding()
                        .fillMaxSize()
        ) {
            Content(viewModel)
        }
        ActionHandler(viewModel, snackbarHostState, onLoginSuccess)
    }
}

@Composable
private fun Content(viewModel: AuthViewModel) {
    Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
    ) {
        OutlinedTextField(
                value = viewModel.loginState.collectAsStateWithLifecycle().value,
                onValueChange = viewModel::onLoginChanged,
                singleLine = true,
                label = { Text(stringResource(Res.string.login)) },
                modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
                value = viewModel.passwordState.collectAsStateWithLifecycle().value,
                onValueChange = viewModel::onPasswordChanged,
                singleLine = true,
                label = { Text(stringResource(Res.string.password)) },
                visualTransformation = remember { PasswordVisualTransformation() },
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
        )
        Button(
                enabled = viewModel.buttonEnabled.value,
                onClick = viewModel::login,
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
        ) {
            Text(stringResource(Res.string.login))
        }
    }
}

@Composable
private fun ActionHandler(
        viewModel: AuthViewModel,
        snackbarHostState: SnackbarHostState,
        onLoginSuccess: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        scope.launch {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                var lastSnackbar: Job? = null
                viewModel.actionState.collect { state ->
                    viewModel.clearAction()
                    if (state is AuthViewAction.Error) {
                        lastSnackbar?.cancel()
                        lastSnackbar = scope.launch {
                            snackbarHostState.showSnackbar(message = state.error.description)
                        }
                    } else if (state is AuthViewAction.LoginSuccess) {
                        onLoginSuccess()
                    }
                }
            }
        }
    }
}