package com.example.application

import android.app.Application
import com.example.application._core.coreKoinModules
import com.example.application._core.di.customerKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CustomerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CustomerApplication)
            modules(coreKoinModules + customerKoinModules)
        }
    }
}
