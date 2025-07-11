package com.kmptest.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class SplashViewModel(
        private val isAuthorizedUseCase: IsAuthorizedUseCase
) : ViewModel() {

    private val _action = MutableStateFlow(SplashAction.NONE)
    val action = _action.asStateFlow()

    init {
        viewModelScope.launch {
            if (isAuthorizedUseCase()) {
                _action.value = SplashAction.OPEN_APP
            } else {
                _action.value = SplashAction.OPEN_AUTH
            }
        }
    }

    fun resetAction() {
        _action.value = SplashAction.NONE
    }
}