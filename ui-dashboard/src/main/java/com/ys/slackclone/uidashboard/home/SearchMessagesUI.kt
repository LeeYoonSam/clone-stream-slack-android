package com.ys.slackclone.uidashboard.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ys.slackclone.common.R.string
import com.ys.slackclone.commonui.material.SlackSurfaceAppBar
import com.ys.slackclone.commonui.reusable.SlackListItem
import com.ys.slackclone.commonui.theme.SlackCloneColorProvider
import com.ys.slackclone.commonui.theme.SlackCloneSurface
import com.ys.slackclone.commonui.theme.SlackCloneTypography
import com.ys.slackclone.uidashboard.home.search.SearchCancel

@Composable
fun SearchMessagesUI() {
	SlackCloneSurface(
		color = SlackCloneColorProvider.colors.uiBackground,
		modifier = Modifier.fillMaxSize()
	) {
		Column {
			SearchTopAppBar()
			Content()
		}
	}
}

@Composable
private fun SearchTopAppBar() {
	SlackSurfaceAppBar(
		backgroundColor = SlackCloneColorProvider.colors.appBarColor,
		contentPadding = PaddingValues(8.dp)
	) {
		SearchCancel()
	}
}

@Composable
private fun Content() {
	Column(Modifier.verticalScroll(rememberScrollState())) {
		SlackListItem(
			icon = Icons.Default.ShoppingCart,
			title = stringResource(id = string.browse_people)
		)
		SlackListItem(
			icon = Icons.Default.Search,
			title = stringResource(id = string.browse_channels)
		)
		SlackListDivider()
		// Recent Searches
		SearchText(title = stringResource(id = string.recent_searches))
		repeat(5) {
			SlackListItem(
				icon = Icons.Default.Favorite,
				title = "in:#android_india",
				trailingItem = Icons.Default.Clear
			)
		}
		SlackListDivider()
		// Narrow Your Search
		SearchText(title = stringResource(id = string.narrow_your_search))
		repeat(5) {
			SlackListItemTrailingView(
				icon = Icons.Default.Favorite,
				title = "from:",
				trailingView =  {
					Text(text = "Ex: @albert")
				}
			)
		}
	}
}

@Composable
private fun SearchText(title: String) {
	Text(
		text = title,
		style = SlackCloneTypography.caption.copy(fontWeight = FontWeight.SemiBold),
		modifier = Modifier.padding(16.dp)
	)
}

@Composable
fun SlackListDivider() {
	Divider(color = SlackCloneColorProvider.colors.lineColor, thickness = 0.5.dp)
}