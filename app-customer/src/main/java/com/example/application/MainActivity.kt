package com.example.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.application.auth.data.repository.UserRepository
import com.example.application._core.ui.navigation.AppNavigation
import com.example.application._core.ui.navigation.Routes
import com.example.application._core.ui.theme.ApplicationTheme
import io.github.jan.supabase.auth.status.SessionStatus
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val userRepository: UserRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.getInsetsController(
            window,
            window.decorView
        ).isAppearanceLightStatusBars = true

        setContent {
            ApplicationTheme {
                val sessionStatus by userRepository.sessionStatus.collectAsState(SessionStatus.Initializing)
                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()

                LaunchedEffect(sessionStatus, currentBackStackEntry) {
                    val currentRoute = currentBackStackEntry?.destination?.route
                    when (sessionStatus) {
                        is SessionStatus.Authenticated -> {
                            if (currentRoute == null ||
                                currentRoute.contains("LandingRoute") ||
                                currentRoute.contains("LoginRoute") ||
                                currentRoute.contains("SignUpRoute") ||
                                currentRoute.contains("AuthGraph")
                            ) {
                                navController.navigate(Routes.DashBoardRoute) {
                                    popUpTo<Routes.AuthGraph> { inclusive = true }
                                }
                            }
                        }
                        is SessionStatus.NotAuthenticated -> {
                            if (currentRoute != null && (
                                currentRoute.contains("DashBoardRoute") ||
                                currentRoute.contains("ProfileRoute") ||
                                currentRoute.contains("OrderHistoryRoute") ||
                                currentRoute.contains("AnterinGraph") ||
                                currentRoute.contains("DeliveryGraph")
                            )) {
                                navController.navigate(Routes.AuthGraph) {
                                    popUpTo<Routes.DashBoardRoute> { inclusive = true }
                                }
                            }
                        }
                        else -> {}
                    }
                }

                AppNavigation(
                    navController = navController,
                    startDestination = Routes.AuthGraph
                )
            }
        }
    }
}
