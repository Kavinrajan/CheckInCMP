package com.kvn.presentation.feature.details

import androidx.lifecycle.ViewModel
import com.kvn.domain.usecases.GetAllListingUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TravelListingDetailsViewModel(
    val getAllListingUseCase: GetAllListingUseCase,
    val itemID: String) : ViewModel() {

    private val _uiState = MutableStateFlow(TravelListingDetailUiState())
    val state = _uiState.asStateFlow()

    init {
        println("TravelListingDetailsViewModel: Item ID in Details ViewModel: $itemID")
    }

}