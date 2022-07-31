package com.ys.slackclone.uichat.chatthread

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import com.ys.slackclone.chatcore.views.SlackChannelItem
import com.ys.slackclone.commonui.material.SlackSurfaceAppBar
import com.ys.slackclone.commonui.theme.SlackCloneColorProvider
import com.ys.slackclone.commonui.theme.SlackCloneTheme
import com.ys.slackclone.uichat.chatthread.composables.ChatScreenContent
import io.getstream.chat.android.client.models.Channel

@Composable
fun ChatScreenUI(
	modifier: Modifier,
	slackChannel: Channel,
	onBackClick: () -> Unit
) {
	val scaffoldState = rememberScaffoldState()

	SlackCloneTheme {
		Scaffold(
			backgroundColor = SlackCloneColorProvider.colors.uiBackground,
			contentColor = SlackCloneColorProvider.colors.textSecondary,
			modifier = modifier
				.statusBarsPadding(),
			scaffoldState = scaffoldState,
			snackbarHost = {
				scaffoldState.snackbarHostState
			},
			topBar = {
				ChatAppBar(onBackClick, slackChannel)
			}
		) { innerPadding ->
			Box(
				modifier = Modifier
					.padding(innerPadding)
			) {
				ChatScreenContent(slackChannel = slackChannel)
			}
		}
	}
}

@Composable
private fun ChatAppBar(onBackClick: () -> Unit, slackChannel: Channel) {
	SlackSurfaceAppBar(backgroundColor = SlackCloneColorProvider.colors.appBarColor) {
		IconButton(onClick = { onBackClick() }) {
			Icon(
				imageVector = Icons.Default.ArrowBack,
				contentDescription = null,
				tint = SlackCloneColorProvider.colors.appBarIconColor,
				modifier = Modifier.size(24.dp)
			)
		}
		Column(
			modifier = Modifier.weight(1f),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			SlackChannelItem(
				slackChannel = slackChannel,
				textColor = SlackCloneColorProvider.colors.appBarTextTitleColor
			) {}
		}
		IconButton(onClick = { }) {
			Icon(
				imageVector = Icons.Default.Call,
				contentDescription = null,
				tint = SlackCloneColorProvider.colors.appBarIconColor,
				modifier = Modifier
					.size(24.dp)
			)
		}
	}
}

enum class BoxState { Collapsed, Expanded}