package com.example.application.delivery.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.delivery.data.model.CartItem
import com.example.application.delivery.data.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.UUID

class CartViewModel(
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())

    val cartItems = _cartItems.asStateFlow()

    init {
        viewModelScope.launch {
            cartRepository.getCartItems().collect {
                _cartItems.value = it
            }
        }
    }

    val subtotal = cartItems.map { carts ->
        carts.sumOf { cart ->
            cart.price * cart.quantity
        }
    }

    val deliveryFee = MutableStateFlow(7000)

    val total = combine(
        subtotal,
        deliveryFee
    ) { sub, fee ->
        sub + fee
    }

    fun addToCart(
        inventoryId: String,
        name: String,
        price: Int,
        imageUrl: String?
    ) {

        viewModelScope.launch {

            val current = _cartItems.value.toMutableList()

            val existingIndex = current.indexOfFirst {
                it.storeInventoryId == inventoryId
            }

            if (existingIndex != -1) {

                val existing = current[existingIndex]

                current[existingIndex] = existing.copy(
                    quantity = existing.quantity + 1
                )

            } else {

                current.add(
                    CartItem(
                        id = UUID.randomUUID().toString(),
                        storeInventoryId = inventoryId,
                        name = name,
                        price = price,
                        imageUrl = imageUrl,
                        quantity = 1
                    )
                )
            }

            cartRepository.saveCartItems(current)
        }
    }

    fun increaseQuantity(cartId: String) {

        viewModelScope.launch {

            val updated = _cartItems.value.map { cart ->

                if (cart.id == cartId) {

                    cart.copy(
                        quantity = cart.quantity + 1
                    )

                } else {
                    cart
                }
            }

            cartRepository.saveCartItems(updated)
        }
    }

    fun decreaseQuantity(cartId: String) {

        viewModelScope.launch {

            val updated = _cartItems.value.mapNotNull { cart ->

                if (cart.id == cartId) {

                    val newQty = cart.quantity - 1

                    if (newQty <= 0) {
                        null
                    } else {
                        cart.copy(
                            quantity = newQty
                        )
                    }

                } else {
                    cart
                }
            }

            cartRepository.saveCartItems(updated)
        }
    }
}