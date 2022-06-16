package com.ys.slackclone.domain.repository

import com.ys.slackclone.domain.model.login.LoginState
import com.ys.slackclone.domain.model.users.DomainLayerUsers

interface UsersRepository {
	suspend fun getUsers(count: Int): List<DomainLayerUsers.SlackUser>
	suspend fun login(userName: String): LoginState
	suspend fun logout()
}