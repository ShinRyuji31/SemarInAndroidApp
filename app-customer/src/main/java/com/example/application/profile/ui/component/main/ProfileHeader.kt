package com.example.application.profile.ui.component.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.application.profile.ui.component.ProfileAvatar

@Composable
fun ProfileHeader(
    name: String,
    phone: String,
    profilePic: String?,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileAvatar(profilePic)

        Spacer(modifier = Modifier.height(12.dp))

        ProfileInfo(
            name = name,
            phone = phone
        )
    }
}
