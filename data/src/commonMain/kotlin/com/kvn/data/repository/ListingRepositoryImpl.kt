package com.kvn.data.repository

import com.kvn.data.datasource.RemoteDataSource
import com.kvn.data.mappers.TravelListingMapper
import com.kvn.domain.model.TravelListing
import com.kvn.domain.repository.ListingRepository

class ListingRepositoryImpl(val dataSource: RemoteDataSource) : ListingRepository {
    override suspend fun getListings(): Result<List<TravelListing>> {
        val dos = dataSource.getAllListing()
        if (dos.isSuccess) {
            val listings = dos.getOrNull()!!.listings
            val models = TravelListingMapper.toDomain(listings)
            return Result.success(models)
        } else {
             throw dos.exceptionOrNull()!!
        }
    }
}