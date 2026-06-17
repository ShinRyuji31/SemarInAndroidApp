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
            // TODO: Nanti di sini kita jalankan LocationService buat broadcast posisi driver
            // dan update status driver ke Supabase biar siap terima order
        } else {
            // TODO: Matikan LocationService
        }
    }
}