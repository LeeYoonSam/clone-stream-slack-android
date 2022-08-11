plugins {
    id(BuildPlugins.ANDROID_APPLICATION_PLUGIN)
    id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
    id(BuildPlugins.KOTLIN_PARCELABLE_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.DAGGER_HILT)
}

android {
    compileSdk = (ProjectProperties.COMPILE_SDK)

    defaultConfig {
        applicationId = (ProjectProperties.APPLICATION_ID)
        minSdk = (ProjectProperties.MIN_SDK)
        targetSdk = (ProjectProperties.TARGET_SDK)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        create("benchmark") {
	        signingConfig = signingConfigs.getByName("debug")
	        matchingFallbacks += listOf("release")
	        isDebuggable = false
        }
	    val debug by getting {
            isDebuggable = true
            versionNameSuffix = "-debug"
            applicationIdSuffix = ".debug"
        }

        val release by getting {
            isDebuggable = false
            versionNameSuffix = "-release"
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"), "proguard-common.txt",
                "proguard-specific.txt"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Lib.Android.COMPOSE_COMPILER_VERSION
    }

    packagingOptions {
        resources.excludes.add("META-INF/LICENSE.txt")
        resources.excludes.add("META-INF/NOTICE.txt")
        resources.excludes.add("LICENSE.txt")
        resources.excludes.add( "/META-INF/{AL2.0,LGPL2.1}")
    }
}

// Required for annotation processing plugins like Dagger
kapt {
    generateStubs = true
    correctErrorTypes = true
}

dependencies {
    api(project(":feat-onboarding"))
    api(project(":ui-dashboard"))
    api(project(":feat-chatcore"))

    implementation(project(":navigator"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(project(":commonui"))

    /* Android Designing and layout */
    implementation(Lib.Android.COMPOSE_LIVEDATA)
    implementation(Lib.Android.COMPOSE_NAVIGATION)
    implementation(Lib.Android.COMPOSE_MATERIAL)
    implementation(Lib.Android.COMPOSE_TOOLING)
    implementation(Lib.Android.CONSTRAINT_LAYOUT_COMPOSE)
    implementation(Lib.Kotlin.KT_STD)
    implementation(Lib.Android.MATERIAL_DESIGN)
    implementation(Lib.Android.ACCOMPANIST_INSETS)
    implementation(Lib.Android.SPLASH_SCREEN_API)

    implementation(Lib.Android.APP_COMPAT)

    implementation(Lib.Kotlin.KTX_CORE)

    /*DI*/
    implementation(Lib.Di.hiltAndroid)
    implementation(Lib.Di.hiltNavigationCompose)
    implementation(Lib.Di.hiltViewModel)

    kapt(Lib.Di.hiltCompiler)
    kapt(Lib.Di.hiltAndroidCompiler)

    /* Logger */
    implementation(Lib.Logger.TIMBER)
    /* Async */
    implementation(Lib.Async.COROUTINES)
    implementation(Lib.Async.COROUTINES_ANDROID)

    /* Room */
    implementation(Lib.Room.roomRuntime)
    kapt(Lib.Room.roomCompiler)
    implementation(Lib.Room.roomKtx)
    implementation(Lib.Room.roomPaging)

    /*Testing*/
    testImplementation(TestLib.JUNIT)
    testImplementation(TestLib.CORE_TEST)
    testImplementation(TestLib.ANDROID_JUNIT)
    testImplementation(TestLib.ARCH_CORE)
    testImplementation(TestLib.MOCK_WEB_SERVER)
    testImplementation(TestLib.ROBO_ELECTRIC)
    testImplementation(TestLib.COROUTINES)
    testImplementation(TestLib.MOCKK)

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Lib.Android.COMPOSE_VERSION}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${Lib.Android.COMPOSE_VERSION}")
}