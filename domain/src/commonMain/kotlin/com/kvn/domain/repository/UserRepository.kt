package com.kvn.domain.repository

import com.kvn.domain.model.RegisterModel
import com.kvn.domain.model.UserModel

interface UserRepository {
    suspend fun login(email: String, password: String): Result<UserModel>
    suspend fun register(registerModel: RegisterModel): Result<UserModel>
}