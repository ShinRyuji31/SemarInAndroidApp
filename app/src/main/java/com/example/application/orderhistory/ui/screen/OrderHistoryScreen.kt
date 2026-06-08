package com.example.application.orderhistory.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.application.dashboard.ui.component.DashboardBottomNavBar
import com.example.application._core.ui.component.Header
import com.example.application._core.ui.theme.WhiteSoft
import com.example.application.orderhistory.ui.component.OrderHistoryItem
import com.example.application.orderhistory.viewmodel.OrderHistoryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrderHistoryScreen(
    onBack: () -> Unit,
    onHomeClick: () -> Unit,
    onOrderStatusClick: () -> Unit,
    onProfileClick: () -> Unit,
    viewModel: OrderHistoryViewModel = koinViewModel(),
) {
    val orders by viewModel.orders.collectAsState()

    Scaffold(
        topBar = {
            Header(
                title = "Order History",
                onBack = onBack
            )
        },
        bottomBar = {
            DashboardBottomNavBar(
                currentTab = 2,
                onHomeClick = onHomeClick,
                onOrderStatusClick = onOrderStatusClick,
                onOrderHistoryClick = {},
                onProfileClick = onProfileClick
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(WhiteSoft),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(orders) { order ->
                OrderHistoryItem(order = order)
            }
        }
    }
}
