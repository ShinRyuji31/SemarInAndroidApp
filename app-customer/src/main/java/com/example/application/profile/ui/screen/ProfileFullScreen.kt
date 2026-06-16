package com.example.application.profile.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application._core.ui.component.ButtonBlue
import com.example.application._core.ui.component.Header
import com.example.application._core.ui.theme.blueWhiteGradient
import com.example.application.auth.ui.component.AuthInputField
import com.example.application.dashboard.ui.component.DashboardBottomNavBar
import com.example.application.profile.ui.component.ProfileAvatar
import com.example.application.profile.ui.viewmodel.ProfileUiiState
import com.example.application.profile.ui.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileFullScreen(
    onBack: () -> Unit,
    onHomeClick: () -> Unit,
    onOrderStatusClick: () -> Unit,
    onOrderHistoryClick: () -> Unit,
    viewModel: ProfileViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    if (uiState !is ProfileUiiState.ProfileUiState.Success) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    val user =
        (uiState as ProfileUiiState.ProfileUiState.Success).user

    var username by remember {
        mutableStateOf(user.username)
    }

    var firstName by remember {
        mutableStateOf(user.firstName)
    }

    var phone by remember {
        mutableStateOf(user.phoneNumber)
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0),

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
                .background(blueWhiteGradient())
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Header(
                    title = "Profile",
                    onBack = onBack
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 55.dp)
                            .background(
                                Color.White,
                                RoundedCornerShape(28.dp)
                            )
                            .border(
                                1.dp,
                                Color(0xFFE0E0E0),
                                RoundedCornerShape(28.dp)
                            )
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    start = 20.dp,
                                    end = 20.dp,
                                    top = 70.dp,
                                    bottom = 16.dp
                                ),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {

                            AuthInputField(
                                label = "Username",
                                value = username,
                                onValueChange = {
                                    username = it
                                },
                                placeholder = username
                            )

                            AuthInputField(
                                label = "Full Name",
                                value = firstName,
                                onValueChange = {
                                    firstName = it
                                }
                            )

                            AuthInputField(
                                label = "Phone Number",
                                value = phone,
                                onValueChange = {
                                    phone = it
                                }
                            )

                            AuthInputField(
                                label = "Email",
                                value = user.email,
                                onValueChange = {},
                                enabled = false
                            )

                            Text(
                                text = "Must be an SSO email",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )

                            Spacer(
                                modifier = Modifier.height(16.dp)
                            )

                            ButtonBlue(
                                text = "Save Changes",
                                onClick = {

                                    viewModel.updateProfile(
                                        username = username,
                                        firstName = firstName,
                                        phoneNumber = phone
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                            )
                        }
                    }

                    ProfileAvatar(
                        profilePic = user.profilePic,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .size(110.dp)
                    )
                }
            }
        }
    }
}