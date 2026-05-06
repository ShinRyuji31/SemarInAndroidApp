package com.example.application.ui.component.jajanin.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.application.data.model.MenuFoodItem

@Composable
fun JajaninMenuSection(
    title: String,
    items: List<MenuFoodItem>
) {
    Column {

        JajaninMenuTitle(title)

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items) {
                JajaninMenuItemCard(
                    name = it.name,
                    price = it.price,
                    imageRes = it.imageRes
                )
            }
        }
    }
}
