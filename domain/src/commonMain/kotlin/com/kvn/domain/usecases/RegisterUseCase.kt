package com.kvn.domain.usecases

import com.kvn.domain.model.RegisterModel
import com.kvn.domain.model.UserModel
import com.kvn.domain.repository.UserRepository

class RegisterUseCase(private val repository: UserRepository) {
    suspend fun execute(request: RegisterModel): Result<UserModel> {
        return repository.register(request)
    }

}