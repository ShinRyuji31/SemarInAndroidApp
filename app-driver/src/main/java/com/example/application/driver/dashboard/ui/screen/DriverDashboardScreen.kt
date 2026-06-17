package com.example.application.driver.dashboard.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.application.driver.dashboard.ui.component.DriverHeader
import com.example.application.driver.dashboard.ui.component.DriverProfileCard
import com.example.application.driver.dashboard.ui.component.DriverStatsSection
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.collectAsState
import com.example.application.driver.dashboard.ui.viewmodel.DriverDashboardViewModel

@Composable
fun DriverDashboardScreen(
    onLogout: () -> Unit,
    viewModel: DriverDashboardViewModel = koinViewModel()
) {
    val isOnline by viewModel.isOnline.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF5F5F5))
    ) {
        DriverHeader(onLogout = onLogout)

        DriverProfileCard(
            isOnline = isOnline,
            onToggleOnline = { viewModel.setOnlineStatus(it) }
        )

        DriverStatsSection()

        Spacer(modifier = Modifier.weight(1f))

        val statusText = if (isOnline) "Menunggu Pesanan Masuk..." else "Anda sedang Offline"
        Text(
            text = statusText,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}