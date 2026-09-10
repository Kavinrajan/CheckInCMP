package com.kvn.domain.repository

import com.kvn.domain.model.TravelListing

interface ListingRepository {
    suspend fun getListings(): Result<List<TravelListing>>
}