package com.example.application.driver._core.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.auth.data.repository.UserRepository
import com.example.application.auth.ui.viewmodel.AuthUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DriverSignUpViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun signUpDriver(
        email: String, password: String, username: String,
        firstName: String, lastName: String, phoneNumber: String,
        driverTypeId: String
    ) {
        if (email.isBlank() || password.isBlank() || username.isBlank() || firstName.isBlank() || phoneNumber.isBlank() || driverTypeId.isBlank()) {
            _uiState.value = AuthUiState.Error("Please fill all required fields")
            return
        }

        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading

            repository.signUpDriver(
                email = email, password = password, username = username,
                firstName = firstName, lastName = lastName, phoneNumber = phoneNumber,
                driverTypeId = driverTypeId
            ).onSuccess {
                _uiState.value = AuthUiState.Success
            }.onFailure { error ->
                _uiState.value = AuthUiState.Error(
                    error.localizedMessage ?: "An error occurred during registration"
                )
            }
        }
    }
}