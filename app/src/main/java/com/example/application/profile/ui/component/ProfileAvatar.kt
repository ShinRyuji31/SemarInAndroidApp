package com.example.application.profile.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.application.R


@Composable
fun ProfileAvatar(
    profilePic: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(110.dp)
            .clip(CircleShape)
            .background(Color.White)
            .padding(4.dp)
    ) {
        if (profilePic != null) {
            AsyncImage(
                model = profilePic,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            Icon(
                painter = painterResource(R.drawable.ic_profile),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                tint = Color.Gray
            )
        }
    }
}