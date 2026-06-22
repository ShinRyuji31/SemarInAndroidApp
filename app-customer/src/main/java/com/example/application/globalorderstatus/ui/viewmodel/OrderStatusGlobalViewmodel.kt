package com.example.application.globalorderstatus.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.auth.data.repository.UserRepository
import com.example.application.order.data.dto.ActiveOrderDto
import com.example.application.order.data.repository.OrderRepository
import kotlinx.coroutines.Job
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

    private val _orderStatus = MutableStateFlow<String?>(null)
    val orderStatus: StateFlow<String?> = _orderStatus

    private var job: Job? = null

    private var currentListeningOrderId: String? = null

    fun startListening(orderId: String) {
        if (currentListeningOrderId == orderId) return

        job?.cancel()

        currentListeningOrderId = orderId

        job = viewModelScope.launch {
            val channel = orderRepository.listenOrderStatus(orderId)

            channel.collect { status ->
                _orderStatus.value = status
            }
        }
    }
}