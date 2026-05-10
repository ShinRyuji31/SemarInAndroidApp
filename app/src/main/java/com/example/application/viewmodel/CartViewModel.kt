package com.example.application.viewmodel

import androidx.lifecycle.ViewModel
import com.example.application.data.model.StoreInventory
import com.example.application.data.repository.CartRepository
import com.example.application.data.repository.StoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class CartViewModel : ViewModel() {

    private val cartRepository = CartRepository()
    private val storeRepository = StoreRepository()

    private val inventories = storeRepository.getStoreInventory()

    private val _cartItems = MutableStateFlow(
        cartRepository.getCartItems()
    )

    val cartItems = _cartItems.asStateFlow()

    // =========================
    // PRICE STATES
    // =========================

    val subtotal = cartItems.map { carts ->

        carts.sumOf { cart ->

            val inventory = getInventoryById(
                cart.storeInventoryId
            )

            (inventory?.price ?: 0) * cart.quantity
        }
    }

    val deliveryFee = MutableStateFlow(7000)

    val total = subtotal.map {
        it + deliveryFee.value
    }

    // =========================
    // QUANTITY
    // =========================

    fun increaseQuantity(cartId: String) {

        _cartItems.value = _cartItems.value.map { cart ->

            if (cart.id == cartId) {
                cart.copy(
                    quantity = cart.quantity + 1
                )
            } else {
                cart
            }
        }
    }

    fun decreaseQuantity(cartId: String) {

        _cartItems.value = _cartItems.value.map { cart ->

            if (cart.id == cartId && cart.quantity > 1) {
                cart.copy(
                    quantity = cart.quantity - 1
                )
            } else {
                cart
            }
        }
    }

    // =========================
    // INVENTORY
    // =========================

    fun getInventoryById(
        inventoryId: String
    ): StoreInventory? {

        return inventories.find {
            it.id == inventoryId
        }
    }
}