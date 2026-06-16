package com.example.application.anterin.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HistoryLocation(
    val id: Int,
    val address: String
)
