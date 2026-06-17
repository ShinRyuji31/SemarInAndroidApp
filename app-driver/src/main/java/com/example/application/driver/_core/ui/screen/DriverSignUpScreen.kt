package com.example.application.driver._core.ui.screen

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
import com.example.application.auth.ui.component.AuthInputField
import com.example.application.auth.ui.viewmodel.AuthUiState
import com.example.application._core.ui.component.ButtonBlue
import com.example.application._core.ui.theme.WhiteSoft
import com.example.application._core.ui.theme.blueWhiteGradient
import com.example.application.driver._core.ui.viewmodel.DriverSignUpViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DriverSignUpScreen(
    onRegisterSuccess: () -> Unit,
    onLoginClick: () -> Unit,
    viewModel: DriverSignUpViewModel = koinViewModel()
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

    val uuidMotor = "90170875-2bdb-4581-9c8e-5f0d9804c6e0"
    val uuidMobil = "c7d5ed06-3d12-44ed-95cb-41d5abac6056"

    var selectedDriverTypeId by rememberSaveable { mutableStateOf(uuidMotor) }

    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.Success) {
            onRegisterSuccess()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(brush = blueWhiteGradient())
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            colors = CardDefaults.cardColors(containerColor = WhiteSoft)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Daftar Driver", fontSize = 24.sp, fontWeight = Bold, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(24.dp))

                AuthInputField(
                    value = username,
                    onValueChange = { username = it },
                    label = "Username"
                )

                Spacer(modifier = Modifier.height(16.dp))

                AuthInputField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = "First Name"
                )

                Spacer(modifier = Modifier.height(16.dp))

                AuthInputField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = "Last Name (Optional)"
                )

                Spacer(modifier = Modifier.height(16.dp))

                AuthInputField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email"
                )

                Spacer(modifier = Modifier.height(16.dp))

                AuthInputField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = "Phone Number"
                )

                Spacer(modifier = Modifier.height(16.dp))

                AuthInputField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                AuthInputField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = "Confirm Password",
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Tipe Kendaraan",
                        fontSize = 14.sp,
                        fontWeight = Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedDriverTypeId == uuidMotor,
                            onClick = { selectedDriverTypeId = uuidMotor }
                        )
                        Text("Motor (Anter-in)")
                        Spacer(modifier = Modifier.width(16.dp))
                        RadioButton(
                            selected = selectedDriverTypeId == uuidMobil,
                            onClick = { selectedDriverTypeId = uuidMobil }
                        )
                        Text("Mobil (Titip-in)")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                val errorMessage = validationError ?: (uiState as? AuthUiState.Error)?.message
                if (errorMessage != null) {
                    Text(text = errorMessage, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                ButtonBlue(
                    text = if (uiState is AuthUiState.Loading) "Loading..." else "Daftar Driver",
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
                                viewModel.signUpDriver(
                                    email, password, username, firstName, lastName, phoneNumber,
                                    selectedDriverTypeId
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    enabled = uiState !is AuthUiState.Loading
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Sudah punya akun? Login",
                    modifier = Modifier.clickable { onLoginClick() }
                )
            }
        }
    }
}