package com.kvn.domain.di

import com.kvn.domain.repository.CacheRepository
import com.kvn.domain.repository.UserRepository
import com.kvn.domain.usecases.GetAuthTokenUseCase
import com.kvn.domain.usecases.RegisterUseCase
import com.kvn.domain.usecases.SignInUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
     // GetAllListingUseCase(get<ListingRepository>())
    }

    factory {
        SignInUseCase(get<UserRepository>())
    }

    factory {
        RegisterUseCase(get<UserRepository>())
    }

    factory {
        GetAuthTokenUseCase(get<CacheRepository>())
    }

}