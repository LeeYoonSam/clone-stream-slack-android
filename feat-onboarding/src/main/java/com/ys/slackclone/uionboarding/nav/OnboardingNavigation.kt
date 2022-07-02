package com.ys.slackclone.uionboarding.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ys.slackclone.navigator.ComposeNavigator
import com.ys.slackclone.navigator.SlackRoute
import com.ys.slackclone.navigator.SlackScreen

fun NavGraphBuilder.onboardingNavigation(
	composeNavigator: ComposeNavigator
) {
	navigation(
		startDestination = SlackScreen.GettingStarted.name,
		route = SlackRoute.OnBoarding.name
	) {
		composable(SlackScreen.GettingStarted.name) {

		}
		composable(SlackScreen.SkipTypingScreen.name) {

		}
		composable(SlackScreen.WorkspaceInputUI.name) {

		}
		composable(SlackScreen.EmailAddressInputUI.name) {

		}
	}
}