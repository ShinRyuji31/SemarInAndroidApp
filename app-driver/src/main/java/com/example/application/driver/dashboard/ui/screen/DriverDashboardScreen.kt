package com.example.application.driver.dashboard.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.application._core.ui.theme.GrayMedium
import com.example.application.driver.dashboard.ui.component.statuscard.DashboardActiveOrderCard
import com.example.application.driver.dashboard.ui.component.DashboardAlertLogout
import com.example.application.driver.dashboard.ui.component.DashboardBottomNavBar
import com.example.application.driver.dashboard.ui.component.DashboardDriverStat
import com.example.application.driver.dashboard.ui.component.DashboardHeader
import com.example.application.driver.dashboard.ui.component.DashboardMenuSection
import com.example.application.driver.dashboard.ui.component.statuscard.DashboardOfflineCard
import com.example.application.driver.dashboard.ui.component.DashboardProfileCard
import com.example.application.driver.dashboard.ui.component.statuscard.DashboardWaitingOrderCard
import com.example.application.driver.dashboard.ui.viewmodel.DriverDashboardViewModel
import org.koin.androidx.compose.koinViewModel
import com.example.application.driver.order.ui.screen.DriverIncomingOrderScreen

@Composable
fun DriverDashboardScreen(
    onLogout: () -> Unit,
    onNavigateToOrderDetail: () -> Unit,
    onNavigateToOrderStatus: () -> Unit = {},
    onNavigateToOrderHistory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    viewModel: DriverDashboardViewModel = koinViewModel()
) {
    val isOnline by viewModel.isOnline.collectAsState()
    val incomingOrder by viewModel.incomingOrder.collectAsState()
    val activeOrder by viewModel.activeOrder.collectAsState()
    var showLogoutDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            bottomBar = {
                DashboardBottomNavBar(
                    currentTab = 0,
                    onHomeClick = { },
                    onOrderStatusClick = onNavigateToOrderStatus,
                    onOrderHistoryClick = onNavigateToOrderHistory,
                    onProfileClick = onNavigateToProfile
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(GrayMedium)
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                ) {
                    DashboardHeader()
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        DashboardProfileCard(
                            isOnline = isOnline,
                            profilePicUrl = null,
                            onToggleOnline = { viewModel.setOnlineStatus(it) }
                        )
                        DashboardDriverStat()
                        DashboardMenuSection(
                            onFullProfileClick = onNavigateToProfile,
                            onLogoutClick = { showLogoutDialog = true }
                        )
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                }

                Box(modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp)) {
                    when {
                        !isOnline -> DashboardOfflineCard()
                        isOnline && activeOrder == null -> DashboardWaitingOrderCard()
                        isOnline && activeOrder != null -> {
                            DashboardActiveOrderCard(
                                onDetailClick = { onNavigateToOrderDetail() }
                            )
                        }
                    }
                }
            }
        }

        if (incomingOrder != null) {
            DriverIncomingOrderScreen(
                price = incomingOrder?.totalPrice?.toInt() ?: 0,
                distanceKm = incomingOrder?.distance ?: 0.0,
                onAccept = { incomingOrder?.orderId?.let { viewModel.acceptOrder(it) } },
                onDecline = { incomingOrder?.orderId?.let { viewModel.declineOrder(it) } }
            )
        }

        if (showLogoutDialog) {
            DashboardAlertLogout(
                onDismiss = { showLogoutDialog = false },
                onLogout = { showLogoutDialog = false; onLogout() }
            )
        }
    }
}