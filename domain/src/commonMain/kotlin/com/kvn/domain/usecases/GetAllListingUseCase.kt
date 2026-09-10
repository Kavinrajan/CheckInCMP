package com.kvn.domain.usecases

import com.kvn.domain.model.TravelListing
import com.kvn.domain.repository.ListingRepository

class GetAllListingUseCase(private val listingRepository: ListingRepository) {
    suspend fun execute(): List<TravelListing> {
        val data = listingRepository.getListings()
        if(data.isSuccess) {
            return data.getOrNull()!!
        } else {
            return emptyList()
        }
    }
}