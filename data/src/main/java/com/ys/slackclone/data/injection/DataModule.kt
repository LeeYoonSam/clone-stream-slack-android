package com.ys.slackclone.data.injection

import android.content.Context
import androidx.room.Room
import com.ys.slackclone.data.local.dao.SlackChannelDao
import com.ys.slackclone.data.local.dao.SlackDatabase
import com.ys.slackclone.data.local.dao.SlackUserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

	@Provides
	@Singleton
	fun provideDatabase(@ApplicationContext context: Context): SlackDatabase {
		return Room.inMemoryDatabaseBuilder(
			context,
			SlackDatabase::class.java
		).fallbackToDestructiveMigration().allowMainThreadQueries().build()
	}

	@Provides
	@Singleton
	fun provideChannelDao(slackDatabase: SlackDatabase): SlackChannelDao =
		slackDatabase.slackChannelDao()

	@Provides
	@Singleton
	fun provideUserDao(slackDatabase: SlackDatabase): SlackUserDao =
		slackDatabase.slackUserDao()
}