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
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
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
}

// Required for annotation processing plugins like Dagger
kapt {
	generateStubs = true
	correctErrorTypes = true
}

dependencies {

	implementation("com.github.vatbub:randomusers:1.3"){
		exclude("com.google.guava")
	}
	implementation(project(":common"))
	implementation(project(":domain"))

	/*Stream Chat SDK*/
	implementation(Lib.STREAM.STREAM_CHAT_CLIENT)

	/*Kotlin*/
	api(Lib.Kotlin.KT_STD)
	api(Lib.Async.COROUTINES)

	/* Paging */
	implementation(Lib.Paging.PAGING_3)
	/* Room */
	api(Lib.Room.roomRuntime)
	kapt(Lib.Room.roomCompiler)
	api(Lib.Room.roomKtx)
	api(Lib.Room.roomPaging)

	/* Networking */
	api(Lib.Networking.RETROFIT)
	api(Lib.Networking.RETROFIT_GSON)
	api(Lib.Networking.LOGGING)

	api(Lib.Serialization.GSON)

	/* Dependency Injection */
	api(Lib.Di.hiltAndroid)
	kapt(Lib.Di.hiltAndroidCompiler)
}