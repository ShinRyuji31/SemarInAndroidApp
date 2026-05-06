package com.example.application.ui.screen.shared.delivery

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application.R
import com.example.application.ui.component.global.Header
import com.example.application.ui.component.jajanin.JajaninInfoCard
import com.example.application.ui.component.jajanin.menu.JajaninMenuSection
import com.example.application.viewmodel.JajaninViewModel

@Composable
fun DeliveryDetailPage(
    onBack: () -> Unit,
    viewModel: JajaninViewModel = viewModel()
) {

    val menu by viewModel.menu.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            item {
                Image(
                    painter = painterResource(id = R.drawable.change_this_to_correct_pic),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentScale = ContentScale.Crop
                )
            }

            item {
                Box(
                    modifier = Modifier
                        .offset(y = (-30).dp)
                        .padding(horizontal = 16.dp)
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(16.dp)
                ) {
                    JajaninInfoCard()
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))

                JajaninMenuSection(
                    title = "Menu",
                    items = menu
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