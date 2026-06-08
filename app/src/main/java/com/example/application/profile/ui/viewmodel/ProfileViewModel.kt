package com.example.application.profile.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.auth.data.model.User
import com.example.application.auth.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ProfileUiState {
    object Loading : ProfileUiState()
    data class Success(val user: User) : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()
    object ProfileNotFound : ProfileUiState()
    object Unauthenticated : ProfileUiState()
}

class ProfileViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    fun loadProfile() {
        viewModelScope.launch {
            _uiState.value = ProfileUiState.Loading
            repository.getUserProfile()
                .onSuccess { user ->
                    if (user != null) {
                        _uiState.value = ProfileUiState.Success(user)
                    } else {
                        // User is authenticated but row in 'profile' table is missing
                        _uiState.value = ProfileUiState.ProfileNotFound
                    }
                }
                .onFailure { error ->
                    Log.e("ProfileViewModel", "Error loading profile from 'PROFILE' table", error)
                    _uiState.value = ProfileUiState.Error("Failed to load profile data")
                }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.signOut()
            _uiState.value = ProfileUiState.Unauthenticated
        }
    }
}
