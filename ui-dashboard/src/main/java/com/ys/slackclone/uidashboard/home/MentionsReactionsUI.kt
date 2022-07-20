package com.ys.slackclone.uidashboard.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.ys.slackclone.commonui.material.SlackSurfaceAppBar
import com.ys.slackclone.commonui.theme.SlackCloneColorProvider
import com.ys.slackclone.commonui.theme.SlackCloneSurface
import com.ys.slackclone.commonui.theme.SlackCloneTypography
import io.getstream.chat.android.compose.R.drawable
import io.getstream.chat.android.compose.R.string
import io.getstream.chat.android.compose.ui.components.EmptyContent
import io.getstream.chat.android.compose.ui.theme.ChatTheme

@Composable
fun MentionsReactionsUI() {
	SlackCloneSurface(
		color = SlackCloneColorProvider.colors.uiBackground,
		modifier = Modifier.fillMaxSize()
	) {
		Column {
			MRTopAppBar()
			ChatTheme {
				EmptyContent(
					modifier = Modifier.fillMaxSize(),
					text = stringResource(id = string.stream_compose_message_list_empty_messages),
					painter = painterResource(id = drawable.stream_compose_empty_channels)
				)
			}
		}
	}
}

@Composable
private fun MRTopAppBar() {
	SlackSurfaceAppBar(
		title = {
			Text(
				text = "Mentions & Reactions",
				style = SlackCloneTypography.h5.copy(
					color = Color.White,
					fontWeight = FontWeight.Bold
				)
			)
		},
		backgroundColor = SlackCloneColorProvider.colors.appBarColor
	)
}