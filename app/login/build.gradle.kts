plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services") version "4.3.15" apply true


}

android {
    namespace = "com.dnd_9th_3_android.gooding.login"
    compileSdk = 34

    defaultConfig {

        minSdk = 30

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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    buildFeatures {
        compose = true
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // 카카오 로그인
    implementation("com.kakao.sdk:v2-user:2.14.0")
    // 구글 로그인
    implementation("com.google.android.gms:play-services-auth:20.6.0")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.45")
    kapt("com.google.dagger:hilt-compiler:2.45")

    // 구글 로그인 모듈
    implementation(platform("com.google.firebase:firebase-bom:32.1.0"))
    implementation ("com.google.firebase:firebase-analytics-ktx")
    implementation ("com.google.firebase:firebase-auth-ktx")

    // compose
    val composeVersion = "1.4.3"
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation ("androidx.compose.ui:ui-test-junit4:$composeVersion")
    implementation ("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation ("androidx.compose.ui:ui-test-manifest:$composeVersion")
    // compose view model
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")
    // image ui
    implementation ("io.coil-kt:coil-compose:1.3.2")
    // compose navi
    implementation("androidx.navigation:navigation-compose:2.7.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")
    // live data
    implementation ("androidx.compose.runtime:runtime-livedata:1.3.1")
    // module
    implementation(project(":core:model"))
    implementation(project(":core:data"))
    implementation(project(":core:api"))
}