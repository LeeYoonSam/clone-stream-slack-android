package com.ys.slackclone.di

import com.ys.slackclone.navigator.ComposeNavigator
import com.ys.slackclone.navigator.SlackCloneComposeNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

	@Binds
	@Singleton
	abstract fun provideComposeNavigator(
		slackCloneComposeNavigator: SlackCloneComposeNavigator
	): ComposeNavigator
}