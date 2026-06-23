package com.example.application.driver.dashboard.ui.screen

import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.example.application.driver.dashboard.ui.viewmodel.DashboardViewModel
import com.example.application._core.data.location.LocationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DriverDashboardScreen(
    onLogout: () -> Unit,
    onNavigateToOrderDetail: () -> Unit,
    onNavigateToOrderStatus: () -> Unit = {},
    onNavigateToOrderHistory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onNavigateToChat: () -> Unit = {},
    viewModel: DashboardViewModel = koinViewModel(),
    locationViewModel: LocationViewModel = koinViewModel()
){
    val isOnline by viewModel.isOnline.collectAsState()
    val activeOrder by viewModel.activeOrder.collectAsState()
    val locationState by locationViewModel.uiState.collectAsState()
    var showLogoutDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val isGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        if (isGranted) {
            locationViewModel.fetchLocation()
        } else {
            Toast.makeText(context, "Izin lokasi ditolak! Aplikasi butuh GPS.", Toast.LENGTH_LONG).show()
        }
    }

    val dashboardViewModel: DashboardViewModel = koinViewModel()
    val driverName by viewModel.driverName.collectAsState()
    val profilePicUrl by viewModel.profilePicUrl.collectAsState()



    LaunchedEffect(Unit) {
        locationPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
        locationViewModel.fetchLocation()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            bottomBar = {
                DashboardBottomNavBar(
                    currentTab = 0,
                    onHomeClick = { },
                    onOrderStatusClick = onNavigateToOrderStatus,
                    onOrderHistoryClick = onNavigateToOrderHistory,
                    onChatClick = onNavigateToChat,
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
                            driverName = driverName,
                            isOnline = isOnline,
                            profilePicUrl = profilePicUrl,
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
                        isOnline && activeOrder == null -> {
                            val locationAddress = locationState.locationName.ifEmpty { "Mencari lokasi..." }
                            DashboardWaitingOrderCard(locationAddress = locationAddress)
                        }
                        isOnline && activeOrder != null -> {
                            DashboardActiveOrderCard(
                                activeOrder = activeOrder!!,
                                onDetailClick = { onNavigateToOrderDetail() }
                            )
                        }
                    }
                }
            }
        }

        if (showLogoutDialog) {
            DashboardAlertLogout(
                onDismiss = { showLogoutDialog = false },
                onLogout = { showLogoutDialog = false; onLogout() }
            )
        }
    }
}