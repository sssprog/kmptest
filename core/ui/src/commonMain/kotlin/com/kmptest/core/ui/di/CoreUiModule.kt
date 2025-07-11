package com.kmptest.core.ui.di

import com.kmptest.core.ui.ErrorMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val coreUiModule = module {
    factoryOf(::ErrorMapper)
}