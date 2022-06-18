package com.ys.slackclone.domain.usecases.channels

import com.ys.slackclone.domain.repository.ChannelsRepository
import com.ys.slackclone.domain.usecases.BaseUseCase

class UseCaseFetchChannelCount(
	private val channelsRepository: ChannelsRepository
) : BaseUseCase<Int, Unit> {
	override suspend fun perform(): Int {
		return  channelsRepository.channelCount()
	}
}