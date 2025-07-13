package com.kmptest.core.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kmptest.core.resources.generated.resources.Res
import kmptest.core.resources.generated.resources.empty_data_message
import kmptest.core.resources.generated.resources.empty_data_title
import kmptest.core.resources.generated.resources.retry
import org.jetbrains.compose.resources.stringResource

@Composable
fun <T> ViewStateHandler(
        modifier: Modifier = Modifier,
        state: ViewState<T>,
        onRetry: () -> Unit,
        loadingView: @Composable () -> Unit = { LoadingState() },
        content: @Composable (T) -> Unit
) {
    Box(modifier) {
        AnimatedContent(
                state,
                transitionSpec = {
                    fadeIn(animationSpec = tween(200)) togetherWith fadeOut(animationSpec = tween(200))
                },
                label = "ViewStateHandler"
        ) { targetState ->
            when (targetState) {
                is ViewState.Success -> content(targetState.data)
                is ViewState.Error -> RetryState(
                        message = targetState.e.description,
                        onRetry = onRetry
                )
                is ViewState.Empty -> EmptyState(
                        title = stringResource(Res.string.empty_data_title),
                        subtitle = stringResource(Res.string.empty_data_message)
                )
                is ViewState.Loading -> loadingView()
            }
        }
    }
}

@Composable
fun <T> NotEmptyViewStateHandler(
        modifier: Modifier = Modifier,
        state: NotEmptyViewState<T>,
        onRetry: () -> Unit,
        loadingView: @Composable () -> Unit = { LoadingState() },
        content: @Composable (T) -> Unit
) {
    Box(modifier) {
        AnimatedContent(
                state,
                transitionSpec = {
                    fadeIn(animationSpec = tween(200)) togetherWith fadeOut(animationSpec = tween(200))
                },
                label = "ViewStateHandler"
        ) { targetState ->
            when (targetState) {
                is NotEmptyViewState.Success -> content(targetState.data)
                is NotEmptyViewState.Error -> RetryState(
                        message = targetState.e.description,
                        onRetry = onRetry
                )
                is NotEmptyViewState.Loading -> loadingView()
            }
        }
    }
}

@Composable
fun LoadingState() {
    Box(
            modifier = Modifier
                    .fillMaxSize()
                    .safeDrawingPadding()
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun RetryState(
        modifier: Modifier = Modifier,
        message: String,
        onRetry: () -> Unit
) {
    Column(
            modifier = modifier
                    .fillMaxSize()
                    .safeDrawingPadding(),
    ) {
        Column(
                modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium
            )
        }
        Button(
                onClick = onRetry,
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
        ) {
            Text(stringResource(Res.string.retry))
        }
    }
}

@Composable
fun EmptyState(
        title: String,
        subtitle: String
) {
    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {
        Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(top = 8.dp)
        )
        Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
        )
    }
}