package com.example.application.driver._core.ui.navigation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.application._core.ui.navigation.Routes
import com.example.application.auth.ui.screen.LoginScreen
import com.example.application.driver._core.ui.viewmodel.CoreViewmodel
import com.example.application.driver.auth.ui.screen.DriverLandingScreen
import com.example.application.driver.auth.ui.screen.DriverSignUpScreen
import com.example.application.driver.auth.ui.viewmodel.DriverAuthViewModel
import com.example.application.driver.dashboard.ui.screen.DriverDashboardScreen
import com.example.application.driver.order.ui.screen.DriverOrderStatusScreen
import com.example.application.driver.order.ui.overlay.GlobalOrderNotification
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun DriverAppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val coreViewModel: CoreViewmodel = koinInject()
    val activeOrder by coreViewModel.activeOrder.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = Routes.LandingRoute
        ) {

            composable<Routes.LandingRoute> {
                val authViewModel: DriverAuthViewModel = koinViewModel()

                var isCheckingSession by remember { mutableStateOf(true) }

                LaunchedEffect(Unit) {
                    authViewModel.checkSession(
                        onSessionValid = {
                            navController.navigate(Routes.DriverDashboardRoute) {
                                popUpTo(Routes.LandingRoute) { inclusive = true }
                            }
                        },
                        onSessionInvalid = {
                            isCheckingSession = false
                        }
                    )
                }

                DriverLandingScreen(
                    onLoginClick = { navController.navigate(Routes.LoginRoute) },
                    onSignUpClick = { navController.navigate(Routes.DriverSignUpRoute) }
                )

            }

            composable<Routes.LoginRoute> {
                val authViewModel: DriverAuthViewModel = koinViewModel()

                LoginScreen(
                    onGoToSignUp = { navController.navigate(Routes.DriverSignUpRoute) },
                    onLoginSuccess = {
                        authViewModel.verifyDriverStatus(
                            onSuccess = {
                                navController.navigate(Routes.DriverDashboardRoute) {
                                    popUpTo(Routes.LandingRoute) { inclusive = true }
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
                    },
                    onNavigateToOrderDetail = {
                        navController.navigate(Routes.DriverOrderStatusRoute)
                    },
                    onNavigateToOrderStatus = {
                        navController.navigate(Routes.DriverOrderStatusRoute)
                    }
                )
            }

            composable<Routes.DriverOrderStatusRoute> {
                DriverOrderStatusScreen(
                    activeOrder = activeOrder,
                    onBack = { navController.popBackStack() },
                    onHomeClick = {
                        navController.navigate(Routes.DriverDashboardRoute) {
                            popUpTo(Routes.DriverDashboardRoute) { inclusive = true }
                        }
                    },
                    onOrderHistoryClick = { },
                    onChatClick = { },
                    onConfirmPickup = {
                        activeOrder?.orderId?.let { coreViewModel.confirmPickup(it) }
                    },
                    onArrivedAtDestination = {
                        activeOrder?.orderId?.let { coreViewModel.arrivedAtDestination(it) }
                    },
                    onPaymentCompleted = {
                        activeOrder?.orderId?.let {
                            coreViewModel.confirmPayment(it)
                            navController.navigate(Routes.DriverDashboardRoute) {
                                popUpTo(Routes.DriverDashboardRoute) { inclusive = true }
                            }
                        }
                    }
                )
            }
        }

        GlobalOrderNotification(
            viewModel = coreViewModel
        )
    }
}