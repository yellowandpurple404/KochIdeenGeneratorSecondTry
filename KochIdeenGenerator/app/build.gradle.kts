plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.example.kochideengenerator"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.kochideengenerator"
        minSdk = 27
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt {
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas".toString())

        }
    }
}

    dependencies {
        val lifecycle_version = "2.6.2"
        val arch_version = "2.2.0"

        // ViewModel
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
        // ViewModel utilities for Compose
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")

        //coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

        implementation("androidx.core:core-ktx:1.9.0")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
        implementation("androidx.activity:activity-compose:1.7.2")
        implementation(platform("androidx.compose:compose-bom:2023.03.00"))
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-graphics")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.compose.material3:material3")
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")

        //room
        /*

            implementation("androidx.room:room-runtime:$room_version")
            //annotationProcessor("androidx.room:room-compiler:$room_version")

            // To use Kotlin annotation processing tool (kapt)
            kapt("androidx.room:room-compiler:$room_version")*/
        val roomVersion = "2.2.3"
        implementation("androidx.room:room-ktx:$roomVersion")
        implementation("androidx.room:room-runtime:$roomVersion")
        implementation("androidx.room:room-guava:$roomVersion")
        kapt("androidx.room:room-compiler:$roomVersion")
        testImplementation("androidx.room:room-testing:$roomVersion")
        annotationProcessor("androidx.room:room-compiler:$roomVersion")

        //coroutines
        api ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$rootProject.coroutines")
        api ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$rootProject.coroutines")

        //implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$1.8.20")

    }