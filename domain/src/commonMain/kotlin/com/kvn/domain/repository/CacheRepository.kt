package com.kvn.domain.repository

interface CacheRepository {
    suspend fun getAuthToken(): String?
}