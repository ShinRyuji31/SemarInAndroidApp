package com.example.application.auth.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.core.R
import com.example.application.auth.ui.component.AuthInputField
import com.example.application.auth.ui.viewmodel.AuthUiState
import com.example.application.auth.ui.viewmodel.LoginViewModel
import com.example.application._core.ui.component.ButtonBlue
import com.example.application._core.ui.component.ButtonSocial
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.WhiteSoft
import com.example.application._core.ui.theme.blueWhiteGradient
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onGoToSignUp: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.Success) {
            onLoginSuccess()
            viewModel.resetState()
        }
    }

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
                    color = BlackSoft,
                    fontSize = 24.sp,
                    fontWeight = Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                AuthInputField(
                    label = "Email",
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Enter Email"
                )

                AuthInputField(
                    label = "Password",
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Enter Password",
                    isPassword = true
                )

                if (uiState is AuthUiState.Error) {
                    Text(
                        text = (uiState as AuthUiState.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                ButtonBlue(
                    text = if (uiState is AuthUiState.Loading) "Logging In..." else "Log In",
                    onClick = { viewModel.login(email, password) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    enabled = uiState !is AuthUiState.Loading
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
                        withStyle(style = SpanStyle(fontWeight = Bold)) {
                            append("Sign Up")
                        }
                    },
                    color = BlackSoft,
                    modifier = Modifier.clickable { onGoToSignUp() },
                    fontSize = 13.sp
                )
            }
        }
    }
}
