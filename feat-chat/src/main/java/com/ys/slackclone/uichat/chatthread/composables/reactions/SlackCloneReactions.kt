package com.ys.slackclone.uichat.chatthread.composables.reactions

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.getstream.chat.android.client.models.Message
import io.getstream.chat.android.client.models.Reaction
import io.getstream.chat.android.common.state.MessageAction
import io.getstream.chat.android.common.state.React
import io.getstream.chat.android.compose.state.reactionoptions.ReactionOptionItemState
import io.getstream.chat.android.compose.ui.components.reactionoptions.ReactionOptions
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.chat.android.compose.ui.util.ReactionIcon

@Composable
fun SlackCloneReactions(
    message: Message,
    reactionTypes: Map<String, ReactionIcon> = SlackCloneReactionFactory().createReactionIcons(),
    onMessageAction: (MessageAction) -> Unit
) {
    ReactionOptions(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 20.dp),
        reactionTypes = reactionTypes,
        ownReactions = message.ownReactions,
        onReactionOptionSelected = {
            onMessageAction(
                React(
                    reaction = Reaction(messageId = message.id, type = it.type),
                    message = message
                )
            )
        },
        onShowMoreReactionsSelected = { /*TODO*/ }
    )
}

@Composable
internal fun ReactionOptions(
    ownReactions: List<Reaction>,
    onReactionOptionSelected: (ReactionOptionItemState) -> Unit,
    modifier: Modifier = Modifier,
    numberOfReactionsShown: Int = 5,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    reactionTypes: Map<String, ReactionIcon> = ChatTheme.reactionIconFactory.createReactionIcons(),
    itemContent: @Composable RowScope.(ReactionOptionItemState) -> Unit = { option ->
        DefaultReactionOptionItem(
            option = option,
            onReactionOptionSelected = onReactionOptionSelected
        )
    }
) {
    val options = reactionTypes.entries.map { (type, reactionIcon) ->
        val isSelected = ownReactions.any { ownReactions -> ownReactions.type == type }
        val painter = reactionIcon.getPainter(isSelected)
        ReactionOptionItemState(
            painter = painter,
            type = type
        )
    }

    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement
    ) {
        options.take(numberOfReactionsShown).forEach { option ->
            key(option.type) {
                itemContent(option)
            }
        }
    }
}

/**
 * 기본 리액션 옵션 아이템
 *
 * @param option 옵션 표시
 * @param onReactionOptionSelected 옵션이 선택되었을 때의 핸들러
 */
@Composable
internal fun DefaultReactionOptionItem(
    option: ReactionOptionItemState,
    onReactionOptionSelected: (ReactionOptionItemState) -> Unit
) {
    var animationState by remember { mutableStateOf(ReactionState.START) }

    val springValue: Float by animateFloatAsState(
        targetValue = if (animationState == ReactionState.START) 0f else 1f,
        spring(dampingRatio = 0.10f, stiffness = Spring.StiffnessLow)
    )

    LaunchedEffect(Unit) {
        animationState = ReactionState.END
    }

    CustomReactionOptionItem(
        option = option,
        springValue = springValue,
        onReactionOptionSelected = { onReactionOptionSelected(option) }
    )
}

enum class ReactionState {
    START,
    END
}