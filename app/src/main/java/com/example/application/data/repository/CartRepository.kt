package com.example.application.data.repository

import com.example.application.data.model.CartItem

class CartRepository {

    fun getCartItems(): List<CartItem> {
        return listOf(

            CartItem(
                id = "cart_1",
                storeInventoryId = "F1016",
                quantity = 1
            ),

            CartItem(
                id = "cart_2",
                storeInventoryId = "F1017",
                quantity = 2
            ),

            CartItem(
                id = "cart_3",
                storeInventoryId = "F1010",
                quantity = 1
            )
        )
    }
}