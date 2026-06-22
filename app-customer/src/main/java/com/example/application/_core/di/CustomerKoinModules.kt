package com.example.application._core.di

import com.example.application._core.data.maps.repository.MapsRepository
import com.example.application.anterin.data.repository.AnterinRepository
import com.example.application.anterin.ui.viewmodel.AnterinViewModel
import com.example.application.auth.data.repository.UserRepository
import com.example.application.dashboard.data.repository.DashboardRepository
import com.example.application.dashboard.ui.viewmodel.DashboardViewModel
import com.example.application.delivery.data.local.CartDataStore
import com.example.application.delivery.data.repository.CartRepository
import com.example.application.delivery.data.repository.SupabaseStoreRepository
import com.example.application.delivery.ui.viewmodel.CartViewModel
import com.example.application.delivery.ui.viewmodel.StoreViewModel
import com.example.application.globalorderstatus.ui.viewmodel.OrderStatusGlobalViewmodel
import com.example.application.order.data.repository.OrderRepository
import com.example.application.orderhistory.data.repository.OrderHistoryRepository
import com.example.application.orderhistory.viewmodel.OrderHistoryViewModel
import com.example.application.profile.ui.viewmodel.ProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val customerAppModule = module {
    single { CartDataStore(androidContext()) }
}

val customerRepositoryModule = module {
    single { UserRepository(get()) }
    single { AnterinRepository() }
    single { DashboardRepository(get()) }
    single { CartRepository(get(), get(), get()) }
    single { SupabaseStoreRepository(get()) }
    single { OrderHistoryRepository(get()) }
    single { OrderRepository(get()) }
}

val customerViewModelModule = module {
    viewModel { ProfileViewModel(get()) }
    viewModel { AnterinViewModel(get(), get()) }
    viewModel { DashboardViewModel(get(), get(), get()) }
    viewModel { StoreViewModel(get()) }
    viewModel { CartViewModel(get(), get(), get()) }
    viewModel { OrderHistoryViewModel(get(), get()) }
    viewModel { OrderStatusGlobalViewmodel(get(), get()) }
}

val customerKoinModules = listOf(
    customerAppModule,
    customerRepositoryModule,
    customerViewModelModule
)