package com.kmptest.auth.di

import androidx.compose.runtime.Composable
import com.kmptest.auth.AuthFeature
import com.kmptest.auth.AuthRepository
import com.kmptest.auth.data.AuthPreferences
import com.kmptest.auth.data.AuthRepositoryImpl
import com.kmptest.auth.domain.LoginUseCase
import com.kmptest.auth.ui.AuthScreen
import com.kmptest.auth.ui.AuthViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authModule = module {
    factory<AuthFeature> {
        object : AuthFeature {
            override val authScreen: @Composable (() -> Unit)
                get() = {
                    AuthScreen()
                }
        }
    }
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    singleOf(::AuthPreferences)

    scope<AuthScreen> {
        viewModelOf(::AuthViewModel)
        factoryOf(::LoginUseCase)
    }
}