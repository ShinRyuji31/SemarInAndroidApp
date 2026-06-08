package com.example.application.delivery.ui.component.orderstatus

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.R
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.BluePrimary
import com.example.application._core.ui.theme.GrayMedium
import com.example.application._core.ui.theme.WhiteSoft

@Composable
fun OrderTimelineItem(
    title: String,
    time: String,
    isCompleted: Boolean,
    isLast: Boolean
) {

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = Modifier.width(32.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        if (isCompleted)
                            BluePrimary
                        else
                            GrayMedium,

                        CircleShape
                    )
                    .border(
                        2.dp,

                        if (isCompleted)
                            BluePrimary
                        else
                            GrayMedium,

                        CircleShape
                    ),

                contentAlignment = Alignment.Center
            ) {

                if (isCompleted) {

                    Icon(
                        painter = painterResource(
                            id = R.drawable.ic_star
                        ),

                        contentDescription = null,

                        modifier = Modifier.size(14.dp),

                        tint = WhiteSoft
                    )
                }
            }

            if (!isLast) {

                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(40.dp)
                        .background(
                            if (isCompleted)
                                BluePrimary
                            else
                                GrayMedium
                        )
                )
            }
        }

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        Column(
            modifier = Modifier.padding(
                bottom =
                    if (isLast)
                        0.dp
                    else
                        24.dp
            )
        ) {

            Text(
                text = title,

                fontSize = 14.sp,

                fontWeight = FontWeight.Bold,

                color =
                    if (isCompleted)
                        BlackSoft
                    else
                        Color.Gray
            )

            if (time.isNotEmpty()) {

                Text(
                    text = time,

                    fontSize = 12.sp,

                    color = Color.Gray
                )
            }
        }
    }
}