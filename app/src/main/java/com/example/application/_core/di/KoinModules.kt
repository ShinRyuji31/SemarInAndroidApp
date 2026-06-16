package com.example.application._core.di

import com.example.application.BuildConfig
import com.example.application.anterin.data.repository.AnterinRepository
import com.example.application._core.data.maps.repository.MapsRepository
import com.example.application.anterin.ui.viewmodel.AnterinViewModel
import com.example.application.auth.data.repository.UserRepository
import com.example.application.auth.ui.viewmodel.LoginViewModel
import com.example.application.profile.ui.viewmodel.ProfileViewModel
import com.example.application.auth.ui.viewmodel.SignUpViewModel
import com.example.application.chat.data.repository.ChatRepository
import com.example.application.chat.ui.viewmodel.ChatViewModel
import com.example.application.dashboard.data.repository.DashboardRepository
import com.example.application.dashboard.ui.viewmodel.DashboardViewModel
import com.example.application.delivery.data.local.CartDataStore
import com.example.application.delivery.data.repository.CartRepository
import com.example.application.delivery.data.repository.SupabaseStoreRepository
import com.example.application.delivery.ui.viewmodel.CartViewModel
import com.example.application.delivery.ui.viewmodel.StoreViewModel
import com.example.application._core.data.location.LocationRepository
import com.example.application._core.data.location.LocationService
import com.example.application._core.data.location.LocationViewModel
import com.example.application.orderhistory.data.repository.OrderHistoryRepository
import com.example.application.orderhistory.viewmodel.OrderHistoryViewModel
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val appModule = module {
    // Supabase Client
    single {
        createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_KEY
        ) {
            install(Auth)
            install(Postgrest)
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

    // Services & DataStore
    single { LocationService(androidContext()) }
    single { CartDataStore(androidContext()) }
}

val repositoryModule = module {
    single { UserRepository(get()) }
    single { AnterinRepository() }
    single { MapsRepository(get()) }
    single { ChatRepository() }
    single { DashboardRepository(get()) }
    single { CartRepository(get()) }
    single { SupabaseStoreRepository(get()) }

    single { LocationRepository(get()) }
    single { OrderHistoryRepository(get()) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { AnterinViewModel(get(), get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { DashboardViewModel(get(), get()) }
    viewModel { StoreViewModel(get()) }
    viewModel { CartViewModel(get()) }

    viewModel { ChatViewModel(get()) }
    viewModel { OrderHistoryViewModel(get()) }
}
