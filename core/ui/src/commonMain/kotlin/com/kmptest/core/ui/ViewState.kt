package com.kmptest.core.ui

sealed class ViewState<T> {
    class Loading<T> : ViewState<T>()
    class Success<T>(val data: T) : ViewState<T>()
    class Error<T>(val e: UiError) : ViewState<T>()

    companion object Companion {
        fun <T> loading(): ViewState<T> = Loading()
        fun <T> success(data: T): ViewState<T> = Success(data)
        fun <T> error(e: UiError): ViewState<T> = Error(e)
    }
}
