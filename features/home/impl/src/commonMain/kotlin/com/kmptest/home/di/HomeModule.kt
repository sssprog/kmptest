package com.kmptest.home.di

import androidx.compose.runtime.Composable
import com.kmptest.home.HomeFeature
import com.kmptest.home.data.HomeApi
import com.kmptest.home.data.HomeRepository
import com.kmptest.home.domain.GetAllRocketsUseCase
import com.kmptest.home.ui.HomeScreen
import com.kmptest.home.ui.HomeViewModel
import com.kmptest.home.ui.RocketScreen
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.scopedOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val homeModule = module {
    factory<HomeFeature> {
        object : HomeFeature {
            override val homeScreen: @Composable ((onItemClick: () -> Unit) -> Unit)
                get() = { onItemClick ->
                    HomeScreen(onItemClick)
                }

            override val detailsScreen: @Composable (() -> Unit)
                get() = {
                    RocketScreen()
                }
        }
    }

    scope<HomeScreen> {
        viewModelOf(::HomeViewModel)
        scopedOf(::HomeApi)
        scopedOf(::HomeRepository)
        factoryOf(::GetAllRocketsUseCase)
    }
}