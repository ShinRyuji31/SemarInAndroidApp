package com.example.application.driver.dashboard.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.auth.data.repository.UserRepository
import com.example.application.driver._core.ui.viewmodel.CoreViewmodel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val coreViewModel: CoreViewmodel,
    private val userRepository: UserRepository // 🚀 TAMBAHKAN INI
) : ViewModel() {

    val isOnline = coreViewModel.isOnline
    val activeOrder = coreViewModel.activeOrder

    fun setOnlineStatus(value: Boolean) {
        coreViewModel.setOnlineStatus(value)
    }

    private val _driverName = MutableStateFlow("Loading...")
    val driverName: StateFlow<String> = _driverName.asStateFlow()

    private val _profilePicUrl = MutableStateFlow<String?>(null)
    val profilePicUrl: StateFlow<String?> = _profilePicUrl.asStateFlow()

    init {
        fetchDriverProfile()
    }

    private fun fetchDriverProfile() {
        viewModelScope.launch {
            userRepository.getUserProfile().onSuccess { user ->
                if (user != null) {
                    val fullName = "${user.firstName} ${user.lastName ?: ""}".trim()
                    _driverName.value = fullName.ifBlank { user.username }
                } else {
                    _driverName.value = "Driver Aktif"
                }
            }.onFailure {
                _driverName.value = "Driver Aktif"
            }
        }
    }
}