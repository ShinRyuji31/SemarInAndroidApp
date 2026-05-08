package com.example.application.ui.component.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.data.model.Restaurant
import com.example.application.ui.component.shared.delivery.StoreInfoCard
import com.example.application.ui.theme.BlackSoft

@Composable
fun DashboardLastOrder(
    restaurant: Restaurant?
) {

    if (restaurant == null) return

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text(
            text = "Last Ordered",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = BlackSoft
        )

        StoreInfoCard(
            restaurant = restaurant
        )
    }
}