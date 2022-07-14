package com.ys.slackclone.chatcore

import com.ys.slackclone.chatcore.data.UiLayerChannels
import com.ys.slackclone.domain.mappers.UiModelMapper
import com.ys.slackclone.domain.model.channel.DomainLayerChannels
import javax.inject.Inject

class ChannelUIModelMapper @Inject constructor() :
	UiModelMapper<DomainLayerChannels.SlackChannel, UiLayerChannels.SlackChannel> {
	override fun mapToPresentation(model: DomainLayerChannels.SlackChannel): UiLayerChannels.SlackChannel {
		return UiLayerChannels.SlackChannel(
			name = model.name,
			isPrivate = model.isPrivate,
			uuid = model.uuid,
			createDate = model.createdDate,
			modifiedDate = model.modifiedDate,
			isMuted = model.isMuted,
			isOneToOne = model.isOneToOne,
			pictureUrl = model.avatarUrl
		)
	}

	override fun mapToDomain(modelItem: UiLayerChannels.SlackChannel): DomainLayerChannels.SlackChannel {
		return DomainLayerChannels.SlackChannel(
			uuid = modelItem.uuid,
			name = modelItem.name,
			createdDate = modelItem.createDate,
			modifiedDate = modelItem.modifiedDate,
			isMuted = modelItem.isMuted,
			isPrivate = modelItem.isPrivate,
			isStarred = false,
			isShareOutSide = false,
			isOneToOne = modelItem.isOneToOne,
			avatarUrl = modelItem.pictureUrl
		)
	}
}