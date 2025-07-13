package com.kmptest.auth

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.kmptest.auth.domain.LoginUseCase
import com.kmptest.auth.ui.AuthViewAction
import com.kmptest.auth.ui.AuthViewModel
import com.kmptest.core.ui.ErrorMapper
import com.kmptest.core.ui.UiError
import com.kmptest.data.ApiException
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class AuthViewModelTest {

    private val loginUseCase = mockk<LoginUseCase>(relaxUnitFun = true)
    private val errorMapper = mockk<ErrorMapper>()

    @Test
    fun initialStateEmpty() {
        val viewModel = createAuthViewModel()
        assertEquals("", viewModel.loginState.value)
        assertEquals("", viewModel.passwordState.value)
    }

    @Test
    fun initialStateNotEmpty() {
        val viewModel = createAuthViewModel(mapOf(
                "KEY_STATE" to mockBundleOf(
                        "KEY_LOGIN" to "qwe",
                        "KEY_PASSWORD" to "123"
                )
        ))

        assertEquals("qwe", viewModel.loginState.value)
        assertEquals("123", viewModel.passwordState.value)
    }

    @Test
    fun buttonEnabledState() {
        val viewModel = createAuthViewModel()
        runTest {
            viewModel.buttonEnabled.test {
                assertEquals(false, awaitItem())
                viewModel.onLoginChanged("q")
                viewModel.onPasswordChanged("1")
                assertEquals(true, awaitItem())
            }
        }
    }

    @Test
    fun loginError() {
        coEvery { loginUseCase(any(), any()) } throws ApiException("", "", "")
        val uiError = UiError("title", "description")
        coEvery { errorMapper.map(any()) } returns uiError
        val viewModel = createAuthViewModel()
        runTest {
            viewModel.actionState.test {
                assertEquals(AuthViewAction.None, awaitItem())
                viewModel.login()
                assertEquals(AuthViewAction.Error(uiError), awaitItem())
                viewModel.clearAction()
                assertEquals(AuthViewAction.None, awaitItem())
            }
        }
    }

    @Test
    fun loginSuccess() {
        val viewModel = createAuthViewModel()
        runTest {
            viewModel.actionState.test {
                assertEquals(AuthViewAction.None, awaitItem())
                viewModel.login()
                assertEquals(AuthViewAction.LoginSuccess, awaitItem())
            }
        }
    }

    private fun createAuthViewModel(state: Map<String, Any?> = emptyMap()) = AuthViewModel(
            savedStateHandle = SavedStateHandle(state),
            loginUseCase,
            errorMapper
    )

    private fun mockBundleOf(vararg pairs: Pair<String, Any?>): Bundle {
        val bundle = mockk<Bundle>()

        for ((key, value) in pairs) {
            when (value) {
                is Boolean -> every { bundle.getBoolean(key) } returns value
                is Byte -> every { bundle.getByte(key) } returns value
                is Char -> every { bundle.getChar(key) } returns value
                is Double -> every { bundle.getDouble(key) } returns value
                is Float -> every { bundle.getFloat(key) } returns value
                is Int -> every { bundle.getInt(key) } returns value
                is Long -> every { bundle.getLong(key) } returns value
                is Short -> every { bundle.getShort(key) } returns value
                is String -> every { bundle.getString(key) } returns value
                else -> throw UnsupportedOperationException("Type is not supported.")
            }
        }

        return bundle
    }
}