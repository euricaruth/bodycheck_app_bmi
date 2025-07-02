plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.appbodycheck"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.appbodycheck"
        minSdk = 21
        targetSdk = 35
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.core.splashscreen)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler) // Untuk proyek Java, gunakan annotationProcessor
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.viewmodel)
}