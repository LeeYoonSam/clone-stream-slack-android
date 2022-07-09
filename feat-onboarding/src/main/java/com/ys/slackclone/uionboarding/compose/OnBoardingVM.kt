package com.ys.slackclone.uionboarding.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ys.slackclone.domain.model.login.LoginState
import com.ys.slackclone.domain.usecases.users.UseCaseLoginUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingVM @Inject constructor(
	private val useCaseLoginUser: UseCaseLoginUser
) : ViewModel() {

	val input: MutableStateFlow<String> = MutableStateFlow("")
	val loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Nothing)

	fun connectUser() {
		viewModelScope.launch {
			loginState.value = LoginState.Loading
			loginState.value = useCaseLoginUser.perform(input.value)
		}
	}
}