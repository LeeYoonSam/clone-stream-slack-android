package com.ys.slackclone.commonui.reusable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import com.ys.slackclone.commonui.R
import com.ys.slackclone.commonui.theme.ShimmerBackground
import com.ys.slackclone.commonui.theme.ShimmerHighLight
import com.ys.slackclone.commonui.theme.SlackCloneColorProvider
import com.ys.slackclone.commonui.theme.SlackCloneSurface

@Composable
fun SlackImageBox(modifier: Modifier, imageModel: Any) {
	GlideImage(
		imageModel = imageModel,
		previewPlaceholder = R.drawable.logo_stream,
		shimmerParams = ShimmerParams(
			highlightColor = ShimmerHighLight,
			baseColor = ShimmerBackground
		),
		modifier = modifier.clip(RoundedCornerShape(8.dp))
	)
}

@Composable
fun SlackOnlineBox(
	imageModel: Any,
	parentModifier: Modifier = Modifier.size(34.dp),
	imageModifier: Modifier = Modifier.size(28.dp)
) {
	ConstraintLayout(parentModifier) {
		val (image, indicator) = createRefs()
		SlackImageBox(
			modifier = imageModifier
				.constrainAs(image) {
					top.linkTo(parent.top)
					bottom.linkTo(parent.bottom)
					start.linkTo(parent.start)
					end.linkTo(parent.end)
				},
			imageModel = imageModel
		)

		SlackCloneSurface(
			shape = CircleShape,
			border = BorderStroke(3.dp, color = SlackCloneColorProvider.colors.uiBackground),
			modifier = Modifier
				.constrainAs(indicator) {
					bottom.linkTo(parent.bottom)
					end.linkTo(parent.end)
				}
				.size(14.dp)
		) {
			Box(
				modifier = Modifier
					.size(12.dp)
					.clip(CircleShape)
					.background(Color.Green)
			)

		}
	}
}

@Preview
@Composable
fun PrevSlackOnlineBox() {
	SlackOnlineBox(
		imageModel = R.drawable.logo_stream
	)
}
