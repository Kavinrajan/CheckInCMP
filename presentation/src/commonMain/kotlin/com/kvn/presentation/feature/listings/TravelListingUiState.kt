package com.kvn.presentation.feature.listings

import com.kvn.domain.model.TravelListing

data class TravelListingUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val travelListings: List<TravelListing> = emptyList()
) {

    val hasListings: Boolean
        get() = travelListings.isNotEmpty()

    val showEmptyState: Boolean
        get() = !isLoading && !hasListings && errorMessage == null

}
