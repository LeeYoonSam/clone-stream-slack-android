package com.ys.slackclone.data.injection

import com.ys.slackclone.data.repository.SlackChannelsRepositoryImpl
import com.ys.slackclone.data.repository.SlackUserRepository
import com.ys.slackclone.domain.repository.ChannelsRepository
import com.ys.slackclone.domain.repository.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

	@Binds
	@Singleton
	abstract fun bindLocalChannelsRepository(
		slackLocalChannelsRepositoryImpl: SlackChannelsRepositoryImpl
	): ChannelsRepository

	@Binds
	@Singleton
	abstract fun bindSlackUserRepository(
		slackUserRepository: SlackUserRepository
	): UsersRepository
}