package com.example.application.driver._core.di

import com.example.application.driver.order.data.repository.OrderRepository
import com.example.application.driver.auth.ui.viewmodel.DriverAuthViewModel
import com.example.application.driver._core.ui.viewmodel.CoreViewmodel
import com.example.application.driver.auth.ui.viewmodel.DriverSignUpViewModel
import com.example.application.driver.dashboard.ui.viewmodel.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val orderRepositoryModule = module {
    single { OrderRepository(get()) }
}

val driverViewModelModule = module {
    viewModel { DriverAuthViewModel(get(), get()) }
    single { CoreViewmodel(get(), get(), get()) }
    viewModel { DashboardViewModel(get()) }
    viewModel { DriverSignUpViewModel(get()) }
}

val driverKoinModules = listOf(
    orderRepositoryModule,
    driverViewModelModule
)