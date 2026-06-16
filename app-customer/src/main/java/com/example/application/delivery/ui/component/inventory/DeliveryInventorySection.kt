package com.example.application.delivery.ui.component.inventory

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.application.R
import com.example.application.delivery.data.model.StoreInventory
import com.example.application._core.util.toRupiah

@Composable
fun DeliveryInventorySection(
    title: String,
    items: List<StoreInventory>,
    onAddToCart: (StoreInventory) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        DeliveryInventoryTitle(title)

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items) { item ->
                DeliveryInventoryItemCard(
                    name = item.name,
                    price = item.price.toRupiah(),
                    imageUrl = item.imageUrl,
                    imageRes = item.imageRes ?: R.drawable.dummy,
                    onAddToCart = {
                        onAddToCart(item)
                    }
                )
            }
        }
    }
}