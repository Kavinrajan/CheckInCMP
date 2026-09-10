package com.kvn.presentation.feature.details

import com.kvn.domain.model.TravelListing

data class TravelListingDetailUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val travelListing: TravelListing? = null
)