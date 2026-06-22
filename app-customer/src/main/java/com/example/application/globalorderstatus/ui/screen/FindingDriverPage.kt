package com.example.application.globalorderstatus.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application._core.ui.component.Header
import com.example.application._core.ui.theme.BluePrimary
import com.example.application._core.ui.theme.WhiteSoft
import kotlinx.coroutines.delay
import com.example.application.R
import com.example.application.globalorderstatus.ui.viewmodel.OrderStatusGlobalViewmodel
import org.koin.androidx.compose.koinViewModel

enum class DriverState {
    FINDING,
    FOUND
}

@Composable
fun FindingDriverPage(
    orderId: String,
    serviceName: String = "Anter-In",
    onBack: () -> Unit,
    onFinished: () -> Unit,
    viewModel: OrderStatusGlobalViewmodel = koinViewModel()
) {
    var currentState by remember { mutableStateOf(DriverState.FINDING) }

    val status by viewModel.orderStatus.collectAsState()

    LaunchedEffect(orderId) {
        viewModel.startListening(orderId = orderId)
    }

    LaunchedEffect(status) {
        when (status) {
            "ACCEPTED" -> {
                currentState = DriverState.FOUND
                delay(1500)
                onFinished()
            }
        }
    }

    val title: String
    val subtitle: String
    val imageRes: Int

    when (currentState) {
        DriverState.FINDING -> {
            title = "Hold on tight."
            subtitle = "We are finding a driver for you"
            imageRes = R.drawable.logo_coloredbike
        }

        DriverState.FOUND -> {
            title = "Hooray!"
            subtitle = "We have found you a driver"
            imageRes = R.drawable.logo_coloredhelmet
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BluePrimary)
    ) {

        Header(
            title = serviceName,
            onBack = onBack
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = WhiteSoft
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = WhiteSoft
            )

            Spacer(modifier = Modifier.height(32.dp))

            Icon(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(120.dp),
                tint = Color.Unspecified
            )
        }
    }
}