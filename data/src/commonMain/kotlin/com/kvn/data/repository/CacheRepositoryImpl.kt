package com.kvn.data.repository

import com.kvn.data.datasource.CacheDataSource
import com.kvn.domain.repository.CacheRepository

class CacheRepositoryImpl(private val cacheDataSource: CacheDataSource): CacheRepository {
    override suspend fun getAuthToken(): String? {
        return cacheDataSource.getAuthToken()
    }
}