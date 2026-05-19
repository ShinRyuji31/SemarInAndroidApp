package com.example.application.dashboard.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.application.auth.data.model.User
import com.example.application.auth.data.repository.UserRepository
import com.example.application.dashboard.data.model.PromoBanner
import com.example.application.dashboard.data.repository.DashboardRepository
import com.example.application.delivery.data.model.Store
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DashboardViewModel(
    private val dashboardRepository: DashboardRepository = DashboardRepository(),
    private val userRepository: UserRepository = UserRepository()
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _topBanners =
        MutableStateFlow<List<PromoBanner>>(emptyList())

    val topBanners: StateFlow<List<PromoBanner>>
            = _topBanners

    private val _bottomBanners =
        MutableStateFlow<List<PromoBanner>>(emptyList())

    val bottomBanners: StateFlow<List<PromoBanner>>
            = _bottomBanners

    private val _affordableRestaurants =
        MutableStateFlow<List<Store>>(emptyList())

    val affordableRestaurants: StateFlow<List<Store>>
            = _affordableRestaurants

    private val _lastOrderStore =
        MutableStateFlow<Store?>(null)

    val lastOrderStore: StateFlow<Store?>
            = _lastOrderStore

    init {
        loadDashboard()
    }

    private fun loadDashboard() {

        _user.value = userRepository.getUser()

        _topBanners.value =
            dashboardRepository.getTopBanners()

        _bottomBanners.value =
            dashboardRepository.getBottomBanners()

        _affordableRestaurants.value =
            dashboardRepository.getAffordableRestaurants()

        _lastOrderStore.value =
            dashboardRepository.getLastOrderRestaurant()
    }
}