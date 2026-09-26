plugins {
    alias(libs.plugins.android.application)
}

val releaseKeystoreFile = System.getenv("SEPTIEMBRE_KEYSTORE_FILE")
val releaseKeystorePassword = System.getenv("SEPTIEMBRE_KEYSTORE_PASSWORD")
val releaseKeyAlias = System.getenv("SEPTIEMBRE_KEY_ALIAS")
val releaseKeyPassword = System.getenv("SEPTIEMBRE_KEY_PASSWORD")

android {
    namespace = "com.elicapo.yaesseptiembre"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.elicapo.yaesseptiembre"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            releaseKeystoreFile?.let { storeFile = file(it) }
            storePassword = releaseKeystorePassword
            keyAlias = releaseKeyAlias
            keyPassword = releaseKeyPassword
        }
    }
    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}
