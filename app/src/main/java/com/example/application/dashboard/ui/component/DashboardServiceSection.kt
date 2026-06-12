package com.example.application.dashboard.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.application.R
import com.example.application._core.ui.component.ButtonIcon

@Composable
fun DashboardServiceSection(
    onAnjeminClick: () -> Unit,
    onJajaninClick: () -> Unit,
    onJastipinClick: () -> Unit,
    onAllClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        ButtonIcon("Antar-in", R.drawable.ic_bike) {
            onAnjeminClick()
        }

        ButtonIcon("Jajan-in", R.drawable.ic_cutlery) {
            onJajaninClick()
        }

        ButtonIcon("Jastip-in", R.drawable.ic_bag) {
            onJastipinClick()
        }

        ButtonIcon("All", R.drawable.ic_all) {
            onAllClick()
        }
    }
}
