package com.ys.slackclone.data.mapper

import com.ys.slackclone.data.local.model.DBSlackChannel
import com.ys.slackclone.domain.model.channel.DomainLayerChannels
import com.ys.slackclone.domain.model.channel.DomainLayerChannels.SlackChannel
import javax.inject.Inject

class SlackChannelMapper @Inject constructor() :
	EntityMapper<DomainLayerChannels.SlackChannel, DBSlackChannel> {

	override fun mapToDomain(entity: DBSlackChannel): SlackChannel {
		return DomainLayerChannels.SlackChannel(
			uuid = entity.uuid,
			name = entity.name,
			createdDate = entity.createdDate,
			modifiedDate = entity.modifiedDate,
			isMuted = entity.isMuted,
			isPrivate = entity.isPrivate,
			isStarred = entity.isStarred,
			isShareOutSide = entity.isShareOutSide,
			isOneToOne = entity.isOneToOne,
			avatarUrl = entity.avatarUrl
		)
	}

	override fun mapToData(model: SlackChannel): DBSlackChannel {
		return DBSlackChannel(
			uuid = model.uuid ?: model.name.orEmpty(),
			name = model.name,
			createdDate = model.createdDate,
			modifiedDate = model.modifiedDate,
			isStarred = model.isStarred,
			isPrivate = model.isPrivate,
			isShareOutSide = model.isShareOutSide,
			avatarUrl = model.avatarUrl,
			isOneToOne = model.isOneToOne
		)
	}
}