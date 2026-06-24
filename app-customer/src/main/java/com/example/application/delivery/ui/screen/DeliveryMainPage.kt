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
import com.example.application._core.ui.component.Header
import com.example.application._core.ui.component.SearchBar
import com.example.application.delivery.ui.component.store.StoreCardList
import com.example.application._core.ui.theme.WhiteSoft
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

    val allFilteredStores by viewModel.filteredStores.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    val displayStores = if (type == StoreType.SEARCH) {
        allFilteredStores
    } else {
        allFilteredStores.filter { it.type == type }
    }

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
                        value = searchQuery,
                        onValueChange = { viewModel.updateSearchQuery(it) },
                        placeholderText = if (type == StoreType.SEARCH) "Cari semua toko atau menu..." else "Cari toko, tag, atau menu..."
                    )
                }
            }

            item {
                StoreCardList(
                    stores = displayStores,
                    onStoreClick = { store ->

                        viewModel.selectStore(store)

                        onStoreClick()
                    }
                )

                Spacer(modifier = Modifier.height(80.dp))
            }
        }

        Header(
            title = when (type) {
                StoreType.FOOD -> "Jajan-In"
                StoreType.RETAIL -> "Titip-In"
                StoreType.SEARCH -> "Pencarian"
            },
            onBack = onBack,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}