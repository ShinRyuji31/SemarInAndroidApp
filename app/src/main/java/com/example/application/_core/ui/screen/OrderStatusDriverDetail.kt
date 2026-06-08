package com.example.application._core.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.R
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.BluePrimary
import com.example.application._core.ui.theme.GrayMedium
import com.example.application._core.ui.theme.WhiteSoft

@Composable
fun OrderStatusDriverDetail(
    driverName: String,
    vehicleInfo: String,
    onCallClick: () -> Unit,
    onChatClick: () -> Unit = {}
) {

    Card(
        modifier = Modifier.fillMaxWidth(),

        colors = CardDefaults.cardColors(
            containerColor = WhiteSoft
        ),

        shape = RoundedCornerShape(0.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),

                verticalAlignment = Alignment.CenterVertically,

                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                Column {

                    Text(
                        text = driverName,
                        fontSize = 18.sp,
                        color = BlackSoft
                    )

                    Text(
                        text = vehicleInfo,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier.width(80.dp)
                ) {

                    Image(
                        painter = painterResource(
                            id = R.drawable.ic_bike
                        ),

                        contentDescription = null,

                        modifier = Modifier
                            .size(60.dp)
                            .align(Alignment.CenterStart)
                    )

                    Image(
                        painter = painterResource(
                            id = R.drawable.dummy
                        ),

                        contentDescription = null,

                        contentScale = ContentScale.Crop,

                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .border(
                                2.dp,
                                BluePrimary,
                                CircleShape
                            )
                            .clickable { onChatClick() }
                            .align(Alignment.CenterEnd)
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                verticalAlignment =
                    Alignment.CenterVertically,

                modifier = Modifier.fillMaxWidth()
            ) {

                OutlinedTextField(
                    value = "",
                    onValueChange = {},

                    placeholder = {
                        Text(
                            "Any notes?",
                            fontSize = 14.sp
                        )
                    },

                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),

                    shape = RoundedCornerShape(8.dp),

                    colors =
                        OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor =
                                GrayMedium
                        )
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                IconButton(
                    onClick = onCallClick
                ) {

                    Icon(
                        painter = painterResource(
                            id = R.drawable.ic_orderstatus
                        ),

                        contentDescription = "Call",

                        tint = BlackSoft,

                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}