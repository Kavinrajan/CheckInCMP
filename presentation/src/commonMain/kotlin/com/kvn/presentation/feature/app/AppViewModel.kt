package com.kvn.presentation.feature.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvn.domain.usecases.GetAuthTokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel (private val useCase: GetAuthTokenUseCase): ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val state = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            loadAuthToken()
        }
    }

    suspend fun loadAuthToken() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        try {
            val token = useCase.execute()
            _uiState.value = _uiState.value.copy(authToken = token, isLoading = false)
        } catch (ex: Exception) {
            _uiState.value = _uiState.value.copy(authToken = null, isLoading = false)
        }
    }
}