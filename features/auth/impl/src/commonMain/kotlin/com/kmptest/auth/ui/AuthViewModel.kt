package com.kmptest.auth.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedState
import androidx.savedstate.read
import androidx.savedstate.savedState
import com.diamondedge.logging.logging
import com.kmptest.auth.domain.LoginUseCase
import com.kmptest.core.ui.ErrorMapper
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class AuthViewModel(
        savedStateHandle: SavedStateHandle,
        private val loginUseCase: LoginUseCase,
        private val errorMapper: ErrorMapper
) : ViewModel() {

    private val _loginState = MutableStateFlow("")
    val loginState = _loginState.asStateFlow()
    private val _passwordState = MutableStateFlow("")
    val passwordState = _passwordState.asStateFlow()
    private val _buttonEnabled = MutableStateFlow(false)
    val buttonEnabled = _buttonEnabled.asStateFlow()

    private val _actionState = MutableStateFlow<AuthViewAction>(AuthViewAction.None)
    val actionState = _actionState.asStateFlow()


    init {
        val state = savedStateHandle.get<SavedState>(KEY_STATE)
        state?.read {
            _loginState.value = getString(KEY_LOGIN)
            _passwordState.value = getString(KEY_PASSWORD)
        }

        savedStateHandle.setSavedStateProvider(KEY_STATE) {
            savedState {
                putString(KEY_LOGIN, _loginState.value)
                putString(KEY_PASSWORD, _passwordState.value)
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            try {
                loginUseCase(_loginState.value, _passwordState.value)
                _actionState.value = AuthViewAction.LoginSuccess
            } catch (e: Throwable) {
                ensureActive()
                _actionState.value = AuthViewAction.Error(errorMapper.map(e))
            }
        }
    }

    fun clearAction() {
        _actionState.value = AuthViewAction.None
    }

    fun onLoginChanged(value: String) {
        _loginState.value = value
        updateButtonState()
    }

    fun onPasswordChanged(value: String) {
        _passwordState.value = value
        updateButtonState()
    }

    private fun updateButtonState() {
        _buttonEnabled.value = _loginState.value.isNotEmpty() && _passwordState.value.isNotEmpty()
    }

    companion object {
        private const val KEY_STATE = "KEY_STATE"
        private const val KEY_LOGIN = "KEY_LOGIN"
        private const val KEY_PASSWORD = "KEY_PASSWORD"
        private val logger = logging()
    }
}