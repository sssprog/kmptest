package com.kmptest.core.ui

sealed class ViewState<out T> {
    class Loading<T> : ViewState<T>()
    class Empty<T> : ViewState<T>()
    class Success<T>(val data: T) : ViewState<T>()
    class Error<T>(val e: UiError) : ViewState<T>()

    companion object Companion {
        val loading = Loading<Nothing>()
        val empty = Empty<Nothing>()
    }
}
