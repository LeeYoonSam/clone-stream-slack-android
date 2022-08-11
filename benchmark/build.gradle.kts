plugins {
	id(BuildPlugins.ANDROID_TEST_PLUGIN)
	id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
}

android {
	namespace = "com.ys.slackclone.benchmark"
	compileSdk = ProjectProperties.COMPILE_SDK

	defaultConfig {
		minSdk = 23
		targetSdk = compileSdk
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		// This benchmark buildType is used for benchmarking, and should function like your
		// release build (for example, with minification on). It"s signed with a debug key
		// for easy local/CI testing.
		create("benchmark") {
			isDebuggable = true
			signingConfig = getByName("debug").signingConfig
			matchingFallbacks += listOf("release")
		}
	}

	targetProjectPath = ":app"
	experimentalProperties["android.experimental.self-instrumenting"] = true
}

dependencies {
	implementation(TestLib.ANDROIDX_TEST_RUNNER)
	implementation(TestLib.ANDROIDX_TEST_RULES)
	implementation(TestLib.ANDROIDX_UI_AUTOMATOR)
	implementation(TestLib.ANDROID_JUNIT)
	implementation(TestLib.CORE_TEST)
	implementation(TestLib.MACRO_BENCHMARK)
	implementation(TestLib.BASE_PROFILE)
}

androidComponents {
	beforeVariants(selector().all()) {
		it.enabled = it.buildType == "benchmark"
	}
}