package com.ys.slackclone.uichannels.createsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ys.slackclone.domain.model.channel.DomainLayerChannels
import com.ys.slackclone.domain.usecases.channels.UseCaseCreateLocalChannel
import com.ys.slackclone.navigator.ComposeNavigator
import com.ys.slackclone.navigator.NavigationKeys
import com.ys.slackclone.navigator.SlackScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateChannelVM @Inject constructor(
	private val composeNavigator: ComposeNavigator,
	private val useCaseCreateChannel: UseCaseCreateLocalChannel
) : ViewModel() {

	val channel =
		MutableStateFlow(DomainLayerChannels.SlackChannel(isOneToOne = false, avatarUrl = null))

	fun createChannel() {
		viewModelScope.launch {
			if (channel.value.name?.isNotEmpty() == true) {
				val channel = useCaseCreateChannel.perform(channel.value)
				composeNavigator.navigateBackWithResult(
					NavigationKeys.navigateChannel,
					channel?.uuid!!,
					SlackScreen.Dashboard.name
				)
			}
		}
	}
}