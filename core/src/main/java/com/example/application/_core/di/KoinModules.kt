package com.example.application._core.di

import com.example.application.core.BuildConfig
import com.example.application.auth.data.repository.UserRepository
import com.example.application.auth.ui.viewmodel.LoginViewModel
import com.example.application.auth.ui.viewmodel.SignUpViewModel
import com.example.application.chat.data.repository.ChatRepository
import com.example.application.chat.ui.viewmodel.ChatViewModel
import com.example.application._core.data.maps.repository.MapsRepository
import com.example.application._core.data.location.LocationRepository
import com.example.application._core.data.location.LocationService
import com.example.application._core.data.location.LocationViewModel
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val coreAppModule = module {
    // Supabase Client
    single {
        createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_KEY
        ) {
            install(Auth)
            install(Postgrest)
            install(Realtime)
        }
    }
    
    // Ktor HttpClient
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                })
            }
        }
    }

    // Services
    single { LocationService(androidContext()) }
}

val coreRepositoryModule = module {
    single { UserRepository(get()) }
    single { MapsRepository(get()) }
    single { ChatRepository() }
    single { LocationRepository(get()) }
}

val coreViewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { ChatViewModel(get()) }
}
