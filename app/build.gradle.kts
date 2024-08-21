plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.mynotes"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mynotes"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures{
        viewBinding =true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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

    //Room Database
    implementation ("androidx.room:room-runtime:2.3.0")
    annotationProcessor ("androidx.room:room-compiler:2.3.0")

    // Android Architecture Components
    val lifecycle_version = "2.3.1"
    implementation ("androidx.lifecycle:lifecycle-livedata:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version")
    implementation ("android.arch.lifecycle:extensions:1.1.1")

}