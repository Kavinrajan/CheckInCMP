package com.kvn.data.repository

import com.kvn.data.datasource.CacheDataSource
import com.kvn.data.datasource.RemoteDataSource
import com.kvn.data.mappers.RegisterRequestMapper
import com.kvn.domain.model.RegisterModel
import com.kvn.domain.model.UserModel
import com.kvn.domain.repository.UserRepository
import com.kvn.data.mappers.UserMapper
import com.kvn.data.model.request.SignInRequest

class UserRepositoryImp(val dataSource: RemoteDataSource, private val cacheDataSource: CacheDataSource): UserRepository {
    override suspend fun login(email: String, password: String): Result<UserModel> {
        return try {
            val response = dataSource.signIn(SignInRequest(email, password))
            if(response.isSuccess) {
                val response = response.getOrNull()!!
                val userModel = UserMapper.toDomain(response.user)
                cacheDataSource.saveAuthToken(response.token)
                Result.success(userModel)
            } else {
                Result.failure(Exception("Login failed with status code: ${response.exceptionOrNull()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(registerModel: RegisterModel): Result<UserModel> {
         return try {
            val response = dataSource.register(RegisterRequestMapper.toDto(registerModel))
            if (response.isSuccess) {
                val response = response.getOrNull()!!
                val userModel = UserMapper.toDomain(response.user)
                cacheDataSource.saveAuthToken(response.token)
                Result.success(userModel)
            } else {
                Result.failure(Exception("Registration failed with status code: ${response.exceptionOrNull()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}