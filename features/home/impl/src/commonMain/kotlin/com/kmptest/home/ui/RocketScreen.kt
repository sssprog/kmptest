package com.kmptest.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun RocketScreen() {
    Column(
            modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .safeDrawingPadding()
                    .fillMaxSize()
    ) {
        AsyncImage(
                model = "https://cdn.culture.ru/images/a83d20e5-df7b-51be-b5c8-d91f77c11bf4",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
        )
        Text(
                text = "Name asd",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(16.dp)
        )
    }
}