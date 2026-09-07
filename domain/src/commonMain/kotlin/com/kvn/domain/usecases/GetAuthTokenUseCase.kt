package com.kvn.domain.usecases

import com.kvn.domain.repository.CacheRepository

class GetAuthTokenUseCase(private val repository: CacheRepository) {
    suspend fun execute(): String? {
        return repository.getAuthToken()
    }
}