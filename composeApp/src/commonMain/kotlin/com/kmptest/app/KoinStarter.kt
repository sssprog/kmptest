package com.kmptest.app

import com.kmptest.auth.di.authModule
import com.kmptest.di.splashModule
import com.kmptest.core.ui.di.coreUiModule
import com.kmptest.data.di.networkModule
import com.kmptest.home.di.homeModule
import org.koin.core.KoinApplication

fun KoinApplication.startKoinCommon() {
    modules(
            networkModule,
            coreUiModule,
            splashModule,
            authModule,
            homeModule
    )
}