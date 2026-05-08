package com.example.application.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application.R
import com.example.application.ui.component.dashboard.DashboardBottomNavBar
import com.example.application.ui.component.global.BackButton
import com.example.application.ui.component.profile.ProfileAlertLogut
import com.example.application.ui.component.profile.ProfileItem
import com.example.application.ui.theme.blueWhiteGradient
import com.example.application.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    onBack: () -> Unit,
    onHomeClick: () -> Unit,
    viewModel: ProfileViewModel = viewModel()
) {

    val user by viewModel.user.collectAsState()

    var showLogoutDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        bottomBar = {

            DashboardBottomNavBar(
                currentTab = 3,
                onHomeClick = onHomeClick,
                onProfileClick = { }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = blueWhiteGradient()
                    )
                    .padding(padding)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {

                    BackButton(
                        onClick = onBack,

                        modifier = Modifier
                            .padding(start = 16.dp)
                            .align(Alignment.CenterStart)
                    )

                    user?.let {

                        Column(
                            modifier = Modifier.align(Alignment.Center),

                            horizontalAlignment =
                                Alignment.CenterHorizontally
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(110.dp)
                                    .clip(CircleShape)
                                    .background(Color.White)
                                    .padding(4.dp)
                            ) {

                                Icon(
                                    painter = painterResource(
                                        id = it.profileImage
                                    ),

                                    contentDescription = null,

                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(CircleShape),

                                    tint = Color.Unspecified
                                )
                            }

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            Text(
                                text = it.name,
                                color = Color.White,
                                fontSize = 18.sp
                            )

                            Text(
                                text = it.phoneNumber,

                                color = Color.White.copy(
                                    alpha = 0.8f
                                ),

                                fontSize = 12.sp
                            )

                            Text(
                                text = "Edit Profile",

                                color = Color.White,

                                fontSize = 12.sp,

                                modifier = Modifier.clickable { }
                            )
                        }
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

                        ProfileItem(
                            "Full Profile",
                            R.drawable.ic_profile
                        ) {
                        }

                        ProfileItem(
                            "Order History",
                            R.drawable.ic_history
                        ) {
                        }

                        ProfileItem(
                            "Support & Help",
                            R.drawable.ic_help
                        ) {
                        }

                        ProfileItem(
                            "Privacy Policy",
                            R.drawable.ic_privacy
                        ) {
                        }

                        ProfileItem(
                            "Terms of Use",
                            R.drawable.ic_termsofuse
                        ) {
                        }

                        ProfileItem(
                            "Log Out",
                            R.drawable.ic_logout,
                        ) {

                            showLogoutDialog = true
                        }
                    }
                }
            }

            if (showLogoutDialog) {

                ProfileAlertLogut(
                    onDismiss = {
                        showLogoutDialog = false
                    },

                    onLogout = {
                        showLogoutDialog = false
                    }
                )
            }
        }
    }
}