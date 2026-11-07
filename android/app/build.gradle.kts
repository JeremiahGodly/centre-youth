plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "org.marthoma.centreyouth"
    compileSdk = 35

    defaultConfig {
        applicationId = "org.marthoma.centreyouth"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        resourceConfigurations += listOf("en", "ml")
    }

    signingConfigs {
        create("release") {
            val possibleKeystores = listOf(
                project.rootProject.file("release.keystore"),
                project.file("release.keystore"),
                file("../release.keystore"),
                file("release.keystore")
            )
            val keystoreFile = possibleKeystores.firstOrNull { it.exists() }
            if (keystoreFile != null) {
                storeFile = keystoreFile
                storePassword = System.getenv("KEYSTORE_PASSWORD") ?: "centreYouth2026"
                keyAlias = System.getenv("KEY_ALIAS") ?: "centreyouth"
                keyPassword = System.getenv("KEY_PASSWORD") ?: "centreYouth2026"
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            val possibleKeystores = listOf(
                project.rootProject.file("release.keystore"),
                project.file("release.keystore"),
                file("../release.keystore"),
                file("release.keystore")
            )
            if (possibleKeystores.any { it.exists() }) {
                signingConfig = signingConfigs.getByName("release")
            } else {
                signingConfig = signingConfigs.getByName("debug")
            }
        }
        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
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
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.splashscreen)

    // Jetpack Compose (Core BOM)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)

    // Media3 & Audio Playback Service
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.session)
    implementation(libs.androidx.media3.ui)

    // WorkManager for background daily scripture & notifications
    implementation(libs.androidx.work.runtime.ktx)

    // Image loading
    implementation(libs.coil.compose)

    // JSON parsing
    implementation(libs.gson)

    debugImplementation(libs.androidx.compose.ui.tooling)
}
