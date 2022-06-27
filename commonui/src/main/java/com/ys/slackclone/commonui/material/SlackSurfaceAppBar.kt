package com.ys.slackclone.commonui.material

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.ys.slackclone.commonui.theme.SlackCloneSurface

@Composable
fun SlackSurfaceAppBar(
	title: @Composable () -> Unit,
	modifier: Modifier = Modifier,
	navigationIcon: @Composable (() -> Unit)? = null,
	actions: @Composable RowScope.() -> Unit = {},
	backgroundColor: Color = MaterialTheme.colors.primarySurface,
	contentColor: Color = contentColorFor(backgroundColor),
	elevation: Dp = AppBarDefaults.TopAppBarElevation
) {
	SlackCloneSurface(
		color = backgroundColor,
		contentColor = contentColor,
		elevation = elevation
	) {
		TopAppBar(
			title, modifier, navigationIcon, actions, backgroundColor, contentColor, elevation
		)
	}
}

@Composable
fun SlackSurfaceAppBar(
	modifier: Modifier = Modifier,
	backgroundColor: Color = MaterialTheme.colors.primarySurface,
	contentColor: Color = contentColorFor(backgroundColor),
	elevation: Dp = AppBarDefaults.TopAppBarElevation,
	contentPadding: PaddingValues = AppBarDefaults.ContentPadding,
	content: @Composable RowScope.() -> Unit
) {
	SlackCloneSurface(
		color = backgroundColor,
		contentColor = contentColor,
		elevation = elevation
	) {
		TopAppBar(
			modifier, backgroundColor, contentColor, elevation, contentPadding, content
		)
	}
}