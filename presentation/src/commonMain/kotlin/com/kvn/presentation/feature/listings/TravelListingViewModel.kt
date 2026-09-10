package com.kvn.presentation.feature.listings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvn.domain.usecases.GetAllListingUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TravelListingViewModel(val getAllListingUseCase: GetAllListingUseCase): ViewModel() {

    private val _uiState = MutableStateFlow(TravelListingUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadTravelListings()
    }

    fun loadTravelListings() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            try {
                val listings = getAllListingUseCase.execute() // ### Kotlin scope function TODO
                _uiState.value = _uiState.value.copy(travelListings = listings, isLoading = false)
            } catch (ex: Exception) {
                _uiState.value = _uiState.value.copy(errorMessage = ex.message, isLoading = false)
            }
        }
    }

}