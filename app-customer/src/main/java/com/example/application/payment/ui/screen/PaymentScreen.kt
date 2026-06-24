package com.example.application.payment.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.application._core.ui.component.Header
import com.example.application._core.ui.theme.WhiteSoft
import com.example.application.dashboard.ui.component.DashboardBottomNavBar
import com.example.application.payment.ui.state.PaymentState
import com.example.application.payment.ui.component.PaymentQrCard
import com.example.application.payment.ui.component.PaymentStatusCard

@Composable
fun PaymentScreen(
    state: PaymentState,
    orderType: String,
    totalPrice: Double,
    qrRes: Int,
    onBack: () -> Unit,

    onHomeClick: () -> Unit,
    onOrderStatusClick: () -> Unit,
    onOrderHistoryClick: () -> Unit,
    onProfileClick: () -> Unit
) {

    Scaffold(
        topBar = {
            Header(
                title = "Payment",
                onBack = onBack
            )
        },
        bottomBar = {
            DashboardBottomNavBar(
                currentTab = 1,
                onHomeClick = onHomeClick,
                onOrderStatusClick = {},
                onOrderHistoryClick = onOrderHistoryClick,
                onProfileClick = onProfileClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(WhiteSoft)
        ) {
            PaymentStatusCard(state, orderType, totalPrice)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                PaymentQrCard(qrRes = qrRes)
            }

            Spacer(Modifier.width(8.dp))
        }
    }
}