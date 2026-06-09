package com.example.application.profile.ui.viewmodel

import com.example.application.auth.data.model.User

class ProfileUiiState {

    sealed class ProfileUiState {

        object Loading : ProfileUiState()

        data class Success(
            val user: User
        ) : ProfileUiState()

        object Updating : ProfileUiState()

        object UpdateSuccess : ProfileUiState()

        data class Error(
            val message: String
        ) : ProfileUiState()

        object ProfileNotFound : ProfileUiState()

        object Unauthenticated : ProfileUiState()
    }

}