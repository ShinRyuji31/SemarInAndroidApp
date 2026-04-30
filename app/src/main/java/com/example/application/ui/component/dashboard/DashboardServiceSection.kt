package com.example.application.ui.component.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.application.R
import com.example.application.ui.component.global.ButtonIcon

@Composable
fun DashboardServiceSection(
    onAnjeminClick: () -> Unit,
    onJajaninClick: () -> Unit,
    onAllClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        ButtonIcon("Antar-in", R.drawable.ic_bike) {
            onAnjeminClick()
        }

        ButtonIcon("Jajan-in", R.drawable.ic_cutlery) {
            onJajaninClick()
        }

        ButtonIcon("Jastip-in", R.drawable.ic_bag) {
            // belum aktif
        }

        ButtonIcon("All", R.drawable.ic_all) {
            onAllClick()
        }
    }
}
