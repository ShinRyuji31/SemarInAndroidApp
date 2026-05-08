package com.example.application.viewmodel

import androidx.lifecycle.ViewModel
import com.example.application.data.model.PromoBanner
import com.example.application.data.model.Restaurant
import com.example.application.data.model.User
import com.example.application.data.repository.DashboardRepository
import com.example.application.data.repository.UserRepository
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
        MutableStateFlow<List<Restaurant>>(emptyList())

    val affordableRestaurants: StateFlow<List<Restaurant>>
            = _affordableRestaurants

    private val _lastOrderRestaurant =
        MutableStateFlow<Restaurant?>(null)

    val lastOrderRestaurant: StateFlow<Restaurant?>
            = _lastOrderRestaurant

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

        _lastOrderRestaurant.value =
            dashboardRepository.getLastOrderRestaurant()
    }
}