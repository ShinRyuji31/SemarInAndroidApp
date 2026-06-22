package com.example.application.driver.dashboard.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.core.R
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.BluePrimary
import com.example.application._core.ui.theme.WhiteSoft

@Composable
fun DashboardBottomNavBar(
    currentTab: Int,
    onHomeClick: () -> Unit,
    onOrderStatusClick: () -> Unit = {},
    onOrderHistoryClick: () -> Unit = {},
    onChatClick: () -> Unit
) {

    val items = listOf("Home", "Order Status", "Order History", "Chat")
    val icons = listOf(
        R.drawable.ic_home,
        R.drawable.ic_orderstatus,
        R.drawable.ic_history,
        R.drawable.ic_chat
    )

    NavigationBar(
        containerColor = WhiteSoft,
        tonalElevation = 8.dp
    ) {
        items.forEachIndexed { index, label ->

            val isSelected = currentTab == index

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    when (index) {
                        0 -> onHomeClick()   
                        1 -> onOrderStatusClick()
                        2 -> onOrderHistoryClick()
                        3 -> onChatClick()
                    }
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = icons[index]),
                            contentDescription = label,
                            modifier = Modifier.size(32.dp),
                            tint = if (isSelected) BluePrimary else BlackSoft
                        )
                        Text(
                            text = label,
                            fontSize = 10.sp,
                            color = if (isSelected) BluePrimary else BlackSoft
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
