package com.ys.slackclone.data.repository

import com.github.vatbub.randomusers.Generator
import com.github.vatbub.randomusers.result.RandomUser
import com.ys.slackclone.common.injection.dispatcher.CoroutineDispatcherProvider
import com.ys.slackclone.data.mapper.EntityMapper
import com.ys.slackclone.domain.model.login.LoginState
import com.ys.slackclone.domain.model.users.DomainLayerUsers
import com.ys.slackclone.domain.repository.UsersRepository
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.call.await
import io.getstream.chat.android.client.models.User
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SlackUserRepository @Inject constructor(
	private val mapper: EntityMapper<DomainLayerUsers.SlackUser, RandomUser>,
	private val coroutineMainDispatcherProvider: CoroutineDispatcherProvider,
	private val chatClient: ChatClient
) : UsersRepository {

	override suspend fun getUsers(count: Int): List<DomainLayerUsers.SlackUser> {
		return withContext(coroutineMainDispatcherProvider.io) {
			Generator.generateRandomUsers(
				RandomUser.RandomUserSpec(),
				count
			).map {
				mapper.mapToDomain(it)
			}
		}
	}

	override suspend fun login(userName: String): LoginState {
		val name: String = if (userName.contains("@")) {
			userName.split("@").first()
		} else {
			userName
		}
		val user = User(
			id = name,
			name = name,
			image = "http://placekitten.com/200/300"
		)

		val token = chatClient.devToken(user.id)
		val result = chatClient.connectUser(user = user, token = token).await()
		return if (result.isSuccess) {
			LoginState.Success
		} else {
			LoginState.Failure(result.error().message ?: "")
		}
	}

	override suspend fun logout() {
		chatClient.disconnect()
	}
}