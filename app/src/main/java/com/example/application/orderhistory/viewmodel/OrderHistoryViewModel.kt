package com.example.application.orderhistory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.orderhistory.data.model.OrderHistory
import com.example.application.orderhistory.data.repository.OrderHistoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrderHistoryViewModel(
    private val repository: OrderHistoryRepository
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
            repository.fetchOrderHistory()
                .onSuccess {
                    _orders.value = it
                }
                .onFailure {
                    // Handle error (bisa tampilkan snackbar)
                    _orders.value = emptyList()
                }
            _isLoading.value = false
        }
    }
}