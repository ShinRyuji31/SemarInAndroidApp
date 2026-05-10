package com.example.application.ui.component.anterin.destination

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.R
import com.example.application.ui.theme.BlackSoft
import com.example.application.ui.theme.GrayDark

@Composable
fun AnterinLocationItem(title: String, subtitle: String) {

    Row(verticalAlignment = Alignment.CenterVertically) {

        Icon(
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = BlackSoft
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            Text(subtitle, fontSize = 12.sp, color = GrayDark)
        }
    }
}