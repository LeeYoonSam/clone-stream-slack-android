package com.ys.slackclone.domain.usecases.channels

import com.ys.slackclone.domain.model.channel.DomainLayerChannels
import com.ys.slackclone.domain.repository.ChannelsRepository
import com.ys.slackclone.domain.usecases.BaseUseCase

class UseCaseCreateLocalChannel(
	private val channelsRepository: ChannelsRepository
) : BaseUseCase<DomainLayerChannels.SlackChannel, DomainLayerChannels.SlackChannel> {

	override suspend fun perform(params: DomainLayerChannels.SlackChannel): DomainLayerChannels.SlackChannel? {
		return  channelsRepository.saveChannel(params)
	}
}