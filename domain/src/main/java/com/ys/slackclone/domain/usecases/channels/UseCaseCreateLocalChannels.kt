package com.ys.slackclone.domain.usecases.channels

import com.ys.slackclone.domain.model.users.DomainLayerUsers
import com.ys.slackclone.domain.repository.ChannelsRepository
import com.ys.slackclone.domain.usecases.BaseUseCase

class UseCaseCreateLocalChannels(
	private val channelsRepository: ChannelsRepository
) : BaseUseCase<Unit, List<DomainLayerUsers.SlackUser>> {

	override suspend fun perform(params: List<DomainLayerUsers.SlackUser>) {
		return channelsRepository.saveOneToOneChannels(params)
	}
}