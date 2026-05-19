package com.example.application.auth.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.R
import com.example.application.global.ui.component.ButtonBlue
import com.example.application.global.ui.component.ButtonSocial
import com.example.application.global.ui.component.TextFieldOutlineRegular
import com.example.application.global.ui.theme.WhiteSoft
import com.example.application.global.ui.theme.blueWhiteGradient

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onGoToSignUp: () -> Unit
) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = blueWhiteGradient())
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .align(Alignment.Center)
                .background(
                    color = WhiteSoft,
                    shape = RoundedCornerShape(28.dp)
                )
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Login",
                    fontSize = 24.sp,
                    fontWeight = Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Username",
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = Bold,
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.height(2.dp))

                TextFieldOutlineRegular(
                    value = username,
                    onValueChange = { username = it },
                    placeholder = "Enter Username"
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Password",
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = Bold,
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.height(2.dp))

                TextFieldOutlineRegular(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Enter Password",
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(18.dp))

                ButtonBlue(
                    text = "Log In",
                    onClick = {
                        if (username.isNotEmpty() && password.isNotEmpty()) {
                            onLoginSuccess()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Or log in with",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    ButtonSocial(icon = R.drawable.logo_google)
                    ButtonSocial(icon = R.drawable.logo_facebook)

                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = buildAnnotatedString {
                        append("Don’t have an account? ")

                        withStyle(
                            style = SpanStyle(
                                fontWeight = Bold
                            )
                        ) {
                            append("Sign Up")
                        }
                    },
                    modifier = Modifier.clickable {
                        onGoToSignUp()
                    },
                    fontSize = 13.sp
                )
            }
        }
    }
}
