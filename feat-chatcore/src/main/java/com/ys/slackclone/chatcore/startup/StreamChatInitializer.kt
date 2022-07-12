package com.ys.slackclone.chatcore.startup

import android.content.Context
import androidx.startup.Initializer
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel.DEBUG
import io.getstream.chat.android.offline.model.message.attachments.UploadAttachmentsNetworkType.NOT_ROAMING
import io.getstream.chat.android.offline.plugin.configuration.Config
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory

class StreamChatInitializer : Initializer<Unit> {
	override fun create(context: Context) {
		// 오프라인 저장을 위한 OfflinePlugin 설정
		val offlinePluginFactory = StreamOfflinePluginFactory(
			config = Config(
				backgroundSyncEnabled = true,
				userPresence = true,
				persistenceEnabled = true,
				uploadAttachmentsNetworkType = NOT_ROAMING
			),
			appContext = context
		)

		ChatClient.Builder("vf8jvvm6s635", context)
			.withPlugin(offlinePluginFactory)
			.logLevel(DEBUG)
			.build()
	}

	override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}