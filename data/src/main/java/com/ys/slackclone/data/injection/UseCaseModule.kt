package com.ys.slackclone.data.injection

import com.ys.slackclone.domain.repository.ChannelsRepository
import com.ys.slackclone.domain.repository.UsersRepository
import com.ys.slackclone.domain.usecases.channels.UseCaseCreateLocalChannel
import com.ys.slackclone.domain.usecases.channels.UseCaseCreateLocalChannels
import com.ys.slackclone.domain.usecases.channels.UseCaseCreateRemoteChannel
import com.ys.slackclone.domain.usecases.channels.UseCaseFetchChannelCount
import com.ys.slackclone.domain.usecases.channels.UseCaseFetchChannels
import com.ys.slackclone.domain.usecases.channels.UseCaseGetChannel
import com.ys.slackclone.domain.usecases.channels.UseCaseSearchChannel
import com.ys.slackclone.domain.usecases.channels.UseCaseSendMessageToChannel
import com.ys.slackclone.domain.usecases.users.UseCaseFetchUsers
import com.ys.slackclone.domain.usecases.users.UseCaseLoginUser
import com.ys.slackclone.domain.usecases.users.UseCaseLogoutUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

	@Provides
	@ViewModelScoped
	fun provideUseCaseFetchChannels(channelsRepository: ChannelsRepository) =
		UseCaseFetchChannels(channelsRepository)

	@Provides
	@ViewModelScoped
	fun provideUseCaseCreateLocalChannel(channelsRepository: ChannelsRepository) =
		UseCaseCreateLocalChannel(channelsRepository)

	@Provides
	@ViewModelScoped
	fun provideUseCaseCreateLocalChannels(channelsRepository: ChannelsRepository) =
		UseCaseCreateLocalChannels(channelsRepository)

	@Provides
	@ViewModelScoped
	fun provideUseCaseCreateRemoteChannel(channelsRepository: ChannelsRepository) =
		UseCaseCreateRemoteChannel(channelsRepository)

	@Provides
	@ViewModelScoped
	fun provideUseCaseSendMessageToChannel(channelsRepository: ChannelsRepository) =
		UseCaseSendMessageToChannel(channelsRepository)

	@Provides
	@ViewModelScoped
	fun provideUseCaseGetChannel(channelsRepository: ChannelsRepository) =
		UseCaseGetChannel(channelsRepository)

	@Provides
	@ViewModelScoped
	fun provideUseCaseFetchChannelCount(channelsRepository: ChannelsRepository) =
		UseCaseFetchChannelCount(channelsRepository)

	@Provides
	@ViewModelScoped
	fun provideUseCaseSearchChannel(channelsRepository: ChannelsRepository) =
		UseCaseSearchChannel(channelsRepository)

	@Provides
	@ViewModelScoped
	fun provideUseCaseFetchUsers(slackUsersRepository: UsersRepository) =
		UseCaseFetchUsers(slackUsersRepository)

	@Provides
	@ViewModelScoped
	fun provideUseCaseLogoutUser(slackUsersRepository: UsersRepository) =
		UseCaseLogoutUser(slackUsersRepository)

	@Provides
	@ViewModelScoped
	fun provideUseCaseLoginUser(slackUsersRepository: UsersRepository) =
		UseCaseLoginUser(slackUsersRepository)
}