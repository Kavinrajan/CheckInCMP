package com.kvn.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.kvn.data.datasource.CacheDataSource
import com.kvn.data.datasource.RemoteDataSource
import com.kvn.data.repository.CacheRepositoryImpl
import com.kvn.data.repository.UserRepositoryImp
import com.kvn.domain.repository.CacheRepository
import com.kvn.domain.repository.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModule = module {
    single<HttpClient> {
        HttpClient {
            // Configure the HttpClient here (e.g., install features, set timeouts, etc.)
            install(ContentNegotiation) {
                // Configure content negotiation (e.g., JSON serialization)
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.DEFAULT
            }
            install(DefaultRequest) {
                // Set default headers or parameters for all requests
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }

    single { RemoteDataSource(httpClient = get<HttpClient>(), get()) }

    single { CacheDataSource(dataStore = get<DataStore<Preferences>>()) }

    single<CacheRepository> {
        CacheRepositoryImpl(cacheDataSource = get<CacheDataSource>())
    }

    single<UserRepository> {
        UserRepositoryImp(dataSource = get<RemoteDataSource>(), cacheDataSource = get<CacheDataSource>())
    }

    /* single<ListingRepository> {
        ListingRepositoryImpl(dataSource = get<RemoteDataSource>())
    } */

}