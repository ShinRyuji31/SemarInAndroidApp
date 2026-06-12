package com.example.application.dashboard.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.R
import com.example.application._core.ui.component.ButtonIcon
import com.example.application._core.ui.theme.BluePrimary
import com.example.application._core.ui.theme.WhiteSoft
import com.example.application._core.ui.theme.blueWhiteGradient

@Composable
fun DashboardAllCategorySheet(
    onClose: () -> Unit,
    onAnterClick: () -> Unit,
    onJajanClick: () -> Unit,
    onJastipinClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                blueWhiteGradient(),
                RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
            .padding(20.dp)
    ) {

        Text(
            text = "All Categories",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = WhiteSoft
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                ButtonIcon(
                    title = "Anter-In",
                    icon = R.drawable.ic_bike,
                    modifier = Modifier.weight(1f),
                    containerColor = WhiteSoft,
                    contentColor = BluePrimary,
                    onClick = onAnterClick
                )

                ButtonIcon(
                    title = "Jajan-In",
                    icon = R.drawable.ic_cutlery,
                    modifier = Modifier.weight(1f),
                    containerColor = WhiteSoft,
                    contentColor = BluePrimary,
                    onClick = onJajanClick
                )

                ButtonIcon(
                    title = "Titip-In",
                    icon = R.drawable.ic_bag,
                    modifier = Modifier.weight(1f),
                    containerColor = WhiteSoft,
                    contentColor = BluePrimary
                ) {}

                ButtonIcon(
                    title = "Survei-In",
                    icon = R.drawable.ic_star,
                    modifier = Modifier.weight(1f),
                    containerColor = WhiteSoft,
                    contentColor = BluePrimary
                ) {}
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                ButtonIcon(
                    title = "Bersih-In",
                    icon = R.drawable.ic_star,
                    modifier = Modifier.weight(1f),
                    containerColor = WhiteSoft,
                    contentColor = BluePrimary
                ) {}

                ButtonIcon(
                    title = "Cetak-In",
                    icon = R.drawable.ic_star,
                    modifier = Modifier.weight(1f),
                    containerColor = WhiteSoft,
                    contentColor = BluePrimary
                ) {}

                Spacer(Modifier.weight(2f))
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )
    }
}