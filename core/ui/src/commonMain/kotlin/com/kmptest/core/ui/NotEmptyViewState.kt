package com.kmptest.core.ui

sealed class NotEmptyViewState<out T> {
    class Loading<T> : NotEmptyViewState<T>()
    class Success<T>(val data: T) : NotEmptyViewState<T>()
    class Error<T>(val e: UiError) : NotEmptyViewState<T>()

    companion object Companion {
        val loading = Loading<Nothing>()
    }
}
