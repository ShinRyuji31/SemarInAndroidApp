package com.example.application.ui.screen.shared.delivery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.application.ui.component.global.Header
import com.example.application.ui.component.global.SearchBar
import com.example.application.ui.component.shared.delivery.DeliveryItemList
import com.example.application.ui.theme.WhiteSoft
import com.example.application.viewmodel.JajaninViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun DeliveryMainPage(
    onBack: () -> Unit,
    onRestaurantClick: () -> Unit,
    viewModel: JajaninViewModel = viewModel()
) {

    val restaurants by viewModel.restaurants.collectAsState()

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
                    SearchBar(placeholderText = "Cari kebutuhanmu")
                }
            }

            item {
                DeliveryItemList(
                    restaurants = restaurants,
                    onRestaurantClick = { restaurant ->

                        viewModel.selectRestaurant(restaurant)

                        onRestaurantClick()
                    }
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