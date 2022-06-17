package com.ys.slackclone.domain.usecases.users

import com.ys.slackclone.domain.model.users.DomainLayerUsers
import com.ys.slackclone.domain.repository.UsersRepository
import com.ys.slackclone.domain.usecases.BaseUseCase

class UseCaseFetchUsers(
	private val usersRepository: UsersRepository
) : BaseUseCase<List<DomainLayerUsers.SlackUser>, Int> {
	override suspend fun perform(params: Int): List<DomainLayerUsers.SlackUser> {
		return usersRepository.getUsers(params)
	}
}