plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
//    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.0"
//    id ("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
}

android {
    namespace = "com.dev.intourist"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dev.intourist"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // ViewbindingPropertyDelegate
    implementation(libs.viewbindingpropertydelegate.full)

    // Paging3
    implementation(libs.androidx.paging.runtime.ktx)

    // Google Play Services
    implementation(libs.play.services.auth)
    implementation(libs.play.services.location)

    // SplashScreen
    implementation(libs.androidx.core.splashscreen)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Retrofit & OkHTTP
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    implementation(platform(libs.okhttp3.okhttp.bom))
    implementation(libs.okhttp3.okhttp)
    implementation(libs.okhttp3.logging.interceptor)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    annotationProcessor(libs.androidx.room.compiler)

    // Navigation Component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // JavaX Inject
    implementation(libs.javax.inject)

    // Glide
    implementation(libs.glide)

    // Koin
    implementation(libs.koin.android)

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    // LifeCycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Circle Indicator
    implementation(libs.circleindicator)

    // RangeSeekBar
    implementation(libs.range.seekbar)
    implementation(libs.rangeseekbar)

    // KotlinX Serialization
    implementation(libs.kotlinx.serialization.json)

    // Security
    implementation(libs.androidx.security.crypto)

    // Retrofit Kotlinx Serialization Converter
    implementation(libs.retrofit2.kotlinx.serialization.converter)

    // Kotlin PayBox
    implementation (libs.kotlin.paybox.sdk)

    // Google Service wallet
    implementation (libs.play.services.wallet)

    // PayBox Service
//    implementation (libs.payboxsdk)

    // Retrofit Coroutines
    implementation(libs.retrofit2.kotlin.coroutines.adapter)
}


