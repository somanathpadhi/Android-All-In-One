plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)

    //hilt changes 2
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.androidallinone"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.androidallinone"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    // Retrofit Core
    implementation("com.squareup.retrofit2:retrofit:2.12.0")

    // Retrofit Gson Converter
    implementation("com.squareup.retrofit2:converter-gson:2.12.0")

    // OkHttp Logging Interceptor
    implementation(libs.okhttp.logging.interceptor)

    //hilt changes 3
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // For local unit tests (running on your computer / JVM)
    testImplementation("io.mockk:mockk:1.14.11")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.1")

    // For instrumented tests (running on a real device or emulator)
    androidTestImplementation("io.mockk:mockk-android:1.14.11")
    androidTestImplementation("io.mockk:mockk-agent:1.14.11")
}