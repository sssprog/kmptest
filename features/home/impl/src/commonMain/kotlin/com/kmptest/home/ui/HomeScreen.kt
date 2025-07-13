package com.kmptest.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.kmptest.core.ui.ViewStateHandler
import com.kmptest.home.data.Rocket
import org.koin.compose.scope.KoinScope
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.qualifier.named
import org.koin.mp.KoinPlatformTools
import org.koin.mp.generateId

internal object HomeScreen

@OptIn(KoinExperimentalAPI::class)
@Composable
internal fun HomeScreen() {
    KoinScope(remember { KoinPlatformTools.generateId() }, named<HomeScreen>()) {
        val viewModel = koinViewModel<HomeViewModel>()
        val state = viewModel.rockets.collectAsStateWithLifecycle().value
        ViewStateHandler(
                state = state,
                onRetry = { viewModel.loadRockets() },
                modifier = Modifier
                        .safeDrawingPadding()
                        .fillMaxSize()
        ) { rockets ->
            RocketsList(rockets)
        }
    }
}

@Composable
private fun RocketsList(rockets: List<Rocket>) {
    LazyColumn(
            modifier = Modifier.fillMaxSize()
    ) {
        items(rockets) { rocket ->
            RocketItem(rocket)
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun RocketItem(rocket: Rocket) {
    Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
    ) {
        rocket.rocketsImages.lastOrNull()?.let { url ->
            AsyncImage(
                    model = "https://cdn.culture.ru/images/a83d20e5-df7b-51be-b5c8-d91f77c11bf4",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
            )
            Text(
                    text = rocket.name,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}