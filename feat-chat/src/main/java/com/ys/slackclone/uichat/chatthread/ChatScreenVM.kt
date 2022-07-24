package com.ys.slackclone.uichat.chatthread

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ys.slackclone.chatcore.data.UiLayerChannels
import com.ys.slackclone.domain.mappers.UiModelMapper
import com.ys.slackclone.domain.model.channel.DomainLayerChannels
import com.ys.slackclone.domain.model.message.ChannelMessage
import com.ys.slackclone.domain.usecases.channels.UseCaseCreateRemoteChannel
import com.ys.slackclone.domain.usecases.channels.UseCaseSendMessageToChannel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatScreenVM @Inject constructor(
	private val useCaseRemoteChannel: UseCaseCreateRemoteChannel,
	private val useCaseSendMessageToChannel: UseCaseSendMessageToChannel,
	private val channelMapper: UiModelMapper<DomainLayerChannels.SlackChannel, UiLayerChannels.SlackChannel>
) : ViewModel() {
	val message = MutableStateFlow("")
	val chatBoxState = MutableStateFlow(BoxState.Collapsed)

	fun createChannel(uiLayerChannels: UiLayerChannels.SlackChannel) {
		viewModelScope.launch {
			useCaseRemoteChannel.perform(
				channelMapper.mapToDomain(uiLayerChannels)
			)
		}
	}

	fun sendMessage(cid: String, message: String) {
		viewModelScope.launch {
			useCaseSendMessageToChannel.perform(ChannelMessage(cid, message))

			// 대화상자 및 입력 상태 초기화
			chatBoxState.value = BoxState.Collapsed
			this@ChatScreenVM.message.value = ""
		}
	}

	fun switchChatBoxState() {
		chatBoxState.value =
			if (chatBoxState.value == BoxState.Collapsed) BoxState.Expanded else BoxState.Collapsed
	}
}