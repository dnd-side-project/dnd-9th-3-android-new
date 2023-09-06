// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://devrepo.kakao.com/nexus/content/groups/public/")
        }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.45")
//        classpath("com.google.gms:google-services:4.3.15")
//        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.7")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
