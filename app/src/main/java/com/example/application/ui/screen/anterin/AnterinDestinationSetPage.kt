package com.example.application.ui.screen.anterin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application.R
import com.example.application.ui.component.anterin.destination.AnterinLocationItem
import com.example.application.ui.component.anterin.destination.AnterinVehicleItem
import com.example.application.ui.component.global.ButtonBlue
import com.example.application.ui.component.global.Header
import com.example.application.ui.theme.BluePrimary
import com.example.application.ui.theme.WhiteSoft
import com.example.application.viewmodel.AnterinViewModel

@Composable
fun AnterinDestinationSetPage(
    onBack: () -> Unit,
    onFindDriver: () -> Unit,
    viewModel: AnterinViewModel = viewModel()
) {

    val route by viewModel.route.collectAsState()
    val vehicles by viewModel.vehicles.collectAsState()
    val selectedVehicle = viewModel.selectedVehicle

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.background_mapdummy),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Header(
            title = "Anter-In",
            onBack = onBack
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(BluePrimary)
        ) {

            Column {

                Column(
                    modifier = Modifier
                        .background(WhiteSoft)
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {

                    AnterinLocationItem(
                        title = "Pick Up",
                        subtitle = route.pickup
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    AnterinLocationItem(
                        title = "Drop Off",
                        subtitle = route.destination
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Column {
                    vehicles.forEach { vehicle ->

                        AnterinVehicleItem(
                            name = vehicle.name,
                            capacity = vehicle.capacity.toString(),
                            price = vehicle.price.toString(),
                            icon = vehicle.icon,
                            isSelected = selectedVehicle == vehicle.id,
                            onClick = {
                                viewModel.selectedVehicle = vehicle.id
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .navigationBarsPadding()
                ) {
                    ButtonBlue(
                        text = "Find Driver",
                        onClick = onFindDriver,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                            .fillMaxWidth()
                            .height(50.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

