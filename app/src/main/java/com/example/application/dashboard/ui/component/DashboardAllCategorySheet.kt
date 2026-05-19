package com.example.application.dashboard.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.R
import com.example.application.global.ui.component.ButtonIcon
import com.example.application.global.ui.theme.BlackSoft

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
            .padding(16.dp)
    ) {

        Text(
            text = "All Categories",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = BlackSoft
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

                ButtonIcon("Anter-In", R.drawable.ic_bike, onClick = onAnterClick)

                ButtonIcon("Jajan-In", R.drawable.ic_cutlery, onClick = onJajanClick)

                ButtonIcon("Titip-In", R.drawable.ic_bag) {}

                ButtonIcon("Survei-In", R.drawable.ic_star) {}
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

                ButtonIcon("Bersih-In", R.drawable.ic_star) {}

                ButtonIcon("Cetak-In", R.drawable.ic_star) {}
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }

}