package com.example.application.dashboard.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.delivery.data.model.Store
import com.example.application.delivery.ui.component.store.StoreCard
import com.example.application.global.ui.theme.BlackSoft

@Composable
fun DashboardLastOrder(
    store: Store?
) {

    if (store == null) return

    Column(
        modifier = Modifier.padding(8.dp)
    ) {

        Text(
            text = "Last Ordered",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = BlackSoft
        )

        StoreCard(
            store = store
        )
    }
}