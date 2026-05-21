package com.example.application.chat.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.chat.data.model.ChatMessage
import com.example.application.global.ui.theme.BlackSoft
import com.example.application.global.ui.theme.BluePrimary
import com.example.application.global.ui.theme.WhiteSoft

@Composable
fun ChatBubble(message: ChatMessage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.isFromUser) Alignment.End else Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                        bottomStart = if (message.isFromUser) 12.dp else 0.dp,
                        bottomEnd = if (message.isFromUser) 0.dp else 12.dp
                    )
                )
                .background(if (message.isFromUser) BluePrimary else Color.White)
                .border(
                    width = if (message.isFromUser) 0.dp else 1.dp,
                    color = if (message.isFromUser) Color.Transparent else Color.LightGray,
                    shape = RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                        bottomStart = if (message.isFromUser) 12.dp else 0.dp,
                        bottomEnd = if (message.isFromUser) 0.dp else 12.dp
                    )
                )
                .padding(12.dp)
        ) {
            Text(
                text = message.text,
                color = if (message.isFromUser) WhiteSoft else BlackSoft,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = message.time,
            fontSize = 10.sp,
            color = Color.Gray
        )
    }
}
