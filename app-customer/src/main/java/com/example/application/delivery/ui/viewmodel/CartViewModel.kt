package com.example.application.delivery.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.auth.data.repository.UserRepository
import com.example.application.delivery.data.model.CartItem
import com.example.application.delivery.data.repository.CartRepository
import com.example.application.order.data.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.UUID

class CartViewModel(
    private val cartRepository: CartRepository,
    private val userRepository: UserRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems = _cartItems.asStateFlow()

    private val _isCheckingOut = MutableStateFlow(false)
    val isCheckingOut = _isCheckingOut.asStateFlow()

    init {
        viewModelScope.launch {
            cartRepository.getCartItems().collect {
                _cartItems.value = it
            }
        }
    }

    val subtotal = cartItems.map { carts ->
        carts.sumOf { it.price * it.quantity }
    }

    val deliveryFee = MutableStateFlow(7000)

    val total = combine(subtotal, deliveryFee) { sub, fee ->
        sub + fee
    }

    fun processCheckout(
        userLat: Double,
        userLon: Double,
        userAddress: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val userId = userRepository.getCurrentUserId()
            if (userId == null) {
                onError("User tidak ditemukan, silakan login ulang.")
                return@launch
            }

            // 2. Cek apakah ada order aktif pakai orderRepository
            val existingOrder = orderRepository.getActiveOrder(userId, isDriver = false)
            if (existingOrder != null) {
                onError("Gagal: Anda sudah memiliki pesanan yang sedang berjalan!")
                return@launch
            }

            // 3. Lanjut proses checkout kalau aman
            if (_cartItems.value.isEmpty()) return@launch
            _isCheckingOut.value = true

            val currentSubtotal = _cartItems.value.sumOf { it.price * it.quantity }
            val currentTotal = currentSubtotal + deliveryFee.value

            val result = cartRepository.checkoutOrder(
                userId = userId,
                items = _cartItems.value,
                total = currentTotal,
                userLat = userLat,
                userLon = userLon,
                userAddress = userAddress
            )

            if (result.isSuccess) {
                onSuccess()
            } else {
                onError("Gagal melakukan checkout: ${result.exceptionOrNull()?.message}")
            }
            _isCheckingOut.value = false
        }
    }

    fun addToCart(
        inventoryId: String,
        name: String,
        price: Int,
        imageUrl: String?,
        storeId: String,
        storeType: String
    ) {
        viewModelScope.launch {
            val current = _cartItems.value.toMutableList()
            val existingIndex = current.indexOfFirst { it.storeInventoryId == inventoryId }

            if (existingIndex != -1) {
                val existing = current[existingIndex]
                current[existingIndex] = existing.copy(quantity = existing.quantity + 1)
            } else {
                current.add(
                    CartItem(
                        id = UUID.randomUUID().toString(),
                        storeInventoryId = inventoryId,
                        name = name,
                        price = price,
                        imageUrl = imageUrl,
                        quantity = 1,
                        storeId = storeId,
                        storeType = storeType
                    )
                )
            }
            cartRepository.saveCartItems(current)
        }
    }

    fun clearCartAndAdd(
        inventoryId: String,
        name: String,
        price: Int,
        imageUrl: String?,
        storeId: String,
        storeType: String
    ) {
        viewModelScope.launch {
            val newItem = CartItem(
                id = UUID.randomUUID().toString(),
                storeInventoryId = inventoryId,
                name = name,
                price = price,
                imageUrl = imageUrl,
                quantity = 1,
                storeId = storeId,
                storeType = storeType
            )
            cartRepository.saveCartItems(listOf(newItem))
        }
    }

    fun increaseQuantity(cartId: String) {
        viewModelScope.launch {
            val updated = _cartItems.value.map { cart ->
                if (cart.id == cartId) cart.copy(quantity = cart.quantity + 1) else cart
            }
            cartRepository.saveCartItems(updated)
        }
    }

    fun decreaseQuantity(cartId: String) {
        viewModelScope.launch {
            val updated = _cartItems.value.mapNotNull { cart ->
                if (cart.id == cartId) {
                    val newQty = cart.quantity - 1
                    if (newQty <= 0) null else cart.copy(quantity = newQty)
                } else {
                    cart
                }
            }
            cartRepository.saveCartItems(updated)
        }
    }
}