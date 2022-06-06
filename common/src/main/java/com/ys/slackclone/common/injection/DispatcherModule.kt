package com.ys.slackclone.common.injection

import com.ys.slackclone.common.injection.dispatcher.CoroutineDispatcherProvider
import com.ys.slackclone.common.injection.dispatcher.RealCoroutineDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DispatcherModule {
	@Provides
	@Singleton
	fun providesCoroutineDispatcher(): CoroutineDispatcherProvider {
		return RealCoroutineDispatcherProvider()
	}
}