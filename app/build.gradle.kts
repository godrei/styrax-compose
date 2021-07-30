import de.mannodermaus.gradle.plugins.junit5.junitPlatform

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("de.mannodermaus.android-junit5")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "dev.kagamirai.styrax"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            keyAlias = System.getenv("KEYSTORE_KEY_ALIAS")
            keyPassword = System.getenv("KEYSTORE_KEY_PASSWORD")
            storeFile = File(System.getenv("KEYSTORE_PATH"))
            storePassword = System.getenv("KEYSTORE_PASSWORD")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
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

    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = listOf("-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check")
    }

    packagingOptions {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }


    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }

    testOptions {
        junitPlatform {
            filters {
                includeEngines("spek2")
            }
        }
    }
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.core)
    implementation(Libs.appCompat)
    implementation(Libs.material)

    implementation(Libs.activityCompose)
    implementation(Libs.composeTooling)
    implementation(Libs.composeFoundation)
    implementation(Libs.composeMaterial)

    debugImplementation(Libs.kodeinDbDebug)
    releaseImplementation(Libs.kodeinDb)

    testImplementation(Libs.kotlinJdk8)
    testImplementation(Libs.kotlinReflect)
    testImplementation(Libs.kotlinTest)
    testImplementation(Libs.spekDsl)
    testImplementation(Libs.spekRunner)

    androidTestImplementation(Libs.composeTest)
}