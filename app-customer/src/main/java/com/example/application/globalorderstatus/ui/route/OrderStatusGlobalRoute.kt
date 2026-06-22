package com.example.application.globalorderstatus.ui.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.application.globalorderstatus.ui.screen.CustomerEmptyOrderScreen
import com.example.application.globalorderstatus.ui.viewmodel.OrderStatusGlobalViewmodel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrderStatusGlobalRoute(
    onBack: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit,
    onOrderHistoryClick: () -> Unit,
    globalViewModel: OrderStatusGlobalViewmodel = koinViewModel()
) {
    val activeOrder by globalViewModel.activeOrder.collectAsState()

    when {
        activeOrder == null || activeOrder?.orderStatus in listOf("COMPLETED", "CANCELLED") -> {
            CustomerEmptyOrderScreen(
                onBack = onBack,
                onHomeClick = onHomeClick,
                onProfileClick = onProfileClick,
                onOrderHistoryClick = onOrderHistoryClick
            )
        }
        activeOrder?.isAnterin == true -> {
        }
        else -> {
        }
    }
}