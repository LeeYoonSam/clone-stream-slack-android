package com.ys.slackclone.chatcore.data

interface UiLayerChannels {
	data class SlackChannel(
		val name: String?,
		val isPrivate: Boolean?,
		val uuid: String?,
		val createDate: Long?,
		val modifiedDate: Long?,
		val isMuted: Boolean?,
		val isOneToOne: Boolean?,
		val pictureUrl: String?
	)
}