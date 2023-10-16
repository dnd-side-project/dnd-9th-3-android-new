import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.dnd_9th_3_android.gooding.core.data"
    compileSdk = 34

    defaultConfig {

        minSdk = 30

        consumerProguardFiles("consumer-rules.pro")

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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

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
    // tab + pager
    implementation("com.google.accompanist:accompanist-pager:0.20.1")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.20.1")
    // compose navi
    implementation ("androidx.fragment:fragment-ktx:1.5.4")
    implementation("androidx.navigation:navigation-compose:2.7.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")


    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    val pagingVersion = "3.1.1"
    // paging dependencies 추가
    implementation("androidx.paging:paging-runtime:$pagingVersion")
    // optional - Jetpack Compose integration
    implementation("androidx.paging:paging-compose:1.0.0-alpha17")

    val roomVersion = "2.5.1"
    implementation ("androidx.room:room-runtime:$roomVersion")
    implementation ("androidx.room:room-ktx:$roomVersion")
    kapt ("androidx.room:room-compiler:$roomVersion")
    implementation ("androidx.room:room-paging:$roomVersion")
    testImplementation ("androidx.room:room-testing:$roomVersion")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.45")
    kapt("com.google.dagger:hilt-compiler:2.45")
    kapt("com.google.dagger:hilt-android-compiler:2.45")
    // compose hilt viewModel
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.squareup.okhttp3:okhttp-urlconnection:4.9.1")

    // gson
    implementation("com.google.code.gson:gson:2.9.0")

    // module
    implementation(project(":core:model"))
    implementation(project(":core:api"))
}