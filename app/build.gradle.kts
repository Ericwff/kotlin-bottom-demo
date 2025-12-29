plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
//    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.bottomnavigationapplication"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.bottomnavigationapplication"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"http://172.16.106.119:4523/m1/6651049-6359122-default/\""
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "BASE_URL",
                "\"http://172.16.106.119:4523/m1/6651049-6359122-default\""
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("com.squareup.okhttp3:okhttp:5.3.1")
    implementation("com.squareup.okhttp3:logging-interceptor:5.3.1")
    implementation("com.google.code.gson:gson:2.13.2")

    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")

    implementation("com.github.bumptech.glide:glide:5.0.5")
    ksp("com.github.bumptech.glide:compiler:5.0.5")

    val room_version = "2.8.4"
    implementation("androidx.room:room-runtime:$room_version")
//    kapt("androidx.room:room-compiler:2.6.1") // For annotation processing
    ksp("androidx.room:room-compiler:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version") // Optional for coroutines
    // optional - RxJava2 support for Room
    implementation("androidx.room:room-rxjava2:${room_version}")
// optional - RxJava3 support for Room
    implementation("androidx.room:room-rxjava3:${room_version}")
    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation("androidx.room:room-guava:${room_version}")
// optional - Test helpers
    testImplementation("androidx.room:room-testing:${room_version}")
// optional - Paging 3 Integration
    implementation("androidx.room:room-paging:${room_version}")

    implementation("androidx.datastore:datastore-preferences:1.1.1")
}