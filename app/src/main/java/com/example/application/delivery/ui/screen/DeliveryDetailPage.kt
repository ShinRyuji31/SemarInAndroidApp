package com.example.application.delivery.ui.screen

import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.R
import com.example.application.global.ui.component.Header
import com.example.application.delivery.ui.component.store.StoreInfoCard
import com.example.application.delivery.ui.component.inventory.DeliveryInventorySection
import com.example.application.delivery.ui.viewmodel.StoreViewModel
import com.example.application.delivery.ui.viewmodel.CartViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DeliveryDetailPage(
    onBack: () -> Unit,
    onCartClick: () -> Unit,
    viewModel: StoreViewModel = koinViewModel()
) {
    val inventory by viewModel.inventory.collectAsState()

    val stores by viewModel.stores.collectAsState()
    val selectedStore by viewModel.selectedStore.collectAsState()

    val storeToShow = selectedStore ?: stores.firstOrNull()

    val filteredInventory = selectedStore?.let { store ->
        inventory.filter { it.storeId == store.id }
    } ?: emptyList()

    val cartViewModel: CartViewModel = koinViewModel()
    val cartItems by cartViewModel.cartItems.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Spacer(modifier = Modifier.height(60.dp).statusBarsPadding())
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    val ctx = LocalContext.current

                    AsyncImage(
                        model = ImageRequest.Builder(ctx)
                            .data(storeToShow?.imageUrl ?: R.drawable.dummy)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }

            item {
                storeToShow?.let {
                    Box(modifier = Modifier.offset(y = (-40).dp)) {
                        StoreInfoCard(store = it)
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height((-20).dp))

                val groupedInventory = filteredInventory.groupBy { it.category }
                
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    groupedInventory.forEach { (category, items) ->
                        DeliveryInventorySection(
                            title = category,
                            items = items,
                            onAddToCart = { inventory ->

                                cartViewModel.addToCart(
                                    inventory.id
                                )
                            }
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
                .offset(y = 40.dp)
                .clickable { onCartClick() }
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.White, CircleShape)
                    .border(1.dp, Color(0xFF2D9CDB), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bag),
                    contentDescription = "Cart",
                    tint = Color(0xFF2D9CDB),
                    modifier = Modifier.size(30.dp)
                )
                
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(20.dp)
                        .background(Color(0xFF2D9CDB), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = cartItems.sumOf { it.quantity }.toString(),
                        color = Color.White,
                        fontSize = 10.sp
                    )
                }
            }
        }

        Header(
            title = "Jajan-In",
            onBack = onBack,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}
