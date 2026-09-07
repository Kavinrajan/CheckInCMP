package com.kvn.data.datasource

import com.kvn.data.model.ListingResponse
import com.kvn.data.model.SignInResponse
import com.kvn.data.model.request.RegisterRequest
import com.kvn.data.model.request.SignInRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class RemoteDataSource(private val httpClient: HttpClient, private val baseUrl: String) {

    private val BASE_URL = baseUrl

    private val SIGN_IN_ENDPOINT = "${BASE_URL}/auth/login"

    private val REGISTER_ENDPOINT = "${BASE_URL}/auth/register"

    private val LISTING_ENDPOINT = "${BASE_URL}/listings"

    suspend fun signIn(signInRequest: SignInRequest): Result<SignInResponse> {
        return try {
            val response = httpClient.post(urlString = SIGN_IN_ENDPOINT) {
                setBody(signInRequest)
            }
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(registerRequest: RegisterRequest): Result<SignInResponse> {
        return try {
            val response = httpClient.post(urlString = REGISTER_ENDPOINT) {
                setBody(registerRequest)
            }
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAllListing(): Result<ListingResponse> {
        return try {
            val response = httpClient.get(urlString = LISTING_ENDPOINT)
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}