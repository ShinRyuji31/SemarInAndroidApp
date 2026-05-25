package com.example.application.auth.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.auth.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val repository: UserRepository = UserRepository.getInstance()
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun signUp(
        email: String, 
        password: String, 
        username: String, 
        firstName: String, 
        lastName: String, 
        phoneNumber: String
    ) {
        // Local validation check
        if (email.isBlank() || password.isBlank() || username.isBlank() || firstName.isBlank() || phoneNumber.isBlank()) {
            _uiState.value = AuthUiState.Error("Please fill all required fields")
            return
        }

        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            
            // Call the refactored repository signUp which handles Auth + Profile insertion
            repository.signUp(
                email = email,
                password = password,
                username = username,
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phoneNumber
            ).onSuccess {
                _uiState.value = AuthUiState.Success
            }.onFailure { error ->
                _uiState.value = AuthUiState.Error(
                    error.localizedMessage ?: "An error occurred during registration"
                )
            }
        }
    }

    fun resetState() {
        _uiState.value = AuthUiState.Idle
    }
}
