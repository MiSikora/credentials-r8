plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "io.mehow.credentials"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "io.mehow.credentials"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.activity)
    implementation(libs.androidx.credentials.providerevents)
    implementation(libs.androidx.credentials.providerevents.play.services)
}
