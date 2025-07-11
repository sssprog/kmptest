package com.kmptest.auth.ui

import com.kmptest.core.ui.UiError

internal sealed interface AuthViewAction {
    class Error(val error: UiError): AuthViewAction
    object LoginSuccess: AuthViewAction
}