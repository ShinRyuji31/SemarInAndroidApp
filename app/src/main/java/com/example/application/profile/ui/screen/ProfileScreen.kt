package com.example.application.profile.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.application.R
import com.example.application.profile.ui.component.ProfileAlertLogout
import com.example.application.profile.ui.component.ProfileItem
import com.example.application.profile.ui.viewmodel.ProfileUiState
import com.example.application.profile.ui.viewmodel.ProfileViewModel
import com.example.application.dashboard.ui.component.DashboardBottomNavBar
import com.example.application.global.ui.component.BackButton
import com.example.application.global.ui.theme.blueWhiteGradient
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    onBack: () -> Unit,
    onHomeClick: () -> Unit,
    onLogoutSuccess: () -> Unit,
    onOrderStatusClick: () -> Unit = {},
    onOrderHistoryClick: () -> Unit = {},
    viewModel: ProfileViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showLogoutDialog by remember { mutableStateOf(false) }

    LaunchedEffect(uiState) {
        if (uiState is ProfileUiState.Unauthenticated) {
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
                onProfileClick = { }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = blueWhiteGradient())
                .padding(padding)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    BackButton(
                        onClick = onBack,
                        modifier = Modifier
                            .padding(top = 16.dp, start = 16.dp)
                            .align(Alignment.TopStart)
                    )

                    when (val state = uiState) {
                        is ProfileUiState.Loading -> {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = Color.White)
                        }
                        is ProfileUiState.Success -> {
                            val user = state.user
                            ProfileHeaderContent(
                                name = "${user.firstName} ${user.lastName ?: ""}",
                                phone = user.phoneNumber,
                                profilePic = user.profilePic
                            )
                        }
                        is ProfileUiState.ProfileNotFound -> {
                            Column(
                                modifier = Modifier.align(Alignment.Center),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text("Profile record missing", color = Color.White, fontWeight = FontWeight.Bold)
                                Text("Please logout and sign up again", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                                Button(
                                    onClick = { viewModel.logout() },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                                    modifier = Modifier.padding(top = 8.dp)
                                ) {
                                    Text("Logout")
                                }
                            }
                        }
                        is ProfileUiState.Error -> {
                            Text(
                                text = state.message,
                                color = Color.Red,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        else -> {}
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-30).dp)
                        .padding(horizontal = 16.dp)
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(24.dp)
                        )
                        .border(
                            1.dp,
                            Color(0xFFE0E0E0),
                            RoundedCornerShape(24.dp)
                        )
                        .padding(vertical = 16.dp)
                ) {
                    Column {
                        ProfileItem("Full Profile", R.drawable.ic_profile) { }
                        ProfileItem("Order History", R.drawable.ic_history) { onOrderHistoryClick() }
                        ProfileItem("Support & Help", R.drawable.ic_help) { }
                        ProfileItem("Privacy Policy", R.drawable.ic_privacy) { }
                        ProfileItem("Terms of Use", R.drawable.ic_termsofuse) { }
                        ProfileItem("Log Out", R.drawable.ic_logout) { showLogoutDialog = true }
                    }
                }
            }

            if (showLogoutDialog) {
                ProfileAlertLogout(
                    onDismiss = { showLogoutDialog = false },
                    onLogout = {
                        showLogoutDialog = false
                        viewModel.logout()
                    }
                )
            }
        }
    }
}

@Composable
private fun BoxScope.ProfileHeaderContent(
    name: String,
    phone: String,
    profilePic: String?
) {
    Column(
        modifier = Modifier.align(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(4.dp)
        ) {
            if (profilePic != null) {
                AsyncImage(
                    model = profilePic,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    tint = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = name,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = phone,
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Edit Profile",
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier.clickable { /* TODO */ }
        )
    }
}
