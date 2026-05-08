package com.example.application.ui.component.shared.delivery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.application.data.model.Restaurant

@Composable
fun DeliveryItemList(
    restaurants: List<Restaurant>,
    onRestaurantClick: (Restaurant) -> Unit
) {
    Column {

        Text(
            text = "Top Picks",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        restaurants.forEach { restaurant ->
            DeliveryItemCard(
                name = restaurant.name,
                address = restaurant.address,
                rating = restaurant.rating,
                imageRes = restaurant.imageRes,
                promo = "diskon 5rb",
                onClick = {
                    onRestaurantClick(restaurant)
                }
            )
        }
    }
}