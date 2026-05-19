package com.example.application.delivery.ui.component.store

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.application.delivery.data.model.Store

@Composable
fun StoreCardList(
    stores: List<Store>,
    onStoreClick: (Store) -> Unit
) {

    Column {

        Text(
            text = "Top Picks",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        stores.forEach { store ->

            StoreCard(
                store = store,
                onClick = {
                    onStoreClick(store)
                }
            )
        }
    }
}