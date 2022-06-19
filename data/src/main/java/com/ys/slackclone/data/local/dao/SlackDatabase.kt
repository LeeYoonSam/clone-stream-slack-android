package com.ys.slackclone.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ys.slackclone.data.local.model.DBSlackChannel
import com.ys.slackclone.data.local.model.DBSlackUser

@Database(
	entities = [DBSlackUser::class, DBSlackChannel::class],
	version = 1
)
abstract class SlackDatabase : RoomDatabase() {
	abstract fun slackUserDao(): SlackUserDao
	abstract fun slackChannelDao(): SlackChannelDao
}