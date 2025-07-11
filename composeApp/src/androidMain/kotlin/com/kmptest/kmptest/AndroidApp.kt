package com.kmptest.kmptest

import android.app.Application
import com.kmptest.app.startKoinCommon
import org.koin.android.ext.android.getKoinScope
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AndroidApp)
            startKoinCommon()
        }
    }
}