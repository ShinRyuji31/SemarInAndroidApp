package com.example.application._core

import android.app.Application
import com.example.application._core.di.appModule
import com.example.application._core.di.repositoryModule
import com.example.application._core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(appModule, repositoryModule, viewModelModule))
        }
    }
}
