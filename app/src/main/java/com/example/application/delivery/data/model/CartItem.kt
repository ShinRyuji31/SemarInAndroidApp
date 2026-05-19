package com.example.application.delivery.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CartItem(
    val id: String,
    val storeInventoryId: String,
    val quantity: Int
)