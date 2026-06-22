package com.example.application._core.orderhistory.ui.screen

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
import com.example.application._core.ui.component.Header
import com.example.application._core.ui.theme.WhiteSoft
import com.example.application._core.orderhistory.ui.component.OrderHistoryEmptyState
import com.example.application._core.orderhistory.ui.component.OrderHistoryItem
import com.example.application._core.orderhistory.viewmodel.OrderHistoryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrderHistoryScreen(
    onBack: () -> Unit,
    viewModel: OrderHistoryViewModel = koinViewModel(),
    bottomBar: @Composable () -> Unit
) {
    val orders by viewModel.orders.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            Header(
                title = "Order History",
                onBack = onBack
            )
        },
        bottomBar = bottomBar
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(WhiteSoft)
        ) {
            if (!isLoading && orders.isEmpty()) {
                OrderHistoryEmptyState()
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(orders) { order ->
                        OrderHistoryItem(order = order)
                    }
                }
            }
        }
    }
}