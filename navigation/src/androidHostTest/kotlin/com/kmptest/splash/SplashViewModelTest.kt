package com.kmptest.splash

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SplashViewModelTest {

    private val isAuthorizedUseCase = mockk<IsAuthorizedUseCase>()

    @Test
    fun testNotAuthorized() {
        coEvery { isAuthorizedUseCase() } answers { false }
        runTest {
            val viewModel = SplashViewModel(isAuthorizedUseCase)
            viewModel.action.test {
                assertEquals(SplashAction.OPEN_AUTH, awaitItem())
            }
        }
    }

    @Test
    fun testAuthorized() {
        coEvery { isAuthorizedUseCase() } answers { true }
        runTest {
            val viewModel = SplashViewModel(isAuthorizedUseCase)
            viewModel.action.test {
                assertEquals(SplashAction.OPEN_APP, awaitItem())
            }
        }
    }
}