package com.example.application.orderhistory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.auth.data.repository.UserRepository // 🚀 IMPORT USER REPO
import com.example.application.orderhistory.data.model.OrderHistory
import com.example.application.orderhistory.data.repository.OrderHistoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrderHistoryViewModel(
    private val repository: OrderHistoryRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _orders = MutableStateFlow<List<OrderHistory>>(emptyList())
    val orders: StateFlow<List<OrderHistory>> = _orders

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadOrders()
    }

    fun loadOrders() {
        viewModelScope.launch {
            _isLoading.value = true

            val userId = userRepository.getCurrentUserId()

            if (userId != null) {
                repository.fetchOrderHistory(userId)
                    .onSuccess {
                        _orders.value = it
                    }
                    .onFailure {
                        _orders.value = emptyList()
                    }
            } else {
                _orders.value = emptyList()
            }

            _isLoading.value = false
        }
    }
}