package com.example.application.chat.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.chat.ui.component.ChatBubble
import com.example.application.chat.ui.component.ChatHeader
import com.example.application.chat.ui.component.ChatInput
import com.example.application.chat.ui.viewmodel.ChatViewModel
import com.example.application._core.ui.theme.WhiteSoft
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatWithDriverPage(
    onBack: () -> Unit,
    viewModel: ChatViewModel = koinViewModel()
) {
    val messages by viewModel.messages.collectAsState()

    Scaffold(
        topBar = {
            ChatHeader(
                driverName = "Kyle",
                vehicleInfo = "AD 6767 SP (Honda Beat)",
                onBack = onBack
            )
        },
        bottomBar = {
            ChatInput(onSendMessage = { viewModel.sendMessage(it) })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(WhiteSoft),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "23 April 2026, 12:00 WIB",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            items(messages) { message ->
                ChatBubble(message)
            }
        }
    }
}
