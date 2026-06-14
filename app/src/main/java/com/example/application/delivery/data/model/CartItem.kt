package com.example.application.delivery.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CartItem(
    val id: String,
    val storeInventoryId: String,
    val name: String,
    val price: Int,
    val imageUrl: String?,
    val quantity: Int,
    val storeId: String = "",
    val storeType: String = "FOOD"
)