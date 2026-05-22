package com.example.application.dashboard.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application.dashboard.ui.component.DashboardAffordableRestaurant
import com.example.application.dashboard.ui.component.DashboardAllCategorySheet
import com.example.application.dashboard.ui.component.DashboardBottomBanner
import com.example.application.dashboard.ui.component.DashboardBottomNavBar
import com.example.application.dashboard.ui.component.DashboardHeader
import com.example.application.dashboard.ui.component.DashboardLastOrder
import com.example.application.dashboard.ui.component.DashboardServiceSection
import com.example.application.dashboard.ui.component.DashboardTopBanner
import com.example.application.global.ui.component.SearchBar
import com.example.application.global.ui.theme.WhiteSoft
import com.example.application.dashboard.ui.viewmodel.DashboardViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DashboardScreen(
    onProfileClick: () -> Unit,
    onAnjeminClick: () -> Unit,
    onJajaninClick: () -> Unit,
    onJastipinClick: () -> Unit,
    onOrderStatusClick: () -> Unit = {},
    onOrderHistoryClick: () -> Unit = {},
    viewModel: DashboardViewModel = viewModel()
) {

    val user by viewModel.user.collectAsState()
    val topBanners by viewModel.topBanners.collectAsState()
    val bottomBanners by viewModel.bottomBanners.collectAsState()
    val affordableRestaurants by viewModel.affordableRestaurants.collectAsState()
    val lastOrderRestaurant by viewModel.lastOrderStore.collectAsState()

    val listState = rememberLazyListState()

    val isScrolled = listState.firstVisibleItemIndex > 0 ||
            listState.firstVisibleItemScrollOffset > 50

    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp
            ),
            containerColor = WhiteSoft
        ) {

            DashboardAllCategorySheet(
                onClose = { showBottomSheet = false },

                onAnterClick = {
                    showBottomSheet = false
                    onAnjeminClick()
                },

                onJajanClick = {
                    showBottomSheet = false
                    onJajaninClick()
                },

                onJastipinClick = {
                    showBottomSheet = false
                    onJastipinClick()
                }
            )
        }
    }

    Scaffold(
        bottomBar = {

            DashboardBottomNavBar(
                currentTab = 0,
                onHomeClick = { },
                onOrderStatusClick = onOrderStatusClick,
                onOrderHistoryClick = onOrderHistoryClick,
                onProfileClick = onProfileClick
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(top = 50.dp)
            ) {

                item {

                    AnimatedVisibility(
                        visible = !isScrolled
                    ) {

                        Text(
                            text = "Hi ${user?.name ?: "User"} 👋\nNeed Something?",

                            modifier = Modifier.padding(
                                top = 16.dp,
                                bottom = 4.dp,
                                start = 16.dp
                            ),

                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }

                stickyHeader {

                    Box(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .padding(vertical = 16.dp)
                    ) {

                        SearchBar(
                            value = "",
                            onValueChange = {},
                            placeholderText = "Cari Kebutuhanmu"
                        )
                    }
                }

                item {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.background
                            )
                            .padding(top = 4.dp)
                    ) {

                        DashboardTopBanner(
                            banners = topBanners
                        )

                        DashboardServiceSection(
                            onAnjeminClick = onAnjeminClick,

                            onJajaninClick = onJajaninClick,

                            onJastipinClick = onJastipinClick,

                            onAllClick = {
                                showBottomSheet = true
                            }
                        )

                        DashboardLastOrder(
                            store = lastOrderRestaurant
                        )

                        DashboardAffordableRestaurant(
                            stores = affordableRestaurants
                        )

                        DashboardBottomBanner(
                            banners = bottomBanners
                        )

                        Spacer(
                            modifier = Modifier.height(80.dp)
                        )
                    }
                }
            }

            user?.let {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                ) {

                    DashboardHeader(
                        user = it
                    )
                }
            }
        }
    }
}