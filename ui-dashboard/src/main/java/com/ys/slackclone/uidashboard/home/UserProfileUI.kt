package com.ys.slackclone.uidashboard.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ys.slackclone.commonui.theme.SlackCloneColorProvider

@Composable
fun RoundedCornerBoxDecoration(content: @Composable () -> Unit) {
	Box(
		Modifier
			.border(
				width = 1.dp,
				color = SlackCloneColorProvider.colors.lineColor,
				shape = RoundedCornerShape(12.dp)
			)
			.padding(12.dp)
	) {
		content()
	}
}
