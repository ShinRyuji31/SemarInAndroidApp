package com.example.application.driver._core.ui.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.application._core.ui.navigation.Routes
import com.example.application.auth.ui.screen.LoginScreen
import com.example.application.driver.auth.ui.viewmodel.DriverAuthViewModel
import com.example.application.driver.dashboard.ui.screen.DriverDashboardScreen
import com.example.application.driver.auth.ui.screen.DriverSignUpScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun DriverAppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Routes.LoginRoute
    ) {
        composable<Routes.LoginRoute> {
            val authViewModel: DriverAuthViewModel = koinViewModel()

            LoginScreen(
                onGoToSignUp = {
                    navController.navigate(Routes.DriverSignUpRoute)
                },
                onLoginSuccess = {
                    authViewModel.verifyDriverStatus(
                        onSuccess = {
                            navController.navigate(Routes.DriverDashboardRoute) {
                                popUpTo<Routes.LoginRoute> { inclusive = true }
                            }
                        },
                        onError = { errorMsg ->
                            Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
                        }
                    )
                }
            )
        }

        composable<Routes.DriverSignUpRoute> {
            DriverSignUpScreen(
                onRegisterSuccess = {
                    Toast.makeText(context, "Pendaftaran berhasil, silakan login", Toast.LENGTH_SHORT).show()
                    navController.navigate(Routes.LoginRoute) {
                        popUpTo<Routes.DriverSignUpRoute> { inclusive = true }
                    }
                },
                onLoginClick = {
                    navController.navigate(Routes.LoginRoute) {
                        popUpTo<Routes.DriverSignUpRoute> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.DriverDashboardRoute> {
            val authViewModel: DriverAuthViewModel = koinViewModel()

            DriverDashboardScreen(
                onLogout = {
                    authViewModel.logout(
                        onSuccess = {
                            navController.navigate(Routes.LoginRoute) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    )
                }
            )
        }
    }
}