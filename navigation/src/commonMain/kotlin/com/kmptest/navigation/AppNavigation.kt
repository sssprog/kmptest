package com.kmptest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.kmptest.auth.AuthFeature
import com.kmptest.home.HomeFeature
import com.kmptest.splash.SplashScreen
import org.koin.compose.koinInject

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.Splash) {
        composable<Destinations.Splash> {
            SplashScreen(
                    openApp = {
                        navController.navigateTo(Destinations.Home, Destinations.Splash)
                    },
                    openAuth = {
                        navController.navigateTo(Destinations.Auth, Destinations.Splash)
                    }
            )
        }

        composable<Destinations.Auth> {
            koinInject<AuthFeature>().authScreen {
                navController.navigateTo(Destinations.Home, Destinations.Auth)
            }
        }

        composable<Destinations.Home> {
            koinInject<HomeFeature>().homeScreen()
        }
    }
}

fun NavController.navigateTo(destination: Any, popupUntilRoute: Any? = null) {
    this.navigate(destination, navOptions {
        popupUntilRoute?.let {
            popUpTo(it) {
                inclusive = true
            }
        }
    })
}