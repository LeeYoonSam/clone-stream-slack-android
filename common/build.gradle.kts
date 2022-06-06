plugins {
	id(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
	id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
	id(BuildPlugins.KOTLIN_KAPT)
	id(BuildPlugins.DAGGER_HILT)
}

android {
	compileSdk = ProjectProperties.COMPILE_SDK

	defaultConfig {
		minSdk = (ProjectProperties.MIN_SDK)
		targetSdk = (ProjectProperties.TARGET_SDK)
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
}

kapt {
	generateStubs = true
	correctErrorTypes = true
}

dependencies {
	// Kotlin
	api(Lib.Kotlin.KT_STD)

	// Logger
	implementation(Lib.Android.APP_STARTUP)
	api(Lib.Logger.TIMBER)

	// Dependency Injection
	api(Lib.Di.hiltAndroid)
	api(Lib.Di.hiltNavigationCompose)
	api(Lib.Di.hiltViewModel)

	kapt(Lib.Di.hiltCompiler)
	kaptTest(Lib.Di.hiltCompiler)
	kapt(Lib.Di.hiltAndroidCompiler)
	kaptTest(Lib.Di.hiltAndroidCompiler)
}