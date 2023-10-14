import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id("org.jlleitschuh.gradle.ktlint") version "11.6.0"

    kotlin("plugin.serialization") version "1.8.10"

    id("com.google.dagger.hilt.android")
    kotlin("kapt")
    id("com.google.devtools.ksp")
    id("io.realm.kotlin") apply true
}

val prop = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "apiKeys.properties")))
}
val baseUrl: String = prop.getProperty("BASE_URL") ?: ""
val oauthWebClientId: String = prop.getProperty("OAUTH-WEB-CLIENT-ID") ?: ""
val mongoServiceId: String = prop.getProperty("MONGO_SERVICE_ID") ?: ""
val ixStorePassword: String = prop.getProperty("STORE_PASSWORD")
val ixKeyPassword: String = prop.getProperty("KEY_PASSWORD")
val ixKeyAlias: String = prop.getProperty("KEY_ALIAS")
val ixKeystorePath: String = prop.getProperty("KEYSTORE_PATH")

android {
    namespace = "com.ix.diary"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ix.diary"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.ix.diary.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }


    buildTypes {
        release {
            buildConfigField("String", "baseUrl", "\"$baseUrl\"")
            buildConfigField("String", "oauthWebClientId", "\"$oauthWebClientId\"")
            buildConfigField("String", "mongoServiceId", "\"$mongoServiceId\"")

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            signingConfigs {
                all {
                    storePassword = ixStorePassword
                    keyPassword = ixKeyPassword
                    keyAlias = ixKeyAlias
                    storeFile = file(ixKeystorePath)
                }
            }
        }
        debug {
            buildConfigField("String", "baseUrl", "\"$baseUrl\"")
            buildConfigField("String", "oauthWebClientId", "\"$oauthWebClientId\"")
            buildConfigField("String", "mongoServiceId", "\"$mongoServiceId\"")
            versionNameSuffix = "debug"
            signingConfigs {
                all {
                    storePassword = ixStorePassword
                    keyPassword = ixKeyPassword
                    keyAlias = ixKeyAlias
                    storeFile = file(ixKeystorePath)
                }
            }
        }
    }
    signingConfigs {
        all {
            storePassword = ixStorePassword
            keyPassword = ixKeyPassword
            keyAlias = ixKeyAlias
            storeFile = file(ixKeystorePath)
        }
    }
//    signingConfigs {
//        debug {
//            storePassword = ixStorePassword
//            keyPassword = ixKeyPassword
//            keyAlias = ixKeyAlias
//            storeFile = file(ixKeystorePath)
//        }
//        create("release") {
//            storePassword = ixStorePassword
//            keyPassword = ixKeyPassword
//            keyAlias = ixKeyAlias
//            storeFile = file(ixKeystorePath)
//        }
//    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE*"
        }
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    val navVersion = "2.7.4"
    val roomVersion = "2.5.2"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")

    implementation(platform("androidx.compose:compose-bom:2023.09.02"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Navigation
    implementation("androidx.navigation:navigation-compose:$navVersion")
    androidTestImplementation("androidx.navigation:navigation-testing:$navVersion")

    // Network
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

    // Firebase
    implementation("com.google.firebase:firebase-auth-ktx:22.1.2")
    implementation("com.google.firebase:firebase-storage-ktx:20.2.1")

    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Image loading
    implementation("io.coil-kt:coil-compose:2.4.0")
    // implementation("io.coil-kt:coil:2.4.0")

    // State
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0-alpha02")

    // Splash API
    implementation("androidx.core:core-splashscreen:1.0.1")

    // MongoDB Realm
    //noinspection GradleDependency
    implementation("io.realm.kotlin:library-base:1.11.0")
    implementation("io.realm.kotlin:library-sync:1.11.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // Dependency Injection
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.48")

    // Google Auth
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    // Pager - Accompanist
    implementation("com.google.accompanist:accompanist-pager:0.27.0")

    // Date-Time Picker
    implementation("io.github.vanpra.compose-material-dialogs:datetime:0.9.0")

    // Message Bar Compose
    implementation("com.github.stevdza-san:MessageBarCompose:1.0.5")

    // One-Tap Compose
    implementation("com.github.stevdza-san:OneTapCompose:1.0.0")

    // Desugar JDK
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")

    // Logging
    implementation("com.jakewharton.timber:timber:5.0.1")

    // Testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation("io.mockk:mockk-android:1.13.8")
    androidTestImplementation("io.mockk:mockk-android:1.13.8")
}

kapt {
    correctErrorTypes = true
}
