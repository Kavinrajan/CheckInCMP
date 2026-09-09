package com.kvn.presentation.feature.prelogin

import com.kvn.domain.model.UserModel

data class SignInRegUiState(
    val user: UserModel? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)