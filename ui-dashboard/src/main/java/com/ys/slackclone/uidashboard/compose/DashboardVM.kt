package com.ys.slackclone.uidashboard.compose

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ys.slackclone.chatcore.data.UiLayerChannels
import com.ys.slackclone.domain.mappers.UiModelMapper
import com.ys.slackclone.domain.model.channel.DomainLayerChannels
import com.ys.slackclone.domain.usecases.channels.UseCaseCreateLocalChannels
import com.ys.slackclone.domain.usecases.channels.UseCaseGetChannel
import com.ys.slackclone.domain.usecases.users.UseCaseFetchUsers
import com.ys.slackclone.domain.usecases.users.UseCaseLogoutUser
import com.ys.slackclone.navigator.ComposeNavigator
import com.ys.slackclone.navigator.NavigationKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardVM @Inject constructor(
	private val savedStateHandle: SavedStateHandle,
	private val composeNavigator: ComposeNavigator,
	private val useCaseGetChannel: UseCaseGetChannel,
	private val useCaseFetchUsers: UseCaseFetchUsers,
	private val useCaseLogoutUser: UseCaseLogoutUser,
	private val useCaseSaveChannel: UseCaseCreateLocalChannels,
	private val channelMapper: UiModelMapper<DomainLayerChannels.SlackChannel, UiLayerChannels.SlackChannel>
) : ViewModel() {

	val selectedChatChannel = MutableStateFlow<UiLayerChannels.SlackChannel?>(null)
	val isChatViewClosed = MutableStateFlow(true)

	init {
		observeChannelCreated()
		preloadUsers()
	}

	private fun observeChannelCreated() {
		composeNavigator.observeResult<String>(
			NavigationKeys.navigateChannel
		).onStart {
			val message = savedStateHandle.get<String>(NavigationKeys.navigateChannel)
			message?.let {
				emit(it)
			}
		}.map {
			useCaseGetChannel.perform(it)
		}.onEach { slackChannel ->
			navigateChatThreadForChannel(slackChannel)
		}.launchIn(viewModelScope)

		selectedChatChannel.onEach {
			savedStateHandle.set(NavigationKeys.navigateChannel, it?.uuid)
		}.launchIn(viewModelScope)
	}

	private fun navigateChatThreadForChannel(slackChannel: DomainLayerChannels.SlackChannel?) {
		slackChannel?.let {
			selectedChatChannel.value = channelMapper.mapToPresentation(it)
			isChatViewClosed.value = false
		}
	}

	private fun preloadUsers() {
		viewModelScope.launch {
			val users = useCaseFetchUsers.perform(10)
			useCaseSaveChannel.perform(users)
		}
	}

	fun logout() {
		viewModelScope.launch {
			useCaseLogoutUser.perform()
		}
	}
}