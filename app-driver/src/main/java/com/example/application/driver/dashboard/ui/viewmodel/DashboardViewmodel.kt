package com.example.application.driver.dashboard.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.application.driver._core.ui.viewmodel.CoreViewmodel

class DashboardViewModel(
    private val coreViewModel: CoreViewmodel
) : ViewModel() {

    val isOnline =
        coreViewModel.isOnline

    val activeOrder =
        coreViewModel.activeOrder

    fun setOnlineStatus(
        value: Boolean
    ) {
        coreViewModel.setOnlineStatus(value)
    }
}