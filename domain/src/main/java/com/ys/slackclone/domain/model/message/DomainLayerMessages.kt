package com.ys.slackclone.domain.model.message

interface DomainLayerMessages {
	data class SlackMessage(
		val uuid: String,
		val channelId: String,
		val message: String,
		val userId: String,
		val createdBy: String,
		val createdDate: Long,
		val modifiedDate: Long,
	)
}