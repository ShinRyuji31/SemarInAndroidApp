package com.example.application.auth.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.R
import com.example.application.auth.ui.component.AuthInputField
import com.example.application.auth.ui.viewmodel.AuthUiState
import com.example.application.auth.ui.viewmodel.SignUpViewModel
import com.example.application._core.ui.component.ButtonBlue
import com.example.application._core.ui.component.ButtonSocial
import com.example.application._core.ui.theme.WhiteSoft
import com.example.application._core.ui.theme.blueWhiteGradient
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    onRegisterSuccess: () -> Unit,
    onLoginClick: () -> Unit,
    viewModel: SignUpViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    var username by rememberSaveable { mutableStateOf("") }
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    
    var validationError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.Success) {
            onRegisterSuccess()
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
                .padding(24.dp)
                .align(Alignment.Center)
                .background(
                    color = WhiteSoft,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier.verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Sign Up", fontSize = 20.sp, fontWeight = Bold)

                Spacer(modifier = Modifier.height(24.dp))

                AuthInputField("Username", username, { username = it; validationError = null })
                AuthInputField("First Name", firstName, { firstName = it; validationError = null })
                AuthInputField("Last Name (Optional)", lastName, { lastName = it; validationError = null })
                AuthInputField("Email", email, { email = it; validationError = null })
                AuthInputField("Phone Number", phoneNumber, { phoneNumber = it; validationError = null })
                AuthInputField("Password", password, { password = it; validationError = null }, isPassword = true)
                AuthInputField("Confirm Password", confirmPassword, { confirmPassword = it; validationError = null }, isPassword = true)

                val errorMessage = (uiState as? AuthUiState.Error)?.message ?: validationError
                if (errorMessage != null) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                ButtonBlue(
                    text = if (uiState is AuthUiState.Loading) "Registering..." else "Sign Up",
                    onClick = {
                        when {
                            username.isBlank() || firstName.isBlank() || email.isBlank() || phoneNumber.isBlank() || password.isBlank() -> {
                                validationError = "Please fill all required fields"
                            }
                            password != confirmPassword -> {
                                validationError = "Passwords do not match"
                            }
                            else -> {
                                validationError = null
                                viewModel.signUp(email, password, username, firstName, lastName, phoneNumber)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    enabled = uiState !is AuthUiState.Loading
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ButtonSocial(icon = R.drawable.logo_google)
                    ButtonSocial(icon = R.drawable.logo_facebook)
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "Already have an account? Login",
                    modifier = Modifier.clickable { onLoginClick() }
                )
            }
        }
    }
}
