package com.example.application.delivery.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CartItem(
    val id: String,
    val storeInventoryId: String,

    val name: String = "",
    val price: Int = 0,
    val imageUrl: String? = null,

    val quantity: Int
)