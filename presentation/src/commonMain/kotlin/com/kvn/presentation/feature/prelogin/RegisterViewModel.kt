package com.kvn.presentation.feature.prelogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvn.domain.model.RegisterModel
import com.kvn.domain.usecases.RegisterUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(private val registerUseCase: RegisterUseCase): ViewModel() {

    private val _navigationState = MutableSharedFlow<AuthNavigation>()
    val navigationState = _navigationState.asSharedFlow()

    private val _uiState = MutableStateFlow(SignInRegUiState())
    val uiState = _uiState.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _firstName = MutableStateFlow("")
    val firstName = _firstName.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword = _confirmPassword.asStateFlow()

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onNameChange(newName: String) {
        _firstName.value = newName
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        _confirmPassword.value = newConfirmPassword
    }

    fun register() {
        viewModelScope.launch {
            if (_password.value != _confirmPassword.value) {
                _uiState.value = _uiState.value.copy(errorMessage = "Password and Confirm Password do not match")
                return@launch
            }

            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            val result = registerUseCase.execute(
                RegisterModel(
                    email = _email.value,
                    password = _password.value,
                    firstName = _firstName.value))

            result.onSuccess { user ->
                _uiState.value = _uiState.value.copy(user = user, isLoading = false, errorMessage = null)
                viewModelScope.launch {
                    _navigationState.emit(AuthNavigation.ToLogin)
                }
            }.onFailure { ex ->
                _uiState.value = _uiState.value.copy(errorMessage = ex.message, isLoading = false)
            }
        }
    }

    fun navigateToLogin() {
        viewModelScope.launch {
            _navigationState.emit(AuthNavigation.ToLogin)
        }
    }

}