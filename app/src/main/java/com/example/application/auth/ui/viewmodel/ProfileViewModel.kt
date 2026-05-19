package com.example.application.auth.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.application.auth.data.model.User
import com.example.application.auth.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel(
    private val repository: UserRepository = UserRepository()
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)

    val user: StateFlow<User?> = _user

    init {
        loadUser()
    }

    private fun loadUser() {
        _user.value = repository.getUser()
    }
}