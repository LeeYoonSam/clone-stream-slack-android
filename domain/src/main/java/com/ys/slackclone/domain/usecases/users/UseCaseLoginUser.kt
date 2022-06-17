package com.ys.slackclone.domain.usecases.users

import com.ys.slackclone.domain.model.login.LoginState
import com.ys.slackclone.domain.repository.UsersRepository
import com.ys.slackclone.domain.usecases.BaseUseCase

class UseCaseLoginUser(
	private val usersRepository: UsersRepository
) : BaseUseCase<LoginState, String> {

	override suspend fun perform(params: String): LoginState {
		return usersRepository.login(params)
	}
}