package com.kmptest.kmptest

import android.app.Application
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.util.DebugLogger
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
        SingletonImageLoader.setSafe { context ->
            ImageLoader.Builder(context)
                    .logger(DebugLogger())
                    .build()
        }
    }
}