package com.example.application.globalorderstatus.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.auth.data.repository.UserRepository
import com.example.application.order.data.dto.ActiveOrderDto
import com.example.application.order.data.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrderStatusGlobalViewmodel(
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _activeOrder = MutableStateFlow<ActiveOrderDto?>(null)
    val activeOrder: StateFlow<ActiveOrderDto?> = _activeOrder

    init {
        checkActiveOrder()
    }

    fun checkActiveOrder() {
        viewModelScope.launch {
            val userId = userRepository.getCurrentUserId() ?: return@launch
            _activeOrder.value = orderRepository.getActiveOrder(userId, isDriver = false)
        }
    }
}