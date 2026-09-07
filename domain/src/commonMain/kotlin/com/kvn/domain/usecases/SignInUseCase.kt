package com.kvn.domain.usecases

import com.kvn.domain.model.UserModel
import com.kvn.domain.repository.UserRepository

class SignInUseCase(private val repository: UserRepository) {
    suspend fun execute(username: String, password: String): Result<UserModel> {
        return repository.login(username, password)
    }
}