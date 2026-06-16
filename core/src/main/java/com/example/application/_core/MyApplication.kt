package com.example.application._core

import com.example.application._core.di.coreAppModule
import com.example.application._core.di.coreRepositoryModule
import com.example.application._core.di.coreViewModelModule

val coreKoinModules = listOf(
    coreAppModule,
    coreRepositoryModule,
    coreViewModelModule
)
