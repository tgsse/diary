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

    implementation(libs.androidx.appcompat)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Navigation
    implementation(libs.androidx.navigation.compose)
    androidTestImplementation(libs.androidx.navigation.testing)

    // Network
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)

    // Serialization
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit2.kotlinx.serialization.converter)

    // Firebase
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.storage.ktx)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    // Image loading
    implementation(libs.coil.compose)
    // implementation("io.coil-kt:coil:2.4.0")

    // State
    implementation(libs.androidx.lifecycle.runtime.compose)

    // Splash API
    implementation(libs.androidx.core.splashscreen)

    // MongoDB Realm
    //noinspection GradleDependency
    implementation(libs.library.base)
    implementation(libs.library.sync)
    implementation(libs.kotlinx.coroutines.core)

    // Dependency Injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.dagger.hilt.android.compiler)

    // Google Auth
    implementation(libs.play.services.auth)

    // Pager - Accompanist
    implementation(libs.accompanist.pager)

    // Date-Time Picker
    implementation(libs.datetime)

    // Message Bar Compose
    implementation(libs.messageBarCompose)

    // One-Tap Compose
    implementation(libs.oneTapCompose)

    // Desugar JDK
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    // Logging
    implementation(libs.timber)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    testImplementation(libs.mockk)
    testImplementation(libs.mockk.android)
    androidTestImplementation(libs.mockk.mockk.android)
}

kapt {
    correctErrorTypes = true
}
