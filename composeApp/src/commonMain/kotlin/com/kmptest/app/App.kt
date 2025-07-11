package com.kmptest.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.kmptest.navigation.AppNavigation
import kmptest.core.resources.generated.resources.Res
import kmptest.core.resources.generated.resources.loading
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val scope = rememberCoroutineScope()
    SideEffect {
        scope.launch {
            getString(Res.string.loading)
        }
    }
    MaterialTheme {
        AppNavigation()
    }
}