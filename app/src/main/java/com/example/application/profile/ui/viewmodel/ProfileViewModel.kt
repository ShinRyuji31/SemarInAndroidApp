package com.example.application.profile.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.auth.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiiState.ProfileUiState>(ProfileUiiState.ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiiState.ProfileUiState> = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    fun loadProfile() {
        viewModelScope.launch {
            _uiState.value = ProfileUiiState.ProfileUiState.Loading
            repository.getUserProfile()
                .onSuccess { user ->
                    if (user != null) {
                        _uiState.value = ProfileUiiState.ProfileUiState.Success(user)
                    } else {
                        // User is authenticated but row in 'profile' table is missing
                        _uiState.value = ProfileUiiState.ProfileUiState.ProfileNotFound
                    }
                }
                .onFailure { error ->
                    Log.e("ProfileViewModel", "Error loading profile from 'PROFILE' table", error)
                    _uiState.value = ProfileUiiState.ProfileUiState.Error("Failed to load profile data")
                }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.signOut()
            _uiState.value = ProfileUiiState.ProfileUiState.Unauthenticated
        }
    }

    fun updateProfile(
        username: String,
        firstName: String,
        phoneNumber: String
    ) {

        viewModelScope.launch {

            _uiState.value = ProfileUiiState.ProfileUiState.Updating

            repository.updateProfile(
                username = username,
                firstName = firstName,
                phoneNumber = phoneNumber
            )
                .onSuccess {

                    loadProfile()

                }
                .onFailure {

                    _uiState.value =
                        ProfileUiiState.ProfileUiState.Error(
                            "Failed to update profile"
                        )
                }
        }
    }
}
