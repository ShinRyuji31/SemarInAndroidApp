package com.example.application.chat.data.repository

import com.example.application.chat.data.model.ChatMessage

class ChatRepository {
    fun getInitialMessages(): List<ChatMessage> {
        return listOf(
            ChatMessage("1", "Hello, I'm your driver. I've received your order!", "12:00 WIB", false),
            ChatMessage("2", "I'm on the way to the restaurant now", "12:00 WIB", false),
            ChatMessage("3", "Okay!", "12:01 WIB", true),
            ChatMessage("4", "I'm on the way to your location now. Can you please confirm your exact location?", "12:30 WIB", false),
            ChatMessage("5", "Sure. My building is right next to the convenience store", "12:31 WIB", true)
        )
    }
}
