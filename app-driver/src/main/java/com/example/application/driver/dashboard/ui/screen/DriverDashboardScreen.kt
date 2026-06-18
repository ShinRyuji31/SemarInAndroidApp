package com.example.application.driver.dashboard.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.application.driver.dashboard.ui.component.DashboardActiveOrderCard
import com.example.application.driver.dashboard.ui.component.DashboardHeader
import com.example.application.driver.dashboard.ui.component.DashboardProfileCard
import com.example.application.driver.dashboard.ui.component.DashboardMenuSection
import com.example.application.driver.dashboard.ui.component.DashboardDriverStat
import com.example.application.driver.dashboard.ui.component.DashboardAlertLogout
import com.example.application.driver.dashboard.ui.viewmodel.DriverDashboardViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DriverDashboardScreen(
    onLogout: () -> Unit,
    onNavigateToOrderDetail: () -> Unit,
    viewModel: DriverDashboardViewModel = koinViewModel()
) {
    val isOnline by viewModel.isOnline.collectAsState()
    var showLogoutDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayMedium)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            DashboardHeader()

            Spacer(modifier = Modifier.width(16.dp))


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){

                DashboardProfileCard(
                    isOnline = isOnline,
                    profilePicUrl = null,
                    onToggleOnline = { viewModel.setOnlineStatus(it) }
                )

                DashboardDriverStat()

                DashboardMenuSection(
                    onFullProfileClick = { /* TODO Pindah Profile */ },
                    onOrderHistoryClick = { /* TODO Pindah History */ },
                    onLogoutClick = { showLogoutDialog = true }
                )

                Spacer(modifier = Modifier.height(100.dp))
            }

        }

        if (isOnline) {
            DashboardActiveOrderCard(
                onDetailClick = { onNavigateToOrderDetail() },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .navigationBarsPadding()
            )
        }

        if (showLogoutDialog) {
            DashboardAlertLogout(
                onDismiss = { showLogoutDialog = false },
                onLogout = {
                    showLogoutDialog = false
                    onLogout()
                }
            )
        }
    }
}