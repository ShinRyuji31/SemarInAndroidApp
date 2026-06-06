package com.example.application.delivery.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.application.global.ui.component.Header
import com.example.application.global.ui.component.SearchBar
import com.example.application.delivery.ui.component.store.StoreCardList
import com.example.application.global.ui.theme.WhiteSoft
import com.example.application.delivery.ui.viewmodel.StoreViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.application.delivery.data.model.StoreType
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DeliveryMainPage(
    type: StoreType,
    onBack: () -> Unit,
    onStoreClick: () -> Unit,
    viewModel: StoreViewModel = koinViewModel()
){

    val stores by viewModel.stores.collectAsState()

    val filteredStores = stores.filter { it.type == type }

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
                    SearchBar(
                        value = "",
                        onValueChange = {},
                        placeholderText = "Cari kebutuhanmu")
                }
            }

            item {
                StoreCardList(
                    stores = filteredStores,
                    onStoreClick = { store ->

                        viewModel.selectStore(store)

                        onStoreClick()
                    }
                )

                Spacer(modifier = Modifier.height(80.dp))
            }
        }

        Header(
            title = if (type == StoreType.FOOD) {
                "Jajan-In"
            } else {
                "Jastip-In"
            },
            onBack = onBack,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}