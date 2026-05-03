package com.example.application.ui.screen.jajanin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.application.ui.component.shared.AffordableRestaurant
import com.example.application.ui.component.global.Header
import com.example.application.ui.component.global.SearchBar
import com.example.application.ui.component.jajanin.JajaninRestaurantList
import com.example.application.ui.theme.WhiteSoft

@Composable
fun JajaninMainPage(
    onBack: () -> Unit,
    onRestaurantClick: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(WhiteSoft)
                .padding(top = 80.dp)
        ) {

            stickyHeader {

                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(vertical = 16.dp)
                ) {
                    SearchBar(placeholderText = "Cari Kebutuhanmu")
                }
            }

            item {
                JajaninRestaurantList(
                    onRestaurantClick = onRestaurantClick
                )

                Spacer(modifier = Modifier.height(80.dp))
            }
        }

        Header(
            title = "Jajan-In",
            onBack = onBack,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}