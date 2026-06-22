package com.example.application._core.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {

    @Serializable data object LandingRoute : Routes
    @Serializable data object LoginRoute : Routes
    @Serializable data object SignUpRoute : Routes
    @Serializable data object DashBoardRoute : Routes
    @Serializable data object ProfileRoute : Routes

    @Serializable data object ProfileFullRoute : Routes
    @Serializable data object OrderHistoryRoute : Routes

    //ANTER FLOW
    @Serializable data object AnterPickupInputRoute : Routes
    @Serializable data object AnterPickupMapRoute : Routes
    @Serializable data object AnterDestinationInputRoute : Routes
    @Serializable data object AnterDestinationMapRoute : Routes
    @Serializable data object AnterDestinationSetRoute : Routes
    @Serializable data object AnterFindingDriverRoute : Routes
    @Serializable data object AnterOrderStatusRoute : Routes

    // JAJAN
    @Serializable data object GlobalSearchRoute : Routes
    @Serializable data object JajaninMainRoute : Routes
    @Serializable data object JastipinMainRoute : Routes
    @Serializable data object JajaninDetailRoute : Routes
    @Serializable data object JajaninFindingDriverRoute : Routes
    @Serializable data object JajaninOrderStatusRoute : Routes
    @Serializable data object JajaninChatRoute : Routes

    // CART
    @Serializable data object CartRoute : Routes

    @Serializable data object OrderStatusGlobalRoute : Routes

    // ─── NESTED GRAPH ROOT MARKERS ───
    @Serializable data object AuthGraph : Routes
    @Serializable data object AnterinGraph : Routes
    @Serializable data object DeliveryGraph : Routes

    // Driver
    @Serializable data object DriverAuthRoute : Routes
    @Serializable data object DriverDashboardRoute : Routes
    @Serializable
    object DriverOrderStatusRoute

    @Serializable data object DriverSignUpRoute : Routes
}
