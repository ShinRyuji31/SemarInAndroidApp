package com.example.application.anterin.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.anterin.ui.component.destination.AnterinLocationCard
import com.example.application.anterin.ui.component.destination.AnterinProgresLine
import com.example.application.anterin.ui.viewmodel.AnterinViewModel
import com.example.application.dashboard.ui.component.DashboardBottomNavBar
import com.example.application._core.ui.component.Header
import com.example.application._core.ui.component.MapWebView
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.GrayMedium
import com.example.application._core.ui.theme.WhiteSoft
import com.example.application.globalorderstatus.ui.component.OrderStatusDriverDetail
import com.example.application._core.data.location.LocationViewModel
import org.koin.androidx.compose.koinViewModel
import com.example.application.core.R

@Composable
fun AnterinOrderStatusPage(
    onBack: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit,
    onChatClick: () -> Unit = {},
    onOrderHistoryClick: () -> Unit = {},
    viewModel: AnterinViewModel,
    locationViewModel: LocationViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val locationState by locationViewModel.uiState.collectAsState()

    val destination = uiState.destination?.address ?: "Unknown destination"
    val pickup = uiState.pickup?.address ?: "Unknown pickup"

    val userLatLng = if (locationState.latitude != null && locationState.longitude != null) {
        Pair(locationState.latitude!!, locationState.longitude!!)
    } else null

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
                onProfileClick = onProfileClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(WhiteSoft)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Driver is heading your way...",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = BlackSoft
                )
                Spacer(modifier = Modifier.height(12.dp))
                AnterinProgresLine()
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                MapWebView(
                    userLocation = userLatLng,
                    pickupLocation = uiState.pickup,
                    destinationLocation = uiState.destination,
                    routeGeoJson = uiState.route?.geoJson,
                    onMapClick = { _, _ -> },
                    isInteractionEnabled = false,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = WhiteSoft),
                shape = RoundedCornerShape(0.dp)
            ) {
                OrderStatusDriverDetail(
                    driverName = "Kyle",
                    vehicleInfo = "AD 6767 SP (Honda Beat)",
                    onCallClick = onChatClick,
                    onChatClick = onChatClick
                )
            }

            HorizontalDivider(
                color = GrayMedium,
                thickness = 1.dp
            )

            AnterinLocationCard(
                pickup = pickup,
                destination = destination
            )
        }
    }
}
