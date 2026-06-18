package com.example.application.driver.dashboard.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.application._core.ui.theme.GrayDark
import com.example.application.core.R

@Composable
fun DashboardMenuSection(
    onFullProfileClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White,
                RoundedCornerShape(16.dp))
            .border(
                1.dp,
                GrayDark,
                RoundedCornerShape(16.dp))
            .padding(vertical = 16.dp)
    ) {
        ProfileItem("Full Profile", R.drawable.ic_profile) { onFullProfileClick() }
        ProfileItem("Support & Help", R.drawable.ic_help) { }
        ProfileItem("Privacy Policy", R.drawable.ic_privacy) { }
        ProfileItem("Terms of Use", R.drawable.ic_termsofuse) { }
        ProfileItem("Log Out", R.drawable.ic_logout) { onLogoutClick() }
    }
}