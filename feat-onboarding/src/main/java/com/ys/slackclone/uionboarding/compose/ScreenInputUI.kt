package com.ys.slackclone.uionboarding.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ys.slackclone.commonui.theme.SlackCloneTheme
import com.ys.slackclone.navigator.ComposeNavigator
import com.ys.slackclone.uionboarding.R

@Composable
fun EmailAddressInputUI(
	composeNavigator: ComposeNavigator,
	onBoardingVM: OnBoardingVM
) {
	SlackCloneTheme {
		CommonInputUI(
			composeNavigator = composeNavigator,
			TopView = { modifier -> EmailInputView(modifier, onBoardingVM) },
			subtitleText = stringResource(id = R.string.subtitle_this_email_slack),
			onBoardingVM = onBoardingVM
		)
	}
}

@Composable
fun WorkspaceInputUI(
	composeNavigator: ComposeNavigator,
	onBoardingVM: OnBoardingVM
) {
	SlackCloneTheme {
		CommonInputUI(
			composeNavigator = composeNavigator,
			TopView = { modifier ->  },
			subtitleText = stringResource(id = R.string.subtitle_this_address_slack),
			onBoardingVM = onBoardingVM
		)
	}
}