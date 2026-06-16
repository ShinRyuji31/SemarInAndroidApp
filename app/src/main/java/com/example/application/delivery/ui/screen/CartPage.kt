package com.example.application.delivery.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.application.R
import com.example.application.delivery.ui.component.cart.CartAlertRemoveItem
import com.example.application.delivery.ui.component.cart.CartEmpty
import com.example.application.delivery.ui.component.cart.CartItemComponent
import com.example.application.delivery.ui.component.cart.CartSummary
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

    var itemToRemove by remember { mutableStateOf<String?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Header(
                title = "Cart",
                onBack = onBack
            )

            if (cartItems.isEmpty()) {

                CartEmpty(modifier = Modifier.weight(1f))

            } else {

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp)
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
                                if (cart.quantity == 1) {
                                    itemToRemove = cart.id
                                } else {
                                    viewModel.decreaseQuantity(cart.id)
                                }
                            }
                        )
                    }
                }

                CartSummary(
                    subtotal = subtotal,
                    deliveryFee = deliveryFee,
                    total = total
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(BlueSecondary, BluePrimary)
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

        if (itemToRemove != null) {
            CartAlertRemoveItem(
                onDismiss = { itemToRemove = null },
                onConfirm = {
                    viewModel.decreaseQuantity(itemToRemove!!)
                    itemToRemove = null
                }
            )
        }
    }
}