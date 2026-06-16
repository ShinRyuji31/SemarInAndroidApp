package com.example.application.driver

import android.app.Application
import com.example.application._core.di.coreAppModule
import com.example.application._core.di.coreRepositoryModule
import com.example.application._core.di.coreViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DriverApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@DriverApplication)
            modules(
                listOf(
                    coreAppModule,
                    coreRepositoryModule,
                    coreViewModelModule
                )
            )
        }
    }
}
