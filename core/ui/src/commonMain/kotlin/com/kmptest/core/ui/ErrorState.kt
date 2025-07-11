package com.kmptest.core.ui

sealed interface ErrorState<out T> {
    class None<T> : ErrorState<T>
    class Error<T>(val error: T) : ErrorState<T>

    companion object {
        val none: None<Nothing> = None()
    }
}