package com.ys.slackclone.uionboarding.nav

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ys.slackclone.navigator.ComposeNavigator
import com.ys.slackclone.navigator.SlackRoute
import com.ys.slackclone.navigator.SlackScreen
import com.ys.slackclone.uionboarding.compose.EmailAddressInputUI
import com.ys.slackclone.uionboarding.compose.GettingStartedUI
import com.ys.slackclone.uionboarding.compose.SkipTypingUI
import com.ys.slackclone.uionboarding.compose.WorkspaceInputUI

fun NavGraphBuilder.onboardingNavigation(
	composeNavigator: ComposeNavigator
) {
	navigation(
		startDestination = SlackScreen.GettingStarted.name,
		route = SlackRoute.OnBoarding.name
	) {
		composable(SlackScreen.GettingStarted.name) {
			GettingStartedUI(composeNavigator)
		}
		composable(SlackScreen.SkipTypingScreen.name) {
			SkipTypingUI(composeNavigator)
		}
		composable(SlackScreen.WorkspaceInputUI.name) {
			WorkspaceInputUI(composeNavigator, hiltViewModel())
		}
		composable(SlackScreen.EmailAddressInputUI.name) {
			EmailAddressInputUI(composeNavigator, hiltViewModel())
		}
	}
}