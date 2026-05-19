package com.example.application.global.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes : NavKey {

    @Serializable data object LandingRoute : Routes
    @Serializable data object LoginRoute : Routes
    @Serializable data object SignUpRoute : Routes
    @Serializable data object DashBoardRoute : Routes
    @Serializable data object ProfileRoute : Routes

    //ANTER FLOW
    @Serializable data object AnterPickupInputRoute : Routes
    @Serializable data object AnterPickupMapRoute : Routes
    @Serializable data object AnterDestinationInputRoute : Routes
    @Serializable data object AnterDestinationMapRoute : Routes
    @Serializable data object AnterDestinationSetRoute : Routes
    @Serializable data object AnterFindingDriverRoute : Routes

    // JAJAN
    @Serializable data object JajaninMainRoute : Routes
    @Serializable data object JastipinMainRoute : Routes
    @Serializable data object JajaninDetailRoute : Routes
    
    // CART
    @Serializable data object CartRoute : Routes
}
