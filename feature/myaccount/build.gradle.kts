import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.dnd_9th_3_android.gooding.feature.my"
    compileSdk = 34

    defaultConfig {

        minSdk = 30

        consumerProguardFiles("consumer-rules.pro")


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        compose = true
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.45")
    kapt("com.google.dagger:hilt-compiler:2.45")

    // compose
    val composeVersion = "1.4.3"
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation ("androidx.compose.ui:ui-test-junit4:$composeVersion")
    implementation ("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation ("androidx.compose.ui:ui-test-manifest:$composeVersion")
    implementation("androidx.activity:activity-compose:1.7.2")
    // compose view model
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")
    // compose navi
    implementation ("androidx.fragment:fragment-ktx:1.5.4")
    implementation("androidx.navigation:navigation-compose:2.7.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")
    // image ui
    implementation ("io.coil-kt:coil-compose:1.3.2")
    // tab + pager
    implementation("com.google.accompanist:accompanist-pager:0.20.1")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.20.1")
    // compose slide
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha09")
    // coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3")

    // bottom sheet
    implementation ("com.holix.android:bottomsheetdialog-compose:1.3.1")

    // Exoplayer Dependency
    implementation ("com.google.android.exoplayer:exoplayer:2.16.1")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.squareup.okhttp3:okhttp-urlconnection:4.9.1")


    implementation("com.google.code.gson:gson:2.9.0")
    
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

    implementation ("androidx.compose.runtime:runtime-livedata:1.3.1")
    // module
    implementation(project(":core:model"))
    implementation(project(":core:data"))
    implementation(project(":core:api"))
}