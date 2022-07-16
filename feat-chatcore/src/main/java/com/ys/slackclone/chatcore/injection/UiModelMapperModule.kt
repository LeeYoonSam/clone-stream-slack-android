package com.ys.slackclone.chatcore.injection

import com.ys.slackclone.chatcore.ChannelUIModelMapper
import com.ys.slackclone.chatcore.data.UiLayerChannels
import com.ys.slackclone.data.mapper.SlackUserChannelMapper
import com.ys.slackclone.domain.mappers.UiModelMapper
import com.ys.slackclone.domain.model.channel.DomainLayerChannels
import com.ys.slackclone.domain.model.users.DomainLayerUsers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UiModelMapperModule {

	@Binds
	@Singleton
	abstract fun bindSlackUserChannelMapper(
		userChannelMapper: SlackUserChannelMapper
	): UiModelMapper<DomainLayerUsers.SlackUser, UiLayerChannels.SlackChannel>

	@Binds
	@Singleton
	abstract fun bindChannelUIModelMapper(
		channelUIModelMapper: ChannelUIModelMapper
	): UiModelMapper<DomainLayerChannels.SlackChannel, UiLayerChannels.SlackChannel>
}