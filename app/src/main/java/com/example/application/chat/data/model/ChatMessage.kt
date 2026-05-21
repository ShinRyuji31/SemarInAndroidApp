package com.example.application.chat.data.model

data class ChatMessage(
    val id: String,
    val text: String,
    val time: String,
    val isFromUser: Boolean
)
