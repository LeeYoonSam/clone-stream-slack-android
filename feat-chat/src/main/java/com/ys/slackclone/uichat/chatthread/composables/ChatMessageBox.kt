package com.ys.slackclone.uichat.chatthread.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.ys.slackclone.commonui.keyboard.Keyboard
import com.ys.slackclone.commonui.keyboard.keyboardAsState
import com.ys.slackclone.commonui.theme.SlackCloneColorProvider
import com.ys.slackclone.commonui.theme.SlackCloneTypography
import com.ys.slackclone.uichat.chatthread.BoxState
import com.ys.slackclone.uichat.chatthread.ChatScreenVM
import io.getstream.chat.android.client.models.Channel

@Composable
fun ChatMessageBox(viewModel: ChatScreenVM, slackChannel: Channel, modifier: Modifier) {
	val keyboard by keyboardAsState()

	SideEffect {
		if (keyboard is Keyboard.Closed) {
			viewModel.chatBoxState.value = BoxState.Collapsed
		}
	}

	Column(
		modifier = modifier.background(SlackCloneColorProvider.colors.uiBackground),
		verticalArrangement = Arrangement.SpaceBetween
	) {
		MessageTFRow(
			viewModel = viewModel,
			slackChannel = slackChannel,
			modifier = Modifier.padding(start = 4.dp)
		)

		if (keyboard is Keyboard.Opened) {
			ChatOptions(
				viewModel = viewModel,
				slackChannel = slackChannel,
				Modifier
			)
		}
	}
}

@Composable
fun ChatOptions(viewModel: ChatScreenVM, slackChannel: Channel, modifier: Modifier = Modifier) {
	val search by viewModel.message.collectAsState()

	Row(
		modifier = modifier
			.fillMaxWidth(),
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		Row(modifier = Modifier.weight(1f)) {
			IconButton(onClick = { /*TODO*/ }) {
				Icon(imageVector = Icons.Outlined.Add, contentDescription = null, chatOptionIconSize())
			}
			IconButton(onClick = { /*TODO*/ }) {
				Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = null, chatOptionIconSize())
			}
			IconButton(onClick = { /*TODO*/ }) {
				Icon(imageVector = Icons.Outlined.Email, contentDescription = null, chatOptionIconSize())
			}
			IconButton(onClick = { /*TODO*/ }) {
				Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = null, chatOptionIconSize())
			}
			IconButton(onClick = { /*TODO*/ }) {
				Icon(imageVector = Icons.Outlined.Phone, contentDescription = null, chatOptionIconSize())
			}
			IconButton(onClick = { /*TODO*/ }) {
				Icon(imageVector = Icons.Outlined.MailOutline, contentDescription = null, chatOptionIconSize())
			}
		}
		Box(Modifier.padding(end = 9.dp)) {
			SendMessageButton(viewModel = viewModel, message = search, slackChannel = slackChannel)
		}
	}
}

private fun chatOptionIconSize() = Modifier.size(20.dp)

@Composable
private fun MessageTFRow(
	viewModel: ChatScreenVM,
	slackChannel: Channel,
	modifier: Modifier
) {
	val keyboard by keyboardAsState()
	val search by viewModel.message.collectAsState()

	Column {
		Divider(color = SlackCloneColorProvider.colors.lineColor, thickness = 0.5.dp)
		Row(
			modifier = modifier
		) {
			BasicTextField(
				value = search,
				maxLines = 4,
				cursorBrush = SolidColor(SlackCloneColorProvider.colors.textPrimary),
				onValueChange = {
					viewModel.message.value = it
				},
				textStyle = SlackCloneTypography.subtitle1.copy(
					color = SlackCloneColorProvider.colors.textPrimary
				),
				decorationBox = { innerTextField ->
					ChatTFPlusPlaceHolder(
						search = search,
						modifier = Modifier,
						innerTextField = innerTextField
					)
				}
			)

			if (keyboard is Keyboard.Closed) {
				SendMessageButton(
					viewModel = viewModel,
					message = search,
					slackChannel = slackChannel
				)
			} else {
				CollapseExpandButton(viewModel)
			}
		}
	}
}

@Composable
fun CollapseExpandButton(viewModel: ChatScreenVM) {
	val isExpanded by viewModel.chatBoxState.collectAsState()

	IconButton(
		onClick = {
			viewModel.switchChatBoxState()
		}
	) {
		Icon(
			imageVector = Icons.Default.KeyboardArrowUp,
			contentDescription = null,
			modifier = Modifier.graphicsLayer {
				rotationZ = if (isExpanded != BoxState.Collapsed) 180f else 0f
			}
		)
	}
}

@Composable
private fun SendMessageButton(
	modifier: Modifier = Modifier,
	viewModel: ChatScreenVM,
	message: String,
	slackChannel: Channel
) {
	IconButton(
		onClick = {
			viewModel.sendMessage(slackChannel.cid, message)
		},
		enabled = message.isNotEmpty(),
		modifier = modifier
	) {
		Icon(
			imageVector = Icons.Default.Send,
			contentDescription = null,
			tint = if (message.isEmpty()) {
				SlackCloneColorProvider.colors.sendButtonDisabled
			} else {
				SlackCloneColorProvider.colors.sendButtonEnabled
			}
		)
	}
}

@Composable
private fun ChatTFPlusPlaceHolder(
	search: String,
	modifier: Modifier = Modifier,
	innerTextField: @Composable () -> Unit
) {
	Row(
		modifier = modifier.padding(16.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		if (search.isEmpty()) {
			Text(
				text = "Sends a message",
				style = SlackCloneTypography.subtitle1.copy(
					color = SlackCloneColorProvider.colors.textSecondary
				),
				modifier = Modifier.weight(1f)
			)
		} else {
			innerTextField()
		}
	}
}