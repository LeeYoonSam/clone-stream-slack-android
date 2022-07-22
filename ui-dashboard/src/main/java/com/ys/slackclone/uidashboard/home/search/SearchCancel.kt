package com.ys.slackclone.uidashboard.home.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.ys.slackclone.commonui.keyboard.Keyboard
import com.ys.slackclone.commonui.keyboard.keyboardAsState
import com.ys.slackclone.commonui.theme.SlackCloneTypography

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchCancel() {
	val keyboardController = LocalSoftwareKeyboardController.current

	Row {
		val isKeyboardOpen by keyboardAsState()
		var search by remember { mutableStateOf("") }
		
		SearchMessagesTF(modifier = Modifier.weight(1f), search) { newValue ->
			search = newValue
		}
		
		AnimatedVisibility(visible = isKeyboardOpen is Keyboard.Opened) {
			TextButton(
				onClick = {
					search = ""
					keyboardController?.hide()
				}
			) {
				Text(
					text = "Cancel",
					style = SlackCloneTypography.subtitle1.copy(color = Color.White)
				)
			}
		}
	}
}

@Composable
private fun SearchMessagesTF(modifier: Modifier, search: String, onValueChange: (String) -> Unit) {
	BasicTextField(
		value = search,
		onValueChange = { newSearch ->
			onValueChange(newSearch)
		},
		textStyle = SlackCloneTypography.subtitle1.copy(
			color = Color.White
		),
		decorationBox = { innerTextField ->
			Row(
				modifier = Modifier
					.background(
						color = Color.White.copy(alpha = 0.2f),
						shape = RoundedCornerShape(12.dp)
					)
					.padding(8.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				Icon(
					imageVector = Icons.Default.Search,
					contentDescription = null,
					tint = Color.White
				)
				if (search.isEmpty()) {
					Text(
						text = "Search for messages and files",
						style = SlackCloneTypography.subtitle1.copy(
							color = Color.White
						),
						modifier = Modifier.weight(1f)
					)
				} else {
					innerTextField()
				}
			}
		},
		modifier = modifier.padding(8.dp),
		cursorBrush = SolidColor(Color.White)
	)
}