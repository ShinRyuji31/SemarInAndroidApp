package com.example.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.application.global.ui.navigation.AppNavigation
import com.example.application.global.ui.navigation.Routes
import com.example.application.global.ui.theme.ApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ApplicationTheme {

                val backStack = remember { mutableStateListOf<Routes>(Routes.LandingRoute) }
                val currentRoute = backStack.last()
                
                AppNavigation(
                    currentRoute = currentRoute,
                    onNavigate = { newRoute ->
                        backStack.add(newRoute)
                    },
                    onBack = {
                        if (backStack.size > 1) {
                            backStack.removeLastOrNull()
                        }
                    }
                )

            }
        }
    }
}
