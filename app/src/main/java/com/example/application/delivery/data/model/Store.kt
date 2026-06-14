package com.example.application.delivery.data.model

data class Store(
    val id: String,
    val name: String,
    val address: String,
    val promo: String = "",
    val rating: Double,
    val imageRes: Int? = null,
    val imageUrl: String? = null,
    val openTime: String,
    val closeTime: String,
    val openDays: String,
    val tags: List<String>,
    val type: StoreType,
    val productNames: List<String> = emptyList()
)