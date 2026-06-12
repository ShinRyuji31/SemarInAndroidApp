package com.example.application.dashboard.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application._core.ui.theme.BlackSoft
import com.example.application.delivery.data.model.Store
import com.example.application.delivery.ui.component.store.StoreCardShort


@Composable
fun DashboardAffordableRestaurant(
    stores: List<Store>,
    onStoreClick: (Store) -> Unit
) {

    Column {

        Text(
            text = "Semar Resto Termurah",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = BlackSoft
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(stores) { store ->

                StoreCardShort(
                    store = store,
                    onClick = onStoreClick
                )
            }
        }
    }
}