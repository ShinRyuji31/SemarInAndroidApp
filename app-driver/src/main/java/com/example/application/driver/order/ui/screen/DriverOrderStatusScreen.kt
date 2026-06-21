package com.example.application.driver.order.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.application._core.ui.component.Header
import com.example.application._core.ui.theme.GrayMedium
import com.example.application.driver.dashboard.ui.component.DashboardBottomNavBar
import com.example.application.driver.order.data.dto.ActiveOrderDto
import com.example.application.driver.order.ui.component.OrderDeliveryContent
import com.example.application.driver.order.ui.component.OrderEmptyScreen
import com.example.application.driver.order.ui.component.OrderPickupContent
import com.example.application.driver.order.ui.component.PaymentCompletedPopup

@Composable
fun DriverOrderStatusScreen(
    activeOrder: ActiveOrderDto?,
    onBack: () -> Unit,
    onHomeClick: () -> Unit,
    onOrderHistoryClick: () -> Unit,
    onProfileClick: () -> Unit,
    onConfirmPickup: () -> Unit,
    onArrivedAtDestination: () -> Unit,
    onPaymentCompleted: () -> Unit,
    onNavigationClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            Header(
                title = "Order Status",
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
    ) { inner ->
        if (activeOrder == null) {
            OrderEmptyScreen(modifier = Modifier.padding(inner))
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(inner)
                    .background(GrayMedium)
            ) {
                // Dummy map area
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(Color(0xFFE7E7E7))
                ) {
                    Text(
                        text = "Dummy Map",
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(24.dp)
                            .background(Color.Red, CircleShape)
                    )
                }

                when (activeOrder.orderStatus) {
                    "PICKING_UP" -> {
                        OrderPickupContent(
                            order = activeOrder,
                            onConfirm = onConfirmPickup
                        )
                    }
                    "DELIVERING" -> {
                        OrderDeliveryContent(
                            order = activeOrder,
                            onArrived = onArrivedAtDestination
                        )
                    }
                    "WAITING_PAYMENT" -> {
                        OrderDeliveryContent(
                            order = activeOrder,
                            onArrived = {} // disabled
                        )
                        PaymentCompletedPopup(
                            totalPrice = activeOrder.totalPrice,
                            onConfirm = onPaymentCompleted
                        )
                    }
                    else -> {
                        OrderPickupContent(
                            order = activeOrder,
                            onConfirm = onConfirmPickup
                        )
                    }
                }
            }
        }
    }
}