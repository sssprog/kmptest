package com.kmptest.core.ui

import com.kmptest.data.ApiException
import kmptest.core.resources.generated.resources.Res
import kmptest.core.resources.generated.resources.default_error_message
import kmptest.core.resources.generated.resources.default_error_title
import org.jetbrains.compose.resources.getString

class ErrorMapper {
    suspend fun map(e: Throwable): UiError {
        if (e !is ApiException) return defaultError()
        return UiError(
                title = e.title,
                description = e.description
        )
    }

    private suspend fun defaultError(): UiError {
        return UiError(getString(Res.string.default_error_title), getString(Res.string.default_error_message))
    }
}