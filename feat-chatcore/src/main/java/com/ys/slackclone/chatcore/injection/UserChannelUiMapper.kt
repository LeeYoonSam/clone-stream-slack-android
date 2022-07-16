package com.ys.slackclone.chatcore.injection

import com.ys.slackclone.chatcore.data.UiLayerChannels
import com.ys.slackclone.domain.mappers.UiModelMapper
import com.ys.slackclone.domain.model.users.DomainLayerUsers
import javax.inject.Inject

class UserChannelUiMapper @Inject constructor() :
	UiModelMapper<DomainLayerUsers.SlackUser, UiLayerChannels.SlackChannel> {
	override fun mapToPresentation(model: DomainLayerUsers.SlackUser): UiLayerChannels.SlackChannel {
		TODO("Not yet implemented")
	}

	override fun mapToDomain(modelItem: UiLayerChannels.SlackChannel): DomainLayerUsers.SlackUser {
		TODO("Not yet implemented")
	}
}