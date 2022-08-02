package com.ys.slackclone.uichannels

import androidx.lifecycle.ViewModel
import com.ys.slackclone.chatcore.data.UiLayerChannels
import com.ys.slackclone.domain.mappers.UiModelMapper
import com.ys.slackclone.domain.model.channel.DomainLayerChannels
import com.ys.slackclone.domain.usecases.channels.UseCaseFetchChannels
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SlackChannelVM @Inject constructor(
	private val ucFetchChannels: UseCaseFetchChannels,
	private val chatPresentationMapper: UiModelMapper<DomainLayerChannels.SlackChannel, UiLayerChannels.SlackChannel>
) : ViewModel() {

	val channels = MutableStateFlow<Flow<List<UiLayerChannels.SlackChannel>>>(emptyFlow())

	fun allChannels() {
		channels.value = ucFetchChannels.performStreaming(null).map { channels ->
			domSlackToPresentation(channels)
		}
	}

	fun loadDirectMessageChannels() {
		channels.value = ucFetchChannels.performStreaming(null).map { channels ->
			domSlackToPresentation(channels)
		}
	}

	fun loadStarredChannels() {
		channels.value = ucFetchChannels.performStreaming(null).map { channels ->
			domSlackToPresentation(channels)
		}
	}

	private fun domSlackToPresentation(channels: List<DomainLayerChannels.SlackChannel>) =
		channels.map { channel ->
			chatPresentationMapper.mapToPresentation(channel)
		}
}