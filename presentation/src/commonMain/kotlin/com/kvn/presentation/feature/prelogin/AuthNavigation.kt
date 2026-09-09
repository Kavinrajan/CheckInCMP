package com.kvn.presentation.feature.prelogin

sealed class AuthNavigation {
    object ToLogin : AuthNavigation()
    object ToSignUp : AuthNavigation()
    object ToListing : AuthNavigation()
}