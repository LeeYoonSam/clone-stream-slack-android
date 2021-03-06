package com.ys.slackclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.ys.slackclone.navigator.ComposeNavigator
import com.ys.slackclone.navigator.SlackRoute
import com.ys.slackclone.uidashboard.nav.dashboardNavigation
import com.ys.slackclone.uionboarding.nav.onboardingNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	@Inject
	lateinit var composeNavigator: ComposeNavigator

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		WindowCompat.setDecorFitsSystemWindows(window, false)

		installSplashScreen()
		setContent {
			val navController = rememberNavController()

			LaunchedEffect(Unit) {
				composeNavigator.handleNavigationCommands(navController)
			}

			ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
				NavHost(
					navController = navController,
					startDestination = SlackRoute.OnBoarding.name,
				) {
					onboardingNavigation(
						composeNavigator = composeNavigator,
					)
					dashboardNavigation(
						composeNavigator = composeNavigator
					)
				}
			}
		}
	}
}