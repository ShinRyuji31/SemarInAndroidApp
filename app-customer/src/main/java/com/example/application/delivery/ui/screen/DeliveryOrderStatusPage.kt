package com.example.application.delivery.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.application.delivery.ui.component.orderstatus.*
import com.example.application._core.ui.component.Header
import com.example.application._core.ui.theme.WhiteSoft
import com.example.application.delivery.ui.component.cart.CartItemComponent
import com.example.application.globalorderstatus.ui.viewmodel.OrderStatusGlobalViewmodel
import com.example.application.dashboard.ui.component.DashboardBottomNavBar
import com.example.application.globalorderstatus.ui.screen.OrderStatusDriverDetail
import com.example.application.globalorderstatus.ui.screen.CustomerEmptyOrderScreen // <-- Jangan lupa import ini
import org.koin.androidx.compose.koinViewModel

@Composable
fun DeliveryOrderStatusPage(
    onBack: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit,
    onChatClick: () -> Unit,
    onOrderHistoryClick: () -> Unit = {},
    onOrderStatusClick: () -> Unit = {},
    viewModel: OrderStatusGlobalViewmodel = koinViewModel()
) {

    val activeOrder by viewModel.activeOrder.collectAsState()
    val realtimeStatus by viewModel.orderStatus.collectAsState()

    val status = realtimeStatus ?: activeOrder?.orderStatus

    LaunchedEffect(activeOrder?.orderId) {
        activeOrder?.orderId?.let { id ->
            viewModel.startListening(id)
        }
    }

    if (activeOrder == null || status == "COMPLETED") {

        CustomerEmptyOrderScreen(
            onBack = onBack,
            onHomeClick = onHomeClick,
            onOrderHistoryClick = onOrderHistoryClick,
            onProfileClick = onProfileClick
        )

    } else {

        val order = activeOrder!!

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
                    onOrderStatusClick = onOrderStatusClick,
                    onOrderHistoryClick = onOrderHistoryClick,
                    onProfileClick = onProfileClick
                )
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(WhiteSoft)
            ) {

                item {
                    Column(modifier = Modifier.padding(16.dp)) {

                        OrderTimelineItem(
                            title = "Driver is on the way to the restaurant",
                            time = "",
                            isCompleted = status != "PENDING",
                            isLast = false
                        )

                        OrderTimelineItem(
                            title = "Your order is on the way to you",
                            time = "",
                            isCompleted = status == "DELIVERING" || status == "WAITING_PAYMENT",
                            isLast = false
                        )

                        OrderTimelineItem(
                            title = "Delivered to your front door!",
                            time = "",
                            isCompleted = status == "WAITING_PAYMENT",
                            isLast = true
                        )
                    }
                }

                item {
                    OrderSummary(
                        subtotal = "Rp ${order.totalPrice}",
                        deliveryFee = "Rp 7000",
                        total = "Rp ${(order.totalPrice ?: 0.0) + 7000}"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Store: ${order.storeName ?: "Unknown Store"}"
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        order.orderItems?.forEach { item ->
                            CartItemComponent(
                                name = item.product?.store?.storeName ?: "Item",
                                price = "From store",
                                imageUrl = null,
                                imageRes = com.example.application.core.R.drawable.dummy,
                                quantity = 1,
                                showQuantitySelector = false
                            )
                        }
                    }
                }

                item {
                    OrderStatusDriverDetail(
                        driverName = "Kyle",
                        vehicleInfo = "AD 6767 SP (Honda Beat)",
                        onCallClick = onChatClick,
                        onChatClick = onChatClick
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}