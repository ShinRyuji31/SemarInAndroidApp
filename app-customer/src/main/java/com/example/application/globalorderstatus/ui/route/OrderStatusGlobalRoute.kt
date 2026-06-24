package com.example.application.globalorderstatus.ui.route

import androidx.compose.runtime.*
import com.example.application.delivery.ui.screen.DeliveryOrderStatusPage
import com.example.application.globalorderstatus.ui.screen.CustomerEmptyOrderScreen
import com.example.application.globalorderstatus.ui.viewmodel.OrderStatusGlobalViewmodel
import com.example.application.payment.ui.screen.PaymentScreen
import com.example.application.payment.ui.state.PaymentState
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import com.example.application.R

@Composable
fun OrderStatusGlobalRoute(
    onBack: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit,
    onOrderHistoryClick: () -> Unit,
    globalViewModel: OrderStatusGlobalViewmodel = koinViewModel()
) {
    val activeOrder by globalViewModel.activeOrder.collectAsState()
    val realtimeStatus by globalViewModel.orderStatus.collectAsState()

    val currentStatus = realtimeStatus ?: activeOrder?.orderStatus

    LaunchedEffect(activeOrder?.orderId) {
        activeOrder?.orderId?.let { globalViewModel.startListening(it) }
    }

    LaunchedEffect(currentStatus) {
        if (currentStatus == "WAITING_PAYMENT") {
            delay(1000)
        }
        if (currentStatus == "COMPLETED") {
            delay(3000)
            globalViewModel.checkActiveOrder()
            onHomeClick()
        }
    }

    when {
        currentStatus == "COMPLETED" -> {
            PaymentScreen(
                state = PaymentState.COMPLETED,
                orderType = activeOrder?.orderType ?: "Order",
                totalPrice = activeOrder?.totalPrice ?: 0.0,
                qrRes = R.drawable.dummy_qris,
                onBack = onBack,
                onHomeClick = onHomeClick,
                onOrderStatusClick = {},
                onOrderHistoryClick = onOrderHistoryClick,
                onProfileClick = onProfileClick
            )
        }
        currentStatus == "WAITING_PAYMENT" -> {
            PaymentScreen(
                state = PaymentState.WAITING,
                orderType = activeOrder?.orderType ?: "Order",
                totalPrice = activeOrder?.totalPrice ?: 0.0,
                qrRes = R.drawable.dummy_qris,
                onBack = onBack,
                onHomeClick = onHomeClick,
                onOrderStatusClick = {},
                onOrderHistoryClick = onOrderHistoryClick,
                onProfileClick = onProfileClick
            )
        }
        activeOrder == null || currentStatus in listOf("COMPLETED", "CANCELLED") -> {
            CustomerEmptyOrderScreen(
                onBack = onBack,
                onHomeClick = onHomeClick,
                onProfileClick = onProfileClick,
                onOrderHistoryClick = onOrderHistoryClick
            )
        }
        activeOrder?.isAnterin == true -> {
            com.example.application.anterin.ui.screen.AnterinOrderStatusPage(
                onBack = onBack,
                onHomeClick = onHomeClick,
                onProfileClick = onProfileClick,
                onChatClick = onHomeClick,
                onOrderHistoryClick = onOrderHistoryClick,
                viewModel = koinViewModel() 
            )
        }
        else -> {
            DeliveryOrderStatusPage(
                onBack = onBack,
                onHomeClick = onHomeClick,
                onProfileClick = onProfileClick,
                onChatClick = onHomeClick,
                onOrderHistoryClick = onOrderHistoryClick,
                onOrderStatusClick = {},
                viewModel = globalViewModel
            )
        }
    }
}