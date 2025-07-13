package com.kmptest.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diamondedge.logging.logging
import com.kmptest.core.ui.ErrorMapper
import com.kmptest.core.ui.ViewState
import com.kmptest.home.data.Rocket
import com.kmptest.home.domain.GetAllRocketsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class HomeViewModel(
        private val getAllRocketsUseCase: GetAllRocketsUseCase,
        private val errorMapper: ErrorMapper
) : ViewModel() {
    private val _rockets = MutableStateFlow<ViewState<List<Rocket>>>(ViewState.loading)
    val rockets = _rockets.asStateFlow()

    init {
        loadRockets()
    }

    fun loadRockets() {
        viewModelScope.launch {
            delay(500)
            try {
                val rockets = getAllRocketsUseCase()
                logger.d { "rockets = $rockets" }
                _rockets.value = if (rockets.isNotEmpty()) ViewState.Success(rockets) else ViewState.empty
            } catch (e: Throwable) {
                ensureActive()
                _rockets.value = ViewState.Error(errorMapper.map(e))
            }
        }
    }

    companion object {
        private val logger = logging()
    }
}