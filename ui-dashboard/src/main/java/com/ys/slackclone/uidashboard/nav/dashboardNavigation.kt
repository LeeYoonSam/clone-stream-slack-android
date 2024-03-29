package com.ys.slackclone.uidashboard.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ys.slackclone.navigator.ComposeNavigator
import com.ys.slackclone.navigator.SlackRoute
import com.ys.slackclone.navigator.SlackScreen
import com.ys.slackclone.uichannels.createsearch.CreateNewChannelUI
import com.ys.slackclone.uichannels.createsearch.SearchCreateChannelUI
import com.ys.slackclone.uichat.newchat.NewChatThreadScreen
import com.ys.slackclone.uidashboard.compose.DashboardUI

fun NavGraphBuilder.dashboardNavigation(
	composeNavigator: ComposeNavigator
) {
	navigation(
		startDestination = SlackScreen.Dashboard.name,
		route = SlackRoute.Dashboard.name
	) {
		composable(SlackScreen.Dashboard.name) {
			DashboardUI(composeNavigator)
		}

		composable(SlackScreen.CreateChannelsScreen.name) {
			SearchCreateChannelUI(composeNavigator)
		}

		composable(SlackScreen.CreateNewChannel.name) {
			CreateNewChannelUI(composeNavigator)
		}

		composable(SlackScreen.CreateNewDM.name) {
			NewChatThreadScreen(composeNavigator)
		}
	}

}