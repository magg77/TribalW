// Top-level build file where you can add configuration options common to all sub-projects/modules.


buildscript {
    val kotlinVersionRoot by extra("2.0.21")
    val nav_version_root by extra("2.7.4")

    val gson_version by extra("2.10")
    val logging_interceptor by extra("4.11.0")
    val coroutines_android by extra("1.10.1")

    val nav_version by extra("2.8.6")
    val materialDesing by extra("1.9.0")
    val liveData by extra("2.8.7")
    val recyclerView by extra("1.3.2")
    val recyclerview_selection by extra("1.1.0")

    val room_version by extra("2.5.0")
    val retrofit by extra("2.9.0")
    val hilt_version by extra("2.55")

    val compose_ui by extra("1.5.3")
    /*val compose_navigation by extra("2.6.0")*/
    val compose_navigation by extra("2.7.4")

    val API_URL_BASE_DEV by extra("https://www.api.com./")
    val API_URL_BASE_PROD by extra("https://www.api.com./")

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.8.0")               //AGP version
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${rootProject.extra["kotlinVersionRoot"]}")

        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${rootProject.extra["nav_version_root"]}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${rootProject.extra["hilt_version"]}")
    }

}

plugins {
    id("com.android.application") version "8.8.0" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false

    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false

    id("com.google.dagger.hilt.android") version("2.44") apply false
    id("com.google.gms.google-services") version "4.4.0" apply false  // Add the dependency for the Google services Gradle plugin
}

allprojects {
    repositories {
        //google()
        //mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}