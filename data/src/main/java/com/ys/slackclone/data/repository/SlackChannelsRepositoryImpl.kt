package com.ys.slackclone.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.ys.slackclone.common.injection.dispatcher.CoroutineDispatcherProvider
import com.ys.slackclone.data.local.dao.SlackChannelDao
import com.ys.slackclone.data.local.model.DBSlackChannel
import com.ys.slackclone.data.mapper.EntityMapper
import com.ys.slackclone.domain.model.channel.DomainLayerChannels.SlackChannel
import com.ys.slackclone.domain.model.message.ChannelMessage
import com.ys.slackclone.domain.model.users.DomainLayerUsers.SlackUser
import com.ys.slackclone.domain.repository.ChannelsRepository
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.call.await
import io.getstream.chat.android.client.models.Message
import io.getstream.chat.android.client.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SlackChannelsRepositoryImpl @Inject constructor(
	private val slackChannelDao: SlackChannelDao,
	private val slackUserChannelMapper: EntityMapper<SlackUser, DBSlackChannel>,
	private val slackChannelMapper: EntityMapper<SlackChannel, DBSlackChannel>,
	private val coroutineMainDispatcherProvider: CoroutineDispatcherProvider,
	private val chatClient: ChatClient
) : ChannelsRepository {

	override fun fetchChannels(): Flow<List<SlackChannel>> {
		return slackChannelDao.getAllAsFlow()
			.map { list -> dbToDomainList(list) }
	}

	private fun dbToDomainList(list: List<DBSlackChannel>) =
		list.map { channel -> slackChannelMapper.mapToDomain(channel) }

	override fun fetchChannelsPaged(params: String?): Flow<PagingData<SlackChannel>> {
		val chatPager = Pager(PagingConfig(pageSize = 20)) {
			params?.takeIf { it.isNotEmpty() }?.let {
				slackChannelDao.channelsByName(params)
			} ?: run {
				slackChannelDao.channelsByName()
			}
		}

		return chatPager.flow.map {
			it.map { message ->
				slackChannelMapper.mapToDomain(message)
			}
		}
	}

	override suspend fun createChannel(domainLayerChannels: SlackChannel) {
		val user = chatClient.getCurrentUser() ?: User()
		chatClient.createChannel(
			channelType = "messaging",
			channelId = domainLayerChannels.uuid ?: "",
			memberIds = listOf(user.id),
			extraData = mapOf(
				"name" to (domainLayerChannels.name ?: ""),
				"image" to (domainLayerChannels.avatarUrl ?: "")
			)
		).await()
	}

	override suspend fun sendMessageToChannel(channelMessage: ChannelMessage) {
		val channelClient = chatClient.channel(channelMessage.cid)
		chatClient.sendMessage(
			channelType = channelClient.channelType,
			channelId = channelClient.channelId,
			message = Message(text = channelMessage.message)
		).await()
	}

	override suspend fun saveChannel(params: SlackChannel): SlackChannel? {
		return withContext(coroutineMainDispatcherProvider.io) {
			slackChannelDao.insert(slackChannelMapper.mapToData(params))
			slackChannelDao.getById(params.uuid!!)?.let { slackChannelMapper.mapToDomain(it) }
		}
	}

	override suspend fun getChannel(uuid: String): SlackChannel? {
		val dbSlack = slackChannelDao.getById(uuid)
		return dbSlack?.let { slackChannelMapper.mapToDomain(it) }
	}

	override suspend fun channelCount(): Int {
		return withContext(coroutineMainDispatcherProvider.io) {
			slackChannelDao.count()
		}
	}

	override suspend fun saveOneToOneChannels(params: List<SlackUser>) {
		return withContext(coroutineMainDispatcherProvider.io) {
			slackChannelDao.insertAll(
				params.map {
					slackUserChannelMapper.mapToData(it)
				}
			)
		}
	}
}