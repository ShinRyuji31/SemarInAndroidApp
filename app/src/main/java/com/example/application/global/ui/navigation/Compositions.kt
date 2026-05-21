package com.example.application.global.ui.navigation

import androidx.compose.runtime.Composable
import com.example.application.anterin.ui.screen.AnterinDestinationSetPage
import com.example.application.anterin.ui.screen.AnterinMainPage
import com.example.application.anterin.ui.screen.AnterinOrderStatusPage
import com.example.application.anterin.ui.screen.AnterinSearchPage
import com.example.application.anterin.ui.screen.MainMode
import com.example.application.anterin.ui.screen.MapMode
import com.example.application.auth.ui.screen.LoginScreen
import com.example.application.auth.ui.screen.ProfileScreen
import com.example.application.auth.ui.screen.SignUpScreen
import com.example.application.dashboard.ui.screen.DashboardScreen
import com.example.application.delivery.data.model.StoreType
import com.example.application.delivery.ui.screen.*
import com.example.application.auth.ui.screen.LandingScreen
import com.example.application.shared.findingdriver.ui.screen.FindingDriverPage
import com.example.application.orderhistory.ui.screen.OrderHistoryScreen
import com.example.application.chat.ui.screen.ChatWithDriverPage

@Composable
fun AppNavigation(
    currentRoute: Routes,
    onNavigate: (Routes) -> Unit,
    onBack: () -> Unit
) {

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
            onOrderStatusClick = { onNavigate(Routes.AnterOrderStatusRoute) },
            onOrderHistoryClick = { onNavigate(Routes.OrderHistoryRoute) }
        )

        is Routes.OrderHistoryRoute -> OrderHistoryScreen(
            onBack = onBack,
            onHomeClick = { onNavigate(Routes.DashBoardRoute) },
            onOrderStatusClick = { onNavigate(Routes.AnterOrderStatusRoute) },
            onProfileClick = { onNavigate(Routes.ProfileRoute) }
        )

        // ANTER FLOW 
        is Routes.AnterPickupInputRoute -> AnterinMainPage(
            mode = MainMode.PICKUP_ONLY,
            onPickupClick = { onNavigate(Routes.AnterPickupMapRoute) },
            onDestinationClick = {},
            onBack = onBack
        )

        is Routes.AnterPickupMapRoute -> AnterinSearchPage(
            mode = MapMode.PICKUP,
            onNavigate = onNavigate,
            onBack = onBack
        )

        is Routes.AnterDestinationInputRoute -> AnterinMainPage(
            mode = MainMode.PICKUP_AND_DESTINATION,
            onPickupClick = { onNavigate(Routes.AnterPickupMapRoute) },
            onDestinationClick = { onNavigate(Routes.AnterDestinationMapRoute) },
            onBack = onBack
        )

        is Routes.AnterDestinationMapRoute -> AnterinSearchPage(
            mode = MapMode.DESTINATION,
            onNavigate = onNavigate,
            onBack = onBack
        )

        is Routes.AnterDestinationSetRoute -> AnterinDestinationSetPage(
            onBack = onBack,
            onFindDriver = { onNavigate(Routes.AnterFindingDriverRoute) }
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
            onOrderHistoryClick = { onNavigate(Routes.OrderHistoryRoute) }
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

        // CART ====================================================================
        is Routes.CartRoute -> CartPage(
            onBack = onBack,
            onCheckout = { onNavigate(Routes.JajaninFindingDriverRoute) }
        )

        is Routes.JajaninChatRoute -> ChatWithDriverPage(
            onBack = onBack
        )

    }
}
