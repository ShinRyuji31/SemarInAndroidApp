package com.example.application.profile.ui.component.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.application.R

@Composable
fun ProfileMenuCard(
    onOrderHistoryClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onFullProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(
                Color.White,
                RoundedCornerShape(24.dp)
            )
            .border(
                1.dp,
                Color(0xFFE0E0E0),
                RoundedCornerShape(24.dp)
            )
            .padding(vertical = 16.dp)
    ) {
        ProfileItem(
            "Full Profile",
            R.drawable.ic_profile
        ) {
            onFullProfileClick()
        }

        ProfileItem(
            "Order History",
            R.drawable.ic_history
        ) {
            onOrderHistoryClick()
        }

        ProfileItem(
            "Support & Help",
            R.drawable.ic_help
        ) { }

        ProfileItem(
            "Privacy Policy",
            R.drawable.ic_privacy
        ) { }

        ProfileItem(
            "Terms of Use",
            R.drawable.ic_termsofuse
        ) { }

        ProfileItem(
            "Log Out",
            R.drawable.ic_logout
        ) {
            onLogoutClick()
        }
    }
}