package com.kvn.presentation.di

import com.kvn.domain.usecases.GetAllListingUseCase
import com.kvn.domain.usecases.GetAuthTokenUseCase
import com.kvn.domain.usecases.RegisterUseCase
import com.kvn.domain.usecases.SignInUseCase
import com.kvn.presentation.feature.app.AppViewModel
import com.kvn.presentation.feature.details.TravelListingDetailsViewModel
import com.kvn.presentation.feature.listings.TravelListingViewModel
import com.kvn.presentation.feature.prelogin.RegisterViewModel
import com.kvn.presentation.feature.prelogin.SignInViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { TravelListingViewModel(get<GetAllListingUseCase>()) }
    viewModel { SignInViewModel(get<SignInUseCase>()) }
    viewModel { RegisterViewModel(get<RegisterUseCase>()) }
    viewModel { (itemID:String) -> TravelListingDetailsViewModel(get(), itemID) }
    viewModel { AppViewModel(get<GetAuthTokenUseCase>()) }
}