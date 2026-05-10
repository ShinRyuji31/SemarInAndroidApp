package com.example.application.ui.component.shared.delivery.inventory

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.application.data.model.StoreInventory
import com.example.application.util.toRupiah

@Composable
fun DeliveryInventorySection(
    title: String,
    items: List<StoreInventory>
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        DeliveryInventoryTitle(title)

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items) { item ->
                DeliveryInventoryItemCard(
                    name = item.name,
                    price = item.price.toRupiah(),
                    imageRes = item.imageRes
                )
            }
        }
    }
}
