package com.example.application.driver._core.di

import com.example.application.driver.order.data.repository.OrderRepository
import com.example.application.driver.auth.ui.viewmodel.DriverAuthViewModel
import com.example.application.driver.dashboard.ui.viewmodel.DriverDashboardViewModel
import com.example.application.driver.auth.ui.viewmodel.DriverSignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val orderRepositoryModule = module {
    single { OrderRepository(get()) }
}

val driverViewModelModule = module {
    viewModel { DriverAuthViewModel(get(), get()) }
    viewModel { DriverDashboardViewModel(get(), get(), get()) }
    viewModel { DriverSignUpViewModel(get()) }
}

val driverKoinModules = listOf(
    orderRepositoryModule,
    driverViewModelModule
)