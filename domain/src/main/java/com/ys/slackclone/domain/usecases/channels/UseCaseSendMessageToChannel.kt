package com.ys.slackclone.domain.usecases.channels

import com.ys.slackclone.domain.model.message.ChannelMessage
import com.ys.slackclone.domain.repository.ChannelsRepository
import com.ys.slackclone.domain.usecases.BaseUseCase

class UseCaseSendMessageToChannel(
	private val channelsRepository: ChannelsRepository
) : BaseUseCase<Unit, ChannelMessage> {

	override suspend fun perform(params: ChannelMessage) {
		channelsRepository.sendMessageToChannel(params)
	}
}