package com.example.application.auth.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.auth.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = AuthUiState.Error("Please fill all fields")
            return
        }

        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            repository.signIn(email, password)
                .onSuccess {
                    _uiState.value = AuthUiState.Success
                }
                .onFailure { error ->
                    val message = when {
                        error.message?.contains("invalid_credentials", true) == true -> "Invalid email or password"
                        else -> "Login failed. Please try again."
                    }
                    _uiState.value = AuthUiState.Error(message)
                }
        }
    }
    
    fun resetState() {
        _uiState.value = AuthUiState.Idle
    }
}
