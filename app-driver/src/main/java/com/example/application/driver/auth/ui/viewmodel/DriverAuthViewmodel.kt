package com.example.application.driver.auth.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.auth.data.repository.UserRepository
import com.example.application.driver._core.data.repository.DriverRepository
import kotlinx.coroutines.launch

class DriverAuthViewModel(
    private val userRepository: UserRepository,
    private val driverRepository: DriverRepository
) : ViewModel() {

    fun verifyDriverStatus(onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val userId = userRepository.getCurrentUserId()

            if (userId == null) {
                onError("Sesi habis. Silakan login ulang.")
                userRepository.signOut()
                return@launch
            }

            val isActive = driverRepository.checkIsDriverActive(userId)

            if (isActive) {
                onSuccess()
            } else {
                userRepository.signOut()
                onError("Akun Anda bukan Driver atau masih dalam proses review.")
            }
        }
    }

    fun logout(onSuccess: () -> Unit) {
        viewModelScope.launch {
            userRepository.signOut()
            onSuccess()
        }
    }
}