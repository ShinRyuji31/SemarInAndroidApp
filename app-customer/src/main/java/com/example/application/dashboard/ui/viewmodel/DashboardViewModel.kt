package com.example.application.dashboard.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.auth.data.model.User
import com.example.application.auth.data.repository.UserRepository
import com.example.application.dashboard.data.model.PromoBanner
import com.example.application.dashboard.data.repository.DashboardRepository
import com.example.application.delivery.data.model.Store
import com.example.application.order.data.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val dashboardRepository: DashboardRepository,
    private val userRepository: UserRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _hasActiveOrder = MutableStateFlow(false)
    val hasActiveOrder: StateFlow<Boolean> = _hasActiveOrder

    private fun checkStatus() {
        viewModelScope.launch {
            val userId = userRepository.getCurrentUserId() ?: return@launch
            val order = orderRepository.getActiveOrder(userId, isDriver = false)
            _hasActiveOrder.value = order != null
        }
    }

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _topBanners = MutableStateFlow<List<PromoBanner>>(emptyList())
    val topBanners: StateFlow<List<PromoBanner>> = _topBanners

    private val _bottomBanners = MutableStateFlow<List<PromoBanner>>(emptyList())
    val bottomBanners: StateFlow<List<PromoBanner>> = _bottomBanners

    private val _affordableRestaurants = MutableStateFlow<List<Store>>(emptyList())
    val affordableRestaurants: StateFlow<List<Store>> = _affordableRestaurants

    private val _lastOrderStore = MutableStateFlow<Store?>(null)
    val lastOrderStore: StateFlow<Store?> = _lastOrderStore

    private val _selectedStore = MutableStateFlow<Store?>(null)
    val selectedStore: StateFlow<Store?> = _selectedStore

    fun selectStore(store: Store) {
        _selectedStore.value = store
    }

    init {
        loadDashboard()
        checkStatus()
    }

    private fun loadDashboard() {
        _topBanners.value = dashboardRepository.getTopBanners()
        _bottomBanners.value = dashboardRepository.getBottomBanners()

        viewModelScope.launch {
            userRepository.getUserProfile().onSuccess { user ->
                _user.value = user
            }.onFailure {
                Log.e("DashboardViewModel", "Failed to load user profile", it)
            }

            try {
                _affordableRestaurants.value = dashboardRepository.getAffordableRestaurants()
                _lastOrderStore.value = dashboardRepository.getLastOrderRestaurant()
            } catch (e: Exception) {
                Log.e("DashboardViewModel", "Failed to load stores from Supabase", e)
            }
        }
    }
}