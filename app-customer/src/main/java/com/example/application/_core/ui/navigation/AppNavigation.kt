package com.example.application._core.ui.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.application.anterin.ui.screen.*
import com.example.application.anterin.ui.viewmodel.AnterinViewModel
import com.example.application.auth.ui.screen.*
import com.example.application.chat.ui.screen.ChatWithDriverPage
import com.example.application.dashboard.ui.screen.DashboardScreen
import com.example.application.delivery.data.model.StoreType
import com.example.application.delivery.ui.screen.*
import com.example.application.delivery.ui.viewmodel.CartViewModel
import com.example.application.delivery.ui.viewmodel.StoreViewModel
import com.example.application.globalorderstatus.ui.route.OrderStatusGlobalRoute
import com.example.application.globalorderstatus.ui.screen.FindingDriverPage
import com.example.application.orderhistory.ui.screen.OrderHistoryScreen
import com.example.application.profile.ui.screen.ProfileFullScreen
import com.example.application.profile.ui.screen.ProfileMainScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: Routes = Routes.AuthGraph
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable<Routes.OrderStatusGlobalRoute> {
            OrderStatusGlobalRoute(
                onBack = { navController.popBackStack() },
                onHomeClick = {
                    navController.navigate(Routes.DashBoardRoute) {
                        popUpTo(Routes.DashBoardRoute) { inclusive = true }
                    }
                },
                onProfileClick = { navController.navigate(Routes.ProfileRoute) },
                onOrderHistoryClick = { navController.navigate(Routes.OrderHistoryRoute) }
            )
        }

        navigation<Routes.AuthGraph>(
            startDestination = Routes.LandingRoute
        ) {
            composable<Routes.LandingRoute> {
                LandingScreen(
                    onLoginClick = {
                        navController.navigate(Routes.LoginRoute)
                    },
                    onSignUpClick = {
                        navController.navigate(Routes.SignUpRoute)
                    }
                )
            }

            composable<Routes.LoginRoute> {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(Routes.DashBoardRoute) {
                            popUpTo(Routes.AuthGraph) { inclusive = true }
                        }
                    },
                    onGoToSignUp = {
                        navController.navigate(Routes.SignUpRoute)
                    }
                )
            }

            composable<Routes.SignUpRoute> {
                SignUpScreen(
                    onRegisterSuccess = {
                        navController.navigate(Routes.DashBoardRoute) {
                            popUpTo(Routes.AuthGraph) { inclusive = true }
                        }
                    },
                    onLoginClick = {
                        navController.navigate(Routes.LoginRoute)
                    }
                )
            }
        }

        composable<Routes.DashBoardRoute> {
            val context = LocalContext.current as ComponentActivity
            val storeViewModel: StoreViewModel = koinViewModel(
                viewModelStoreOwner = context
            )

            DashboardScreen(
                onProfileClick = {
                    navController.navigate(Routes.ProfileRoute)
                },
                onAnjeminClick = {
                    navController.navigate(Routes.AnterinGraph)
                },
                onJajaninClick = {
                    navController.navigate(Routes.JajaninMainRoute)
                },
                onJastipinClick = {
                    navController.navigate(Routes.JastipinMainRoute)
                },
                onSearchClick = {
                    navController.navigate(Routes.GlobalSearchRoute)
                },
                onOrderStatusClick = { navController.navigate(Routes.OrderStatusGlobalRoute) },
                onOrderHistoryClick = { navController.navigate(Routes.OrderHistoryRoute) },
                onStoreClick = { store ->
                    storeViewModel.selectStore(store)
                    navController.navigate(
                        Routes.JajaninDetailRoute
                    )
                }
            )
        }

        composable<Routes.ProfileRoute> {
            ProfileMainScreen(
                onBack = {
                    navController.popBackStack()
                },
                onHomeClick = {
                    navController.navigate(Routes.DashBoardRoute) {
                        popUpTo(Routes.DashBoardRoute) { inclusive = true }
                    }
                },
                onLogoutSuccess = {
                    navController.navigate(Routes.AuthGraph) {
                        popUpTo<Routes.DashBoardRoute> { inclusive = true }
                    }
                },
                onOrderStatusClick = {
                    navController.navigate(Routes.OrderStatusGlobalRoute) {
                        launchSingleTop = true
                    }
                },
                onOrderHistoryClick = {
                    navController.navigate(Routes.OrderHistoryRoute)
                },
                onFullProfileClick = {
                    navController.navigate(Routes.ProfileFullRoute)
                }
            )
        }

        composable<Routes.ProfileFullRoute> {
            ProfileFullScreen(
                onBack = {
                    navController.popBackStack()
                },
                onHomeClick = {
                    navController.navigate(Routes.DashBoardRoute) {
                        popUpTo(Routes.DashBoardRoute) { inclusive = true }
                    }
                },
                onOrderStatusClick = {
                    navController.navigate(Routes.OrderStatusGlobalRoute) {
                        launchSingleTop = true
                    }
                },
                onOrderHistoryClick = {
                    navController.navigate(Routes.OrderHistoryRoute)
                }
            )
        }

        composable<Routes.OrderHistoryRoute> {
            OrderHistoryScreen(
                onBack = {
                    navController.popBackStack()
                },
                onHomeClick = {
                    navController.navigate(Routes.DashBoardRoute) {
                        popUpTo(Routes.DashBoardRoute) { inclusive = true }
                    }
                },
                onOrderStatusClick = {
                    navController.navigate(Routes.OrderStatusGlobalRoute) {
                        launchSingleTop = true
                    }
                },
                onProfileClick = {
                    navController.navigate(Routes.ProfileRoute)
                }
            )
        }

        composable<Routes.JajaninChatRoute> {
            ChatWithDriverPage(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        navigation<Routes.AnterinGraph>(
            startDestination = Routes.AnterPickupInputRoute
        ) {
            composable<Routes.AnterPickupInputRoute> { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Routes.AnterinGraph)
                }
                val anterinViewModel: AnterinViewModel = koinViewModel(
                    viewModelStoreOwner = parentEntry
                )
                AnterinMainPage(
                    mode = MainMode.PICKUP_ONLY,
                    onPickupClick = {
                        navController.navigate(Routes.AnterPickupMapRoute)
                    },
                    onDestinationClick = {},
                    onBack = {
                        navController.popBackStack()
                    },
                    viewModel = anterinViewModel
                )
            }

            composable<Routes.AnterPickupMapRoute> { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Routes.AnterinGraph)
                }
                val anterinViewModel: AnterinViewModel = koinViewModel(
                    viewModelStoreOwner = parentEntry
                )
                AnterinSearchPage(
                    mode = MapMode.PICKUP,
                    onConfirm = {
                        navController.navigate(Routes.AnterDestinationInputRoute)
                    },
                    onBack = {
                        navController.popBackStack()
                    },
                    viewModel = anterinViewModel
                )
            }

            composable<Routes.AnterDestinationInputRoute> { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Routes.AnterinGraph)
                }
                val anterinViewModel: AnterinViewModel = koinViewModel(
                    viewModelStoreOwner = parentEntry
                )
                AnterinMainPage(
                    mode = MainMode.PICKUP_AND_DESTINATION,
                    onPickupClick = {
                        navController.navigate(Routes.AnterPickupMapRoute)
                    },
                    onDestinationClick = {
                        navController.navigate(Routes.AnterDestinationMapRoute)
                    },
                    onBack = {
                        navController.popBackStack()
                    },
                    viewModel = anterinViewModel
                )
            }

            composable<Routes.AnterDestinationMapRoute> { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Routes.AnterinGraph)
                }
                val anterinViewModel: AnterinViewModel = koinViewModel(
                    viewModelStoreOwner = parentEntry
                )
                AnterinSearchPage(
                    mode = MapMode.DESTINATION,
                    onConfirm = {
                        navController.navigate(Routes.AnterDestinationSetRoute)
                    },
                    onBack = {
                        navController.popBackStack()
                    },
                    viewModel = anterinViewModel
                )
            }

            composable<Routes.AnterDestinationSetRoute> { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Routes.AnterinGraph)
                }
                val anterinViewModel: AnterinViewModel = koinViewModel(
                    viewModelStoreOwner = parentEntry
                )
                AnterinDestinationSetPage(
                    onBack = {
                        navController.popBackStack()
                    },
                    onFindDriver = {
                        navController.navigate(Routes.AnterFindingDriverRoute)
                    },
                    viewModel = anterinViewModel
                )
            }

            composable<Routes.AnterFindingDriverRoute> {
                FindingDriverPage(
                    serviceName = "Anter-In",
                    onBack = {
                        navController.popBackStack()
                    },
                    onFinished = {
                        navController.navigate(Routes.AnterOrderStatusRoute) {
                            popUpTo(Routes.AnterFindingDriverRoute) { inclusive = true }
                        }
                    }
                )
            }

            composable<Routes.AnterOrderStatusRoute> { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Routes.AnterinGraph)
                }
                val anterinViewModel: AnterinViewModel = koinViewModel(
                    viewModelStoreOwner = parentEntry
                )
                AnterinOrderStatusPage(
                    onBack = {
                        navController.popBackStack()
                    },
                    onHomeClick = {
                        navController.navigate(Routes.DashBoardRoute) {
                            popUpTo<Routes.DashBoardRoute> {
                                inclusive = false
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    onProfileClick = {
                        navController.navigate(Routes.ProfileRoute)
                    },
                    onChatClick = {
                        navController.navigate(Routes.JajaninChatRoute)
                    },
                    onOrderHistoryClick = {
                        navController.navigate(Routes.OrderHistoryRoute)
                    },
                    viewModel = anterinViewModel
                )
            }
        }

        navigation<Routes.DeliveryGraph>(
            startDestination = Routes.JajaninMainRoute
        ) {
            composable<Routes.GlobalSearchRoute> {
                val context = LocalContext.current as ComponentActivity
                val storeViewModel: StoreViewModel = koinViewModel(
                    viewModelStoreOwner = context
                )
                DeliveryMainPage(
                    type = StoreType.SEARCH,
                    onBack = {
                        navController.popBackStack()
                    },
                    onStoreClick = {
                        navController.navigate(Routes.JajaninDetailRoute)
                    },
                    viewModel = storeViewModel
                )
            }

            composable<Routes.JajaninMainRoute> {
                val context = LocalContext.current as ComponentActivity
                val storeViewModel: StoreViewModel = koinViewModel(
                    viewModelStoreOwner = context
                )
                DeliveryMainPage(
                    type = StoreType.FOOD,
                    onBack = {
                        navController.popBackStack()
                    },
                    onStoreClick = {
                        navController.navigate(Routes.JajaninDetailRoute)
                    },
                    viewModel = storeViewModel
                )
            }

            composable<Routes.JastipinMainRoute> {
                val context = LocalContext.current as ComponentActivity
                val storeViewModel: StoreViewModel = koinViewModel(
                    viewModelStoreOwner = context
                )
                DeliveryMainPage(
                    type = StoreType.RETAIL,
                    onBack = {
                        navController.popBackStack()
                    },
                    onStoreClick = {
                        navController.navigate(Routes.JajaninDetailRoute)
                    },
                    viewModel = storeViewModel
                )
            }

            composable<Routes.JajaninDetailRoute> { backStackEntry ->
                val context = LocalContext.current as ComponentActivity
                val storeViewModel: StoreViewModel = koinViewModel(
                    viewModelStoreOwner = context
                )

                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Routes.DeliveryGraph)
                }
                val cartViewModel: CartViewModel = koinViewModel(
                    viewModelStoreOwner = parentEntry
                )

                DeliveryDetailPage(
                    onBack = {
                        navController.popBackStack()
                    },
                    onCartClick = {
                        navController.navigate(Routes.CartRoute)
                    },
                    viewModel = storeViewModel,
                    cartViewModel = cartViewModel
                )
            }

            composable<Routes.CartRoute> { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Routes.DeliveryGraph)
                }
                val cartViewModel: CartViewModel = koinViewModel(
                    viewModelStoreOwner = parentEntry
                )
                CartPage(
                    onBack = {
                        navController.popBackStack()
                    },
                    onCheckout = {
                        navController.navigate(Routes.JajaninFindingDriverRoute)
                    },
                    viewModel = cartViewModel
                )
            }

            composable<Routes.JajaninFindingDriverRoute> {
                FindingDriverPage(
                    serviceName = "Jajan-In",
                    onBack = {
                        navController.popBackStack()
                    },
                    onFinished = {
                        navController.navigate(Routes.JajaninOrderStatusRoute) {
                            popUpTo(Routes.JajaninFindingDriverRoute) { inclusive = true }
                        }
                    }
                )
            }

            composable<Routes.JajaninOrderStatusRoute> { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Routes.DeliveryGraph)
                }
                val cartViewModel: CartViewModel = koinViewModel(
                    viewModelStoreOwner = parentEntry
                )
                DeliveryOrderStatusPage(
                    onBack = {
                        navController.popBackStack()
                    },
                    onHomeClick = {
                        navController.navigate(Routes.DashBoardRoute) {
                            popUpTo<Routes.DashBoardRoute> {
                                inclusive = false
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    onProfileClick = {
                        navController.navigate(Routes.ProfileRoute)
                    },
                    onChatClick = {
                        navController.navigate(Routes.JajaninChatRoute)
                    },
                    onOrderHistoryClick = {
                        navController.navigate(Routes.OrderHistoryRoute)
                    },
                    viewModel = cartViewModel
                )
            }
        }
    }
}