package com.example.application.driver.order.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application._core.ui.component.Header
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.GrayMedium
import com.example.application._core.ui.theme.WhiteSoft
import com.example.application.core.R
import com.example.application.driver.dashboard.ui.component.DashboardBottomNavBar

@Composable
fun OrderEmptyScreen(
    onBack: () -> Unit,
    onHomeClick: () -> Unit,
    onOrderHistoryClick: () -> Unit,
    onChatClick: () -> Unit
) {
    Scaffold(
        topBar = {
            Header(title = "Order Status", onBack = onBack)
        },
        bottomBar = {
            DashboardBottomNavBar(
                currentTab = 1,
                onHomeClick = onHomeClick,
                onOrderStatusClick = {},
                onOrderHistoryClick = onOrderHistoryClick,
                onChatClick = onChatClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(WhiteSoft),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_coloredleaf),
                contentDescription = "Empty Order",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "No active order",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = BlackSoft
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Your ongoing order will appear here",
                fontSize = 14.sp,
                color = GrayMedium
            )
        }
    }
}