package com.example.application.driver._core.di

import com.example.application.order.data.repository.OrderRepository
import com.example.application.driver.auth.ui.viewmodel.DriverAuthViewModel
import com.example.application.driver._core.ui.viewmodel.CoreViewmodel
import com.example.application.driver.auth.ui.viewmodel.DriverSignUpViewModel
import com.example.application.driver.dashboard.ui.viewmodel.DashboardViewModel
import com.example.application._core.orderhistory.viewmodel.OrderHistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val orderRepositoryModule = module {
    single { OrderRepository(get()) }
}

val driverViewModelModule = module {
    viewModel { DriverAuthViewModel(get(), get()) }
    single { CoreViewmodel(get(), get(), get()) }
    viewModel { DashboardViewModel(get(), get()) }
    viewModel { DriverSignUpViewModel(get()) }
    viewModel { OrderHistoryViewModel(get(), get(), isDriver = true) }
}

val driverKoinModules = listOf(
    orderRepositoryModule,
    driverViewModelModule
)