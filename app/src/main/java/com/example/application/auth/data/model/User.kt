package com.example.application.auth.data.model

data class User(
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val address: String,
    val profileImage: Int
)