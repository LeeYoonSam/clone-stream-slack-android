package com.ys.slackclone.data.injection

import com.github.vatbub.randomusers.result.RandomUser
import com.ys.slackclone.data.local.model.DBSlackChannel
import com.ys.slackclone.data.local.model.DBSlackUser
import com.ys.slackclone.data.mapper.EntityMapper
import com.ys.slackclone.data.mapper.SlackChannelMapper
import com.ys.slackclone.data.mapper.SlackUserChannelMapper
import com.ys.slackclone.data.mapper.SlackUserMapper
import com.ys.slackclone.domain.model.channel.DomainLayerChannels
import com.ys.slackclone.domain.model.users.DomainLayerUsers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataMappersModule {

	@Binds
	@Singleton
	abstract fun bindSlackUserChannelMapper(
		slackUserChannelMapper: SlackUserChannelMapper
	): EntityMapper<DomainLayerUsers.SlackUser, DBSlackChannel>

	@Binds
	@Singleton
	abstract fun bindSlackUserDataDomainMapper(
		slackUserMapper: SlackUserMapper
	): EntityMapper<DomainLayerUsers.SlackUser, RandomUser>

	@Binds
	@Singleton
	abstract fun bindSlackChannelDataDomainMapper(
		slackChannelMapper: SlackChannelMapper
	): EntityMapper<DomainLayerChannels.SlackChannel, DBSlackChannel>
}