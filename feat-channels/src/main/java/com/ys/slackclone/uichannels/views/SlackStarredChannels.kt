package com.ys.slackclone.uichannels.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ys.slackclone.chatcore.data.ExpandCollapseModel
import com.ys.slackclone.chatcore.data.UiLayerChannels
import com.ys.slackclone.common.R
import com.ys.slackclone.uichannels.SlackChannelVM

@Composable
fun SlackStarredChannels(
	onItemClick: (UiLayerChannels.SlackChannel) -> Unit = {},
	channelVM: SlackChannelVM = hiltViewModel(),
	onClickAdd: () -> Unit
) {
	val starred = stringResource(id = R.string.starred)
	val channelsFlow = channelVM.channels.collectAsState()
	val channels by channelsFlow.value.collectAsState(initial = listOf())

	LaunchedEffect(key1 = Unit) {
		channelVM.loadStarredChannels()
	}

	var expandCollapseModel by remember {
		mutableStateOf(
			ExpandCollapseModel(
				id = 1,
				title = starred,
				needsPlusButton = false,
				isOpen = false
			)
		)
	}

	SKExpandCollapseColumn(
		expandCollapseModel = expandCollapseModel,
		onItemClick = onItemClick,
		onExpandCollapse = {
			expandCollapseModel = expandCollapseModel.copy(isOpen = it)
		},
		channels = channels,
		onClickAdd = onClickAdd
	)
}