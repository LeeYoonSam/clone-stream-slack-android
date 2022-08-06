package com.ys.slackclone.uichannels.createsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.ys.slackclone.chatcore.data.UiLayerChannels
import com.ys.slackclone.domain.mappers.UiModelMapper
import com.ys.slackclone.domain.model.channel.DomainLayerChannels
import com.ys.slackclone.domain.usecases.channels.UseCaseFetchChannelCount
import com.ys.slackclone.domain.usecases.channels.UseCaseSearchChannel
import com.ys.slackclone.navigator.ComposeNavigator
import com.ys.slackclone.navigator.NavigationKeys
import com.ys.slackclone.navigator.SlackScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchChannelsVM @Inject constructor(
	private val composeNavigator: ComposeNavigator,
	private val ucFetchChannels: UseCaseSearchChannel,
	private val ucFetchChannelCount: UseCaseFetchChannelCount,
	private val chatPresentationMapper: UiModelMapper<DomainLayerChannels.SlackChannel, UiLayerChannels.SlackChannel>
) : ViewModel() {

	val search = MutableStateFlow("")
	val channelCount = MutableStateFlow(0)
	val channels = MutableStateFlow(flow(""))

	init {
		viewModelScope.launch {
			val count = ucFetchChannelCount.perform()
			channelCount.value = count
		}
	}

	private fun flow(search: String) = ucFetchChannels.performStreaming(search).map { channels ->
		channels.map { channel ->
			chatPresentationMapper.mapToPresentation(channel)
		}
	}

	fun search(newValue: String) {
		search.value = newValue
		channels.value = flow(newValue)
	}

	fun navigate(channel: UiLayerChannels.SlackChannel) {
		composeNavigator.navigateBackWithResult(
			NavigationKeys.navigateChannel,
			channel.uuid!!,
			SlackScreen.Dashboard.name
		)
	}
}