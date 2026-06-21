package com.example.application.driver._core.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application._core.data.location.LocationRepository
import com.example.application.auth.data.repository.UserRepository
import com.example.application.driver.order.data.dto.ActiveOrderDto
import com.example.application.driver.order.data.repository.OrderRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class CoreViewmodel(
    private val orderRepository: OrderRepository,
    private val locationRepository: LocationRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _isOnline = MutableStateFlow(false)
    val isOnline: StateFlow<Boolean> = _isOnline.asStateFlow()

    private val _incomingOrder = MutableStateFlow<ActiveOrderDto?>(null)
    val incomingOrder: StateFlow<ActiveOrderDto?> = _incomingOrder.asStateFlow()

    private val _activeOrder = MutableStateFlow<ActiveOrderDto?>(null)
    val activeOrder: StateFlow<ActiveOrderDto?> = _activeOrder.asStateFlow()

    private var trackingJob: Job? = null

    fun setOnlineStatus(online: Boolean) {
        _isOnline.value = online
        if (online) startTrackingAndPolling() else stopTrackingAndPolling()
    }

    private fun startTrackingAndPolling() {
        trackingJob?.cancel()
        trackingJob = viewModelScope.launch {
            val driverId = userRepository.getCurrentUserId() ?: return@launch

            while (isActive && _isOnline.value) {
                val location = locationRepository.fetchCurrentLocation()
                if (location != null) {
                    orderRepository.updateDriverLocation(driverId, location.latitude, location.longitude)
                }
                _incomingOrder.value = orderRepository.getIncomingOrder(driverId)
                _activeOrder.value = orderRepository.getActiveOrder(driverId)

                delay(10000)
            }
        }
    }

    private fun stopTrackingAndPolling() {
        trackingJob?.cancel()
        trackingJob = null
        _incomingOrder.value = null
        _activeOrder.value = null
    }

    fun acceptOrder(orderId: String) {
        viewModelScope.launch {
            val success = orderRepository.updateOrderStatus(orderId, "ACCEPTED")
            if (success) {
                _incomingOrder.value = null

                val driverId = userRepository.getCurrentUserId() ?: return@launch

                orderRepository.updateOrderStatus(orderId, "PICKING_UP")
                _activeOrder.value = orderRepository.getActiveOrder(driverId)
            }
        }
    }

    fun confirmPickup(orderId: String) {
        viewModelScope.launch {
            val success = orderRepository.updateOrderStatus(orderId, "DELIVERING")
            if (success) {
                val driverId = userRepository.getCurrentUserId() ?: return@launch
                _activeOrder.value = orderRepository.getActiveOrder(driverId)
            }
        }
    }

    fun arrivedAtDestination(orderId: String) {
        viewModelScope.launch {
            val success = orderRepository.updateOrderStatus(orderId, "WAITING_PAYMENT")
            if (success) {
                val driverId = userRepository.getCurrentUserId() ?: return@launch
                _activeOrder.value = orderRepository.getActiveOrder(driverId)
            }
        }
    }

    fun confirmPayment(orderId: String) {
        viewModelScope.launch {
            val success = orderRepository.updateOrderStatus(orderId, "COMPLETED")
            if (success) {
                _activeOrder.value = null
            }
        }
    }

    fun declineOrder(orderId: String) {
        viewModelScope.launch {
            orderRepository.updateOrderStatus(orderId, "NULL")
            _incomingOrder.value = null
        }
    }
}