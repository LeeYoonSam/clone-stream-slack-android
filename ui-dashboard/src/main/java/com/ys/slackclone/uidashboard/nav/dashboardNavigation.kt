package com.ys.slackclone.uidashboard.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ys.slackclone.navigator.ComposeNavigator
import com.ys.slackclone.navigator.SlackRoute
import com.ys.slackclone.navigator.SlackScreen

fun NavGraphBuilder.dashboardNavigation(
	composeNavigator: ComposeNavigator
) {
	navigation(
		startDestination = SlackScreen.Dashboard.name,
		route = SlackRoute.Dashboard.name
	) {
		composable(SlackScreen.Dashboard.name) {

		}

		composable(SlackScreen.CreateChannelsScreen.name) {

		}

		composable(SlackScreen.CreateNewChannel.name) {

		}

		composable(SlackScreen.CreateNewDM.name) {

		}
	}

}