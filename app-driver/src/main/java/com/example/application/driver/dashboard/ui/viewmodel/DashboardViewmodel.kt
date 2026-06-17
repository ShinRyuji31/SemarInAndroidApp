package com.example.application.driver.dashboard.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DriverDashboardViewModel : ViewModel() {

    private val _isOnline = MutableStateFlow(false)
    val isOnline: StateFlow<Boolean> = _isOnline.asStateFlow()

    fun setOnlineStatus(online: Boolean) {
        _isOnline.value = online

        if (online) {
            // TODO: brodcast posisi driver
        } else {
            // TODO: Matikan LocationService
        }
    }
}