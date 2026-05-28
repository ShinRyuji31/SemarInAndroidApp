package com.example.application.global.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application.anterin.ui.screen.*
import com.example.application.auth.ui.screen.*
import com.example.application.dashboard.ui.screen.DashboardScreen
import com.example.application.delivery.data.model.StoreType
import com.example.application.delivery.ui.screen.*
import com.example.application.orderhistory.ui.screen.OrderHistoryScreen
import com.example.application.chat.ui.screen.ChatWithDriverPage
import com.example.application.anterin.ui.viewmodel.AnterinViewModel
import com.example.application.global.ui.screen.FindingDriverPage

@Composable
fun AppNavigation(
    currentRoute: Routes,
    onNavigate: (Routes) -> Unit,
    onBack: () -> Unit
) {
    // 1. Shared ViewModel for the Anter-In flow
    val anterinViewModel: AnterinViewModel = viewModel()

    when (currentRoute) {
        // AUTH FLOW
        is Routes.LandingRoute -> LandingScreen(
            onLoginClick = { onNavigate(Routes.LoginRoute) },
            onSignUpClick = { onNavigate(Routes.SignUpRoute) }
        )

        is Routes.LoginRoute -> LoginScreen(
            onLoginSuccess = { onNavigate(Routes.DashBoardRoute) },
            onGoToSignUp = { onNavigate(Routes.SignUpRoute) }
        )

        is Routes.SignUpRoute -> SignUpScreen(
            onRegisterSuccess = { onNavigate(Routes.DashBoardRoute) },
            onLoginClick = { onNavigate(Routes.LoginRoute) }
        )

        // DASHBOARD
        is Routes.DashBoardRoute -> DashboardScreen(
            onProfileClick = { onNavigate(Routes.ProfileRoute) },
            onAnjeminClick = { onNavigate(Routes.AnterPickupInputRoute) },
            onJajaninClick = { onNavigate(Routes.JajaninMainRoute) },
            onJastipinClick = { onNavigate(Routes.JastipinMainRoute) },
            onOrderStatusClick = { onNavigate(Routes.AnterOrderStatusRoute) },
            onOrderHistoryClick = { onNavigate(Routes.OrderHistoryRoute) }
        )

        is Routes.ProfileRoute -> ProfileScreen(
            onBack = onBack,
            onHomeClick = { onNavigate(Routes.DashBoardRoute) },
            onLogoutSuccess = { onNavigate(Routes.LandingRoute) },
            onOrderStatusClick = { onNavigate(Routes.AnterOrderStatusRoute) },
            onOrderHistoryClick = { onNavigate(Routes.OrderHistoryRoute) }
        )

        is Routes.OrderHistoryRoute -> OrderHistoryScreen(
            onBack = onBack,
            onHomeClick = { onNavigate(Routes.DashBoardRoute) },
            onOrderStatusClick = { onNavigate(Routes.AnterOrderStatusRoute) },
            onProfileClick = { onNavigate(Routes.ProfileRoute) }
        )

        // ANTER FLOW (Uses Shared ViewModel)
        is Routes.AnterPickupInputRoute -> AnterinMainPage(
            mode = MainMode.PICKUP_ONLY,
            onPickupClick = { onNavigate(Routes.AnterPickupMapRoute) },
            onDestinationClick = {},
            onBack = onBack,
            viewModel = anterinViewModel
        )

        is Routes.AnterPickupMapRoute -> AnterinSearchPage(
            mode = MapMode.PICKUP,
            onNavigate = onNavigate,
            onBack = onBack,
            viewModel = anterinViewModel
        )

        is Routes.AnterDestinationInputRoute -> AnterinMainPage(
            mode = MainMode.PICKUP_AND_DESTINATION,
            onPickupClick = { onNavigate(Routes.AnterPickupMapRoute) },
            onDestinationClick = { onNavigate(Routes.AnterDestinationMapRoute) },
            onBack = onBack,
            viewModel = anterinViewModel
        )

        is Routes.AnterDestinationMapRoute -> AnterinSearchPage(
            mode = MapMode.DESTINATION,
            onNavigate = onNavigate,
            onBack = onBack,
            viewModel = anterinViewModel
        )

        is Routes.AnterDestinationSetRoute -> AnterinDestinationSetPage(
            onBack = onBack,
            onFindDriver = { onNavigate(Routes.AnterFindingDriverRoute) },
            viewModel = anterinViewModel
        )

        is Routes.AnterFindingDriverRoute -> FindingDriverPage(
            serviceName = "Anter-In",
            onBack = onBack,
            onFinished = { onNavigate(Routes.AnterOrderStatusRoute) }
        )

        is Routes.AnterOrderStatusRoute -> AnterinOrderStatusPage(
            onBack = onBack,
            onHomeClick = { onNavigate(Routes.DashBoardRoute) },
            onProfileClick = { onNavigate(Routes.ProfileRoute) },
            onChatClick = { onNavigate(Routes.JajaninChatRoute) },
            onOrderHistoryClick = { onNavigate(Routes.OrderHistoryRoute) },
            viewModel = anterinViewModel
        )

        // JAJAN FLOW
        is Routes.JajaninMainRoute -> DeliveryMainPage(
            type = StoreType.FOOD,
            onBack = onBack,
            onStoreClick = {
                onNavigate(Routes.JajaninDetailRoute)
            }
        )

        is Routes.JajaninDetailRoute -> DeliveryDetailPage(
            onBack = onBack,
            onCartClick = { onNavigate(Routes.CartRoute) }
        )

        is Routes.JastipinMainRoute -> DeliveryMainPage(
            type = StoreType.RETAIL,
            onBack = onBack,
            onStoreClick = {
                onNavigate(Routes.JajaninDetailRoute)
            }
        )

        is Routes.JajaninFindingDriverRoute -> FindingDriverPage(
            serviceName = "Jajan-In",
            onBack = onBack,
            onFinished = { onNavigate(Routes.JajaninOrderStatusRoute) }
        )

        is Routes.JajaninOrderStatusRoute -> JajaninOrderStatusPage(
            onBack = onBack,
            onHomeClick = { onNavigate(Routes.DashBoardRoute) },
            onProfileClick = { onNavigate(Routes.ProfileRoute) },
            onChatClick = { onNavigate(Routes.JajaninChatRoute) },
            onOrderHistoryClick = { onNavigate(Routes.OrderHistoryRoute) }
        )

        // CART 
        is Routes.CartRoute -> CartPage(
            onBack = onBack,
            onCheckout = { onNavigate(Routes.JajaninFindingDriverRoute) }
        )

        is Routes.JajaninChatRoute -> ChatWithDriverPage(
            onBack = onBack
        )
    }
}
