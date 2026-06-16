package com.example.application.profile.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.application._core.ui.component.Header
import com.example.application._core.ui.theme.blueWhiteGradient
import com.example.application.dashboard.ui.component.DashboardBottomNavBar
import com.example.application.profile.ui.component.ProfileAlertLogout
import com.example.application.profile.ui.component.main.ProfileHeader
import com.example.application.profile.ui.component.main.ProfileMenuCard
import com.example.application.profile.ui.viewmodel.ProfileUiiState
import com.example.application.profile.ui.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileMainScreen(
    onBack: () -> Unit,
    onHomeClick: () -> Unit,
    onLogoutSuccess: () -> Unit,
    onOrderStatusClick: () -> Unit = {},
    onOrderHistoryClick: () -> Unit = {},
    onFullProfileClick: () -> Unit,
    viewModel: ProfileViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showLogoutDialog by remember { mutableStateOf(false) }

    LaunchedEffect(uiState) {
        if (uiState is ProfileUiiState.ProfileUiState.Unauthenticated) {
            onLogoutSuccess()
        }
    }

    Scaffold(
        bottomBar = {
            DashboardBottomNavBar(
                currentTab = 3,
                onHomeClick = onHomeClick,
                onOrderStatusClick = onOrderStatusClick,
                onOrderHistoryClick = onOrderHistoryClick,
                onProfileClick = {}
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = blueWhiteGradient())
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {

                    Header(
                        title = "Profile",
                        onBack = onBack
                    )

                    when (val state = uiState) {

                        is ProfileUiiState.ProfileUiState.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center),
                                color = Color.White
                            )
                        }

                        is ProfileUiiState.ProfileUiState.Success -> {
                            val user = state.user

                            Spacer(modifier = Modifier.height(16.dp))

                            Box(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .offset(y = 48.dp)
                            ) {
                                ProfileHeader(
                                    name = "${user.firstName} ${user.lastName ?: ""}",
                                    phone = user.phoneNumber,
                                    profilePic = user.profilePic
                                )
                            }
                        }

                        is ProfileUiiState.ProfileUiState.Error -> {
                        }

                        else -> Unit
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-30).dp)
                        .padding(horizontal = 32.dp)
                ) {
                    ProfileMenuCard(
                        onOrderHistoryClick = onOrderHistoryClick,
                        onLogoutClick = {
                            showLogoutDialog = true
                        },
                        onFullProfileClick = onFullProfileClick
                    )
                }
            }

            if (showLogoutDialog) {
                ProfileAlertLogout(
                    onDismiss = {
                        showLogoutDialog = false
                    },
                    onLogout = {
                        showLogoutDialog = false
                        viewModel.logout()
                    }
                )
            }
        }
    }
}