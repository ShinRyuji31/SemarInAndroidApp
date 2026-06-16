package com.example.application.chat.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.core.R
import com.example.application._core.ui.theme.WhiteSoft

@Composable
fun ChatInput(
    onSendMessage: (String) -> Unit
) {
    var messageText by remember { mutableStateOf("") }

    Surface(
        tonalElevation = 8.dp,
        color = WhiteSoft,
        modifier = Modifier.navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = messageText,
                onValueChange = { messageText = it },
                placeholder = { Text("Message...", fontSize = 14.sp) },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                },
                trailingIcon = {
                    if (messageText.isNotBlank()) {
                        IconButton(onClick = {
                            onSendMessage(messageText)
                            messageText = ""
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_orderstatus), // Using placeholder for send
                                contentDescription = "Send",
                                tint = Color.Blue,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray
                )
            )
        }
    }
}
