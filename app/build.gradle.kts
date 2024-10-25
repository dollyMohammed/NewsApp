plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.questionapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.questionapp"
        minSdk = 26
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.51")
    kapt ("com.google.dagger:hilt-android-compiler:2.51")
    kapt ("androidx.hilt:hilt-compiler:1.2.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation ("androidx.compose.material3:material3:1.3.0") // تأكد من إصدار أحدث متاح

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
    // implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")
    // implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
//
    // Coil
    implementation("io.coil-kt:coil-compose:2.6.0")

    //firebase
    //implementation(platform("com.google.firebase:firebase-bom:32.3.0"))
    // implementation ("com.google.firebase:firebase-common:21.0.0")
    //implementation("com.google.firebase:firebase-firestore:24.10.3")

    //implementation("com.google.firebase:firebase-storage")
    //implementation("com.google.firebase:firebase-auth")
    //implementation ("com.google.firebase:firebase-common")
    //implementation("com.google.firebase:firebase-auth-ktx:23.0.0")
    //implementation("com.google.firebase:firebase-firestore-ktx:25.0.0")
    // implementation("com.google.firebase:firebase-storage-ktx:21.0.0")
    // implementation ("com.google.firebase:firebase-auth:23.0.0")
    // implementation (platform("com.google.firebase:firebase-bom:32.7.3"))
    // implementation ("com.google.firebase:firebase-auth-ktx:23.0.0")
    //implementation ("com.google.firebase:firebase-firestore-ktx:25.0.0")
    //implementation ("com.google.firebase:firebase-storage-ktx:21.0.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    // Room components
    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")

    // Room with Kotlin extensions
    implementation ("androidx.room:room-ktx:2.6.1")
    //Desuger
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.2")
    implementation ("androidx.compose.foundation:foundation:1.7.4")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.30.1")
//
    // OkHttp
//    implementation("com.squareup.okhttp3:okhttp:4.10.0")
//
    // JSON Converter
    //  implementation("com.squareup.retrofit2:converter-gson:2.9.0")
//
    //preferences
//    implementation("androidx.datastore:datastore-preferences:1.1.0-beta01")

    //swipe refresh
    //  implementation("com.google.accompanist:accompanist-swiperefresh:0.25.1")

    //implementation("androidx.appcompat:appcompat:1.6.1")

    /*implementation("com.google.firebase:firebase-firestore:24.10.3") {
        exclude(group = "com.google.firebase", module = "firebase-common")
    }*/


}
kapt {
    correctErrorTypes = true
}
