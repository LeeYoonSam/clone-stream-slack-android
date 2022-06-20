package com.ys.slackclone.data.mapper

import com.ys.slackclone.data.local.model.DBSlackChannel
import com.ys.slackclone.domain.model.users.DomainLayerUsers
import javax.inject.Inject

class SlackUserChannelMapper @Inject constructor() :
	EntityMapper<DomainLayerUsers.SlackUser, DBSlackChannel> {

	override fun mapToDomain(entity: DBSlackChannel): DomainLayerUsers.SlackUser {
		TODO("Not yet implemented")
	}

	override fun mapToData(model: DomainLayerUsers.SlackUser): DBSlackChannel {
		return DBSlackChannel(
			uuid = model.login,
			name = model.name,
			avatarUrl = model.picture,
			isOneToOne = true
		)
	}
}