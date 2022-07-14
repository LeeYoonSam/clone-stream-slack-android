package com.ys.slackclone.chatcore.extensions

import com.ys.slackclone.chatcore.data.UiLayerChannels
import com.ys.slackclone.domain.model.message.DomainLayerMessages
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.Channel
import io.getstream.chat.android.client.models.Member
import io.getstream.chat.android.client.models.User
import java.util.Date
import kotlin.random.Random

fun UiLayerChannels.SlackChannel.toStreamChannel(): Channel {
	return Channel(
		id = uuid.orEmpty(),
		cid = "messaging:$uuid",
		type = "messaging",
		name = name.orEmpty(),
		image = pictureUrl.orEmpty(),
		hidden = isPrivate ?: false,
		members = listOf(
			Member(User(name = name.orEmpty())),
			Member(ChatClient.instance().getCurrentUser() ?: User())
		),
		createdAt = Date(createDate ?: System.currentTimeMillis()),
		updatedAt = Date(modifiedDate ?: System.currentTimeMillis()),
		memberCount = if (isOneToOne == true) 2 else Random.nextInt(100) + 2
	)
}

fun Channel.toSlackUIChannel(): UiLayerChannels.SlackChannel {
	return UiLayerChannels.SlackChannel(
		name = name,
		isPrivate = hidden,
		uuid = id,
		createDate = createdAt?.time ?: System.currentTimeMillis(),
		modifiedDate = updatedAt?.time ?: System.currentTimeMillis(),
		isMuted = false,
		isOneToOne = true,
		pictureUrl = image
	)
}

fun Channel.toSlackDomainChannelMessage(): DomainLayerMessages.SlackMessage {
	return DomainLayerMessages.SlackMessage(
		uuid = id,
		channelId = cid,
		message = messages.firstOrNull()?.text ?: "Direct message",
		userId = members.firstOrNull()?.user?.id.orEmpty(),
		createdBy = members.firstOrNull()?.user?.name.orEmpty(),
		createdDate = createdAt?.time ?: System.currentTimeMillis(),
		modifiedDate = updatedAt?.time ?: System.currentTimeMillis()
	)
}