package com.ys.slackclone.domain.usecases.channels

import com.ys.slackclone.domain.model.channel.DomainLayerChannels
import com.ys.slackclone.domain.repository.ChannelsRepository
import com.ys.slackclone.domain.usecases.BaseUseCase

class UseCaseGetChannel(
	private val channelsRepository: ChannelsRepository
) : BaseUseCase<DomainLayerChannels.SlackChannel, String> {

	override suspend fun perform(params: String): DomainLayerChannels.SlackChannel? {
		return channelsRepository.getChannel(params)
	}
}