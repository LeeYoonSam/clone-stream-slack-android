package com.ys.slackclone.uichat.newchat

import androidx.lifecycle.ViewModel
import androidx.paging.map
import com.ys.slackclone.chatcore.data.UiLayerChannels
import com.ys.slackclone.domain.mappers.UiModelMapper
import com.ys.slackclone.domain.model.channel.DomainLayerChannels
import com.ys.slackclone.domain.usecases.channels.UseCaseSearchChannel
import com.ys.slackclone.navigator.ComposeNavigator
import com.ys.slackclone.navigator.NavigationKeys
import com.ys.slackclone.navigator.SlackScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class NewChatThreadVM @Inject constructor(
	private val composeNavigator: ComposeNavigator,
	private val ucFetchChannels: UseCaseSearchChannel,
	private val chatPresentationMapper: UiModelMapper<DomainLayerChannels.SlackChannel, UiLayerChannels.SlackChannel>
) : ViewModel() {

	val search = MutableStateFlow("")
	var users = MutableStateFlow(flow(""))

	private fun flow(search: String) = ucFetchChannels.performStreaming(search).map { channels ->
		channels.map { channel ->
			chatPresentationMapper.mapToPresentation(channel)
		}
	}

	fun search(newValue: String) {
		search.value = newValue
		users.value = flow(newValue)
	}

	fun navigate(channel: UiLayerChannels.SlackChannel) {
		composeNavigator.navigateBackWithResult(
			NavigationKeys.navigateChannel,
			channel.uuid!!,
			SlackScreen.Dashboard.name
		)
	}
}