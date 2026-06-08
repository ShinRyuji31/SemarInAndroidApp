package com.example.application.delivery.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.R
import com.example.application.delivery.ui.component.cart.CartItemComponent
import com.example.application._core.ui.component.ButtonWhite
import com.example.application._core.ui.component.Header
import com.example.application._core.ui.theme.BluePrimary
import com.example.application._core.ui.theme.BlueSecondary
import com.example.application._core.util.toRupiah
import com.example.application.delivery.ui.viewmodel.CartViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CartPage(
    onBack: () -> Unit,
    onCheckout: () -> Unit,
    viewModel: CartViewModel = koinViewModel()
) {

    val cartItems by viewModel.cartItems.collectAsState()
    val subtotal by viewModel.subtotal.collectAsState(0)
    val deliveryFee by viewModel.deliveryFee.collectAsState()
    val total by viewModel.total.collectAsState(0)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Header(
                title = "Cart",
                onBack = onBack
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),

                contentPadding = PaddingValues(
                    top = 8.dp,
                    bottom = 16.dp
                )
            ) {

                items(cartItems) { cart ->

                    CartItemComponent(
                        name = cart.name,
                        price = cart.price.toRupiah(),
                        imageUrl = cart.imageUrl,
                        imageRes = R.drawable.dummy,
                        quantity = cart.quantity,

                        onIncrease = {
                            viewModel.increaseQuantity(cart.id)
                        },

                        onDecrease = {
                            viewModel.decreaseQuantity(cart.id)
                        }
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(
                        horizontal = 16.dp,
                        vertical = 20.dp
                    )
            ) {

                HorizontalDivider(
                    modifier = Modifier.padding(bottom = 16.dp),
                    thickness = 1.dp,
                    color = Color.LightGray
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {

                    Text(
                        text = "Deliver to",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Cart size = ${cartItems.size}"
                    )

                    Column(
                        horizontalAlignment = Alignment.End
                    ) {

                        Text(
                            text = "Current Location",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Gg. Kutai Utara No. 1",
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Subtotal",
                        color = Color.Gray,
                        fontSize = 15.sp
                    )

                    Text(
                        text = subtotal.toRupiah(),
                        color = Color.Gray,
                        fontSize = 15.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Delivery fee",
                        color = Color.Gray,
                        fontSize = 15.sp
                    )

                    Text(
                        text = deliveryFee.toRupiah(),
                        color = Color.Gray,
                        fontSize = 15.sp
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Total",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = total.toRupiah(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                BlueSecondary,
                                BluePrimary
                            )
                        )
                    )
                    .padding(16.dp)
                    .navigationBarsPadding()
            ) {

                ButtonWhite(
                    text = "Checkout",
                    onClick = onCheckout,

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                )
            }
        }
    }
}