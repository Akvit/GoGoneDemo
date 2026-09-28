plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)

    id("com.google.devtools.ksp") version "2.3.6"
}

android {
    namespace = "com.azhel.gogonedemoapp"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.azhel.gogonedemoapp"
        minSdk = 29
        targetSdk = 37
        versionCode = 2
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true

            ndk {
                debugSymbolLevel = "FULL"
            }

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.koin.androidx.compose)
    implementation(libs.androidx.compose.material3.adaptive.navigation3)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    ksp(libs.androidx.room.compiler)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)

    implementation(libs.androidx.core.splashscreen)
    implementation(libs.timber)
    debugImplementation(libs.leakcanary.android)

    implementation(project(":features:irregularverbs"))
    implementation(project(":features:settings"))
    implementation(project(":designsystem"))
    implementation(project(":navigation"))
    implementation(project(":features:setingsapi"))
    implementation(project(":analytic"))
}