package com.example.application.delivery.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.delivery.data.local.CartDataStore
import com.example.application.delivery.data.model.CartItem
import com.example.application.delivery.data.model.StoreInventory
import com.example.application.delivery.data.repository.CartRepository
import com.example.application.delivery.data.repository.StoreRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID

class CartViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val cartRepository = CartRepository(
        CartDataStore(application)
    )

    private val storeRepository = StoreRepository()

    private val inventories = storeRepository.getStoreInventory()

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())

    val cartItems = _cartItems.asStateFlow()

    init {

        viewModelScope.launch {

            cartRepository.getCartItems().collect {

                _cartItems.value = it
            }
        }
    }

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

    val total = combine(
        subtotal,
        deliveryFee
    ) { sub, fee ->

        sub + fee
    }

    // =========================
    // ADD TO CART
    // =========================

    fun addToCart(
        inventoryId: String
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
                        quantity = 1
                    )
                )
            }

            cartRepository.saveCartItems(current)
        }
    }

    // =========================
    // QUANTITY
    // =========================

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
                        cart.copy(quantity = newQty)
                    }

                } else {
                    cart
                }
            }

            cartRepository.saveCartItems(updated)
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