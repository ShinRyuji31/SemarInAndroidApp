package com.example.application.delivery.ui.component.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.application.R
import com.example.application._core.ui.theme.BluePrimary


@Composable
fun FloatingCartButton(
    count: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .clickable { onClick() }
            .wrapContentSize()
    ) {


        Box(
            modifier = Modifier
                .size(60.dp)
                .background(
                    Color.White,
                    CircleShape
                )
                .border(
                    1.dp,
                    BluePrimary,
                    CircleShape
                ),

            contentAlignment = Alignment.Center
        ) {

            Icon(
                painter = painterResource(R.drawable.ic_bag),
                contentDescription = "Cart",
                tint = BluePrimary,
                modifier = Modifier.size(30.dp)
            )
        }

        if (count > 0) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 5.dp, y = (-5).dp)
                    .size(25.dp)
                    .background(
                        BluePrimary,
                        CircleShape
                    ),

                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = count.toString(),
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}