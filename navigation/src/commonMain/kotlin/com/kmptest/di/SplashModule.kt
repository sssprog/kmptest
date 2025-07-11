package com.kmptest.di

import com.kmptest.splash.IsAuthorizedUseCase
import com.kmptest.splash.SplashScreen
import com.kmptest.splash.SplashViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val splashModule = module {
    scope<SplashScreen> {
        factoryOf(::IsAuthorizedUseCase)
        viewModelOf(::SplashViewModel)
    }
}