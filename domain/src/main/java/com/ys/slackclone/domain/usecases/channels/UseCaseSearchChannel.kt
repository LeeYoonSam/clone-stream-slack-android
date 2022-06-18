package com.ys.slackclone.domain.usecases.channels

import androidx.paging.PagingData
import com.ys.slackclone.domain.model.channel.DomainLayerChannels.SlackChannel
import com.ys.slackclone.domain.repository.ChannelsRepository
import com.ys.slackclone.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

class UseCaseSearchChannel(
	private val channelsRepository: ChannelsRepository
) : BaseUseCase<PagingData<SlackChannel>, String> {

	override fun performStreaming(params: String?): Flow<PagingData<SlackChannel>> {
		return channelsRepository.fetchChannelsPaged(params)
	}
}