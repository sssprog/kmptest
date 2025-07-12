package com.kmptest.home.di

import androidx.compose.runtime.Composable
import com.kmptest.home.HomeFeature
import com.kmptest.home.ui.HomeScreen
import com.kmptest.home.ui.HomeViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val homeModule = module {
    factory<HomeFeature> {
        object : HomeFeature {
            override val homeScreen: @Composable (() -> Unit)
                get() = {
                    HomeScreen()
                }
        }
    }
//    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
//    single<AuthRepository> { AuthRepositoryImpl(get()) }
//    singleOf(::AuthPreferences)

    scope<HomeScreen> {
        viewModelOf(::HomeViewModel)
//        factoryOf(::LoginUseCase)
    }
}