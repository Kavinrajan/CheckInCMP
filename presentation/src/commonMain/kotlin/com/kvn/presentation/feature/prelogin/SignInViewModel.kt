package com.kvn.presentation.feature.prelogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvn.domain.usecases.SignInUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(private val loginUseCase: SignInUseCase) : ViewModel() {

    private val _navigationState = MutableSharedFlow<AuthNavigation>()
    val navigationState = _navigationState.asSharedFlow()

    private val _uiState = MutableStateFlow(SignInRegUiState())
    val uiState = _uiState.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun signIn() {
        viewModelScope.launch {
            val email = _email.value
            val password = _password.value
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            if (email.isBlank() || password.isBlank()) {
                _uiState.emit(
                    SignInRegUiState(
                        errorMessage = "Email and password cannot be empty",
                        isLoading = false
                    )
                )
                return@launch
            }

            val result = loginUseCase.execute(email, password)
            result.onSuccess { user ->
                _uiState.value = _uiState.value.copy(user = user, isLoading = false)
                _navigationState.emit(AuthNavigation.ToListing)
            }.onFailure { ex ->
                _uiState.value =
                    _uiState.value.copy(errorMessage = ex.message, isLoading = false)            }
        }
    }

    fun onSignUpClick() {
        viewModelScope.launch {
            _navigationState.emit(AuthNavigation.ToSignUp)
        }
    }

}