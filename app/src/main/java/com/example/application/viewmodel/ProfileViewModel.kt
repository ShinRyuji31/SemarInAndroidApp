package com.example.application.viewmodel

import androidx.lifecycle.ViewModel
import com.example.application.data.model.User
import com.example.application.data.repository.UserRepository
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