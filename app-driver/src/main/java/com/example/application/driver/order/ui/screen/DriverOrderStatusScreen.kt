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
import com.example.application.order.data.dto.ActiveOrderDto
import com.example.application.driver.order.ui.component.OrderActionContent // 🚀 Import komponen baru
import com.example.application.driver.order.ui.component.OrderEmptyScreen
import com.example.application.driver.order.ui.component.PaymentCompletedPopup

@Composable
fun DriverOrderStatusScreen(
    activeOrder: ActiveOrderDto?,
    onBack: () -> Unit,
    onHomeClick: () -> Unit,
    onOrderHistoryClick: () -> Unit,
    onChatClick: () -> Unit,
    onConfirmPickup: () -> Unit,
    onArrivedAtDestination: () -> Unit,
    onPaymentCompleted: () -> Unit,
    onNavigationClick: () -> Unit = {}
) {
    if (activeOrder == null) {
        OrderEmptyScreen(
            onBack = onBack,
            onHomeClick = onHomeClick,
            onOrderHistoryClick = onOrderHistoryClick,
            onChatClick = onChatClick
        )
    } else {
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
                    onChatClick = onChatClick
                )
            }
        ) { inner ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(inner)
                    .background(GrayMedium)
            ) {
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
                        OrderActionContent(
                            title = if (activeOrder.isAnterin == true) "Picking Up Passenger" else "Picking Up Food",
                            locationName = if (activeOrder.isAnterin == true) "Pickup Point" else activeOrder.storeName,
                            address = activeOrder.pickupAddress,
                            buttonText = "Confirm Pick Up",
                            onButtonClick = onConfirmPickup
                        )
                    }
                    "DELIVERING" -> {
                        OrderActionContent(
                            title = if (activeOrder.isAnterin == true) "Dropping Off" else "Delivering Food",
                            locationName = "Customer Destination",
                            address = activeOrder.destinationAddress,
                            buttonText = "Arrived at destination",
                            onButtonClick = onArrivedAtDestination
                        )
                    }
                    "WAITING_PAYMENT" -> {
                        OrderActionContent(
                            title = if (activeOrder.isAnterin == true) "Dropping Off" else "Delivering Food",
                            locationName = "Customer Destination",
                            address = activeOrder.destinationAddress,
                            buttonText = "Arrived at destination",
                            onButtonClick = {}
                        )
                        PaymentCompletedPopup(
                            totalPrice = activeOrder.totalPrice,
                            onConfirm = onPaymentCompleted
                        )
                    }
                    else -> {
                        OrderActionContent(
                            title = "Picking Up",
                            locationName = activeOrder.storeName,
                            address = activeOrder.pickupAddress,
                            buttonText = "Confirm",
                            onButtonClick = onConfirmPickup
                        )
                    }
                }
            }
        }
    }
}