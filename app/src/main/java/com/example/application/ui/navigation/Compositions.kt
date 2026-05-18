package com.example.application.ui.navigation

import androidx.compose.runtime.Composable
import com.example.application.data.model.StoreType
import com.example.application.ui.screen.*
import com.example.application.ui.screen.anterin.*
import com.example.application.ui.screen.shared.delivery.CartPage
import com.example.application.ui.screen.shared.delivery.DeliveryDetailPage
import com.example.application.ui.screen.shared.delivery.DeliveryMainPage

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
            onJastipinClick = { onNavigate(Routes.JastipinMainRoute) }
        )

        is Routes.ProfileRoute -> ProfileScreen(
            onBack = onBack,
            onHomeClick = { onNavigate(Routes.DashBoardRoute) }
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

        is Routes.AnterFindingDriverRoute -> AnterinFindingDriverPage(
            state = DriverState.FINDING,
            onBack = onBack
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

        // CART ====================================================================
        is Routes.CartRoute -> CartPage(
            onBack = onBack
        )

    }
}
