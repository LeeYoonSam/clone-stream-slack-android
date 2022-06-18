package com.ys.slackclone.domain.usecases.channels

import com.ys.slackclone.domain.model.channel.DomainLayerChannels
import com.ys.slackclone.domain.repository.ChannelsRepository
import com.ys.slackclone.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

class UseCaseFetchChannels(
	private val channelsRepository: ChannelsRepository
) : BaseUseCase<List<DomainLayerChannels.SlackChannel>, Unit> {

	override fun performStreaming(params: Unit?): Flow<List<DomainLayerChannels.SlackChannel>> {
		return channelsRepository.fetchChannels()
	}
}