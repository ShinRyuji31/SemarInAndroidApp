package com.example.application.delivery.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.R
import com.example.application.delivery.ui.component.orderstatus.OrderSummary
import com.example.application.delivery.ui.viewmodel.CartViewModel
import com.example.application.dashboard.ui.component.DashboardBottomNavBar
import com.example.application.delivery.ui.component.cart.CartItemComponent
import com.example.application.delivery.ui.component.orderstatus.OrderTimelineItem
import com.example.application._core.ui.component.Header
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.GrayMedium
import com.example.application._core.ui.theme.WhiteSoft
import com.example.application._core.util.toRupiah
import com.example.application._core.ui.screen.OrderStatusDriverDetail
import org.koin.androidx.compose.koinViewModel

@Composable
fun JajaninOrderStatusPage(
    onBack: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit,
    onChatClick: () -> Unit,
    onOrderHistoryClick: () -> Unit = {},
    viewModel: CartViewModel = koinViewModel()
) {

    val cartItems by viewModel.cartItems.collectAsState()

    val subtotal by viewModel.subtotal.collectAsState(0)

    val deliveryFee by viewModel.deliveryFee.collectAsState()

    val total by viewModel.total.collectAsState(0)

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
                onOrderHistoryClick = onOrderHistoryClick,
                onProfileClick = onProfileClick
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(WhiteSoft)
        ) {

            item {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    OrderTimelineItem(
                        title =
                            "Driver is on the way to the restaurant",

                        time =
                            "23 April 2026, 12:00 WIB",

                        isCompleted = true,

                        isLast = false
                    )

                    OrderTimelineItem(
                        title =
                            "Your order is on the way to you",

                        time =
                            "23 April 2026, 12:30 WIB",

                        isCompleted = true,

                        isLast = false
                    )

                    OrderTimelineItem(
                        title =
                            "Delivered to your front door!",

                        time = "",

                        isCompleted = false,

                        isLast = true
                    )
                }
            }

            item {

                Text(
                    text = "Order Details",

                    fontSize = 18.sp,

                    fontWeight = FontWeight.Bold,

                    color = BlackSoft,

                    modifier = Modifier.padding(16.dp)
                )
            }

            items(cartItems) { cart ->

                CartItemComponent(
                    name = cart.name,
                    price = cart.price.toRupiah(),
                    imageUrl = cart.imageUrl,
                    imageRes = R.drawable.dummy,
                    quantity = cart.quantity,
                    showQuantitySelector = false
                )
            }

            item {

                OrderSummary(
                    subtotal =
                        subtotal.toRupiah(),

                    deliveryFee =
                        deliveryFee.toRupiah(),

                    total =
                        total.toRupiah()
                )

                HorizontalDivider(
                    color = GrayMedium.copy(alpha = 0.5f),
                    thickness = 8.dp
                )
            }

            item {

                OrderStatusDriverDetail(
                    driverName = "Kyle",

                    vehicleInfo =
                        "AD 6767 SP (Honda Beat)",

                    onCallClick = onChatClick,

                    onChatClick = onChatClick
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )
            }
        }
    }
}