package com.example.application.driver.order.ui.overlay

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.application.driver._core.ui.viewmodel.CoreViewmodel
import com.example.application.driver.order.ui.component.OrderIncomingNotification

@Composable
fun GlobalOrderNotification(
    viewModel: CoreViewmodel
) {

    val incomingOrder by viewModel.incomingOrder.collectAsState()

    incomingOrder?.let { order ->
        OrderIncomingNotification(
            order = order,
            onAccept = { viewModel.acceptOrder(order.orderId) },
            onDecline = { viewModel.declineOrder(order.orderId) }
        )
    }

    LaunchedEffect(incomingOrder) {
        Log.d(
            "ORDER_DEBUG",
            incomingOrder.toString()
        )
    }
}