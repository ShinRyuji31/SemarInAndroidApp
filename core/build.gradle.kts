import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.application.core"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        val supabaseUrl = properties.getProperty("SUPABASE_URL")
        val supabaseKey = properties.getProperty("SUPABASE_KEY")

        buildConfigField("String", "SUPABASE_URL", "\"$supabaseUrl\"")
        buildConfigField("String", "SUPABASE_KEY", "\"$supabaseKey\"")
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
}

dependencies {

    api(libs.androidx.compose.foundation)
    api(libs.androidx.core.ktx)
    api(libs.androidx.lifecycle.runtime.ktx)
    api(libs.androidx.activity.compose)

    api(platform(libs.androidx.compose.bom))

    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.graphics)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.material3)

    api("io.github.jan-tennert.supabase:supabase-kt:3.6.0")
    api("io.github.jan-tennert.supabase:postgrest-kt:3.6.0")
    api("io.github.jan-tennert.supabase:auth-kt:3.6.0")

    api("io.ktor:ktor-client-okhttp:3.5.0")

    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    api("androidx.lifecycle:lifecycle-viewmodel-compose:2.10.0")
    api(libs.androidx.navigation.compose)
    api("androidx.datastore:datastore-preferences:1.2.1")
    api("io.coil-kt:coil-compose:2.7.0")
    api("com.google.android.gms:play-services-location:21.3.0")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.11.0")

    // Koin for Dependency Injection
    api("io.insert-koin:koin-android:3.5.6")
    api("io.insert-koin:koin-androidx-compose:3.5.6")
}
