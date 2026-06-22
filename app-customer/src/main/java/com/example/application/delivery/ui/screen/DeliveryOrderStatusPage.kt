package com.example.application.delivery.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.core.R
import com.example.application._core.ui.component.Header
import com.example.application._core.ui.theme.WhiteSoft
import com.example.application.globalorderstatus.ui.viewmodel.OrderStatusGlobalViewmodel
import com.example.application.dashboard.ui.component.DashboardBottomNavBar
import com.example.application.globalorderstatus.ui.component.OrderSummary
import com.example.application.globalorderstatus.ui.component.OrderTimelineItem
import com.example.application.globalorderstatus.ui.screen.OrderStatusDriverDetail
import com.example.application.globalorderstatus.ui.screen.CustomerEmptyOrderScreen
// Import CartItemComponent lo
import com.example.application.delivery.ui.component.cart.CartItemComponent
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
        activeOrder?.orderId?.let {
            viewModel.startListening(it)
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

        val groupedItems = order.orderItems?.mapNotNull { it.product }
            ?.groupBy { it.productName }
            ?.map { (name, products) ->
                val sampleProduct = products.first()
                Triple(
                    sampleProduct,
                    products.size,
                    (sampleProduct.productPrice * products.size)
                )
            } ?: emptyList()

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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(WhiteSoft)
            ) {
                Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
                    OrderTimelineItem(
                        title = "Driver is on the way to the restaurant",
                        time = "",
                        isCompleted = status != "PENDING",
                        isLast = false
                    )

                    OrderTimelineItem(
                        title = "Your order is on the way to you",
                        time = "",
                        isCompleted = status == "DELIVERING",
                        isLast = false
                    )

                    OrderTimelineItem(
                        title = "Delivered to your front door!",
                        time = "",
                        isCompleted = status == "WAITING_PAYMENT",
                        isLast = true
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Items Ordered",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                ) {
                    items(groupedItems) { (product, quantity, totalPrice) ->
                        CartItemComponent(
                            name = product.productName,
                            price = "Rp ${totalPrice.toInt()}",
                            imageUrl = product.productImg,
                            imageRes = R.drawable.dummy,
                            quantity = quantity,
                            showQuantitySelector = false
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(WhiteSoft)
                ) {
                    OrderSummary(
                        subtotal = "Rp ${order.totalPrice ?: 0}",
                        deliveryFee = "Rp 7000",
                        total = "Rp ${(order.totalPrice ?: 0.0) + 7000}"
                    )

                    OrderStatusDriverDetail(
                        driverName = "Kyle",
                        vehicleInfo = "AD 6767 SP (Honda Beat)",
                        onCallClick = onChatClick,
                        onChatClick = onChatClick
                    )
                }
            }
        }
    }
}