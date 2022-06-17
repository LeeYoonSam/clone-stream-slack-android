package com.ys.slackclone.domain.usecases.users

import com.ys.slackclone.domain.repository.UsersRepository
import com.ys.slackclone.domain.usecases.BaseUseCase

class UseCaseLogoutUser(
	private val usersRepository: UsersRepository
) : BaseUseCase<Unit, Unit> {

	override suspend fun perform() {
		return usersRepository.logout()
	}
}