package com.example.application.driver._core.di

import com.example.application.driver._core.data.repository.DriverRepository
import com.example.application.driver._core.ui.viewmodel.DriverAuthViewModel
import com.example.application.driver.dashboard.ui.viewmodel.DriverDashboardViewModel
import com.example.application.driver._core.ui.viewmodel.DriverSignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val driverRepositoryModule = module {
    single { DriverRepository(get()) }
}

val driverViewModelModule = module {
    viewModel { DriverAuthViewModel(get(), get()) }
    viewModel { DriverDashboardViewModel() }
    viewModel { DriverSignUpViewModel(get()) }
}

val driverKoinModules = listOf(
    driverRepositoryModule,
    driverViewModelModule
)