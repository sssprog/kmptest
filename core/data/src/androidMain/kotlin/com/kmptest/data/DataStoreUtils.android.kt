package com.kmptest.data

import android.content.Context
import org.koin.core.context.GlobalContext

actual fun dataStoreFilePath(fileName: String): String {
    val context = GlobalContext.get().get<Context>()
    return context.filesDir.resolve(fileName).absolutePath
}