package com.ys.slackclone.domain.usecases.channels

import com.ys.slackclone.domain.model.channel.DomainLayerChannels
import com.ys.slackclone.domain.repository.ChannelsRepository
import com.ys.slackclone.domain.usecases.BaseUseCase

class UseCaseCreateRemoteChannel(
	private val channelsRepository: ChannelsRepository
) : BaseUseCase<Unit, DomainLayerChannels.SlackChannel> {
	override suspend fun perform(params: DomainLayerChannels.SlackChannel) {
		return  channelsRepository.createChannel(params)
	}
}