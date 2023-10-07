package com.dhruv.radial_quick_actions.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import com.dhruv.radial_quick_actions.RadialQuickActionViewModel
import com.dhruv.radial_quick_actions.model.QuickAction
import kotlin.math.cos
import kotlin.math.sin

/**
 * draws the actual buttons
 * @param viewModel the model used to store the data about state
 * @param actionItem the composable for every individual button that uses a modifier to get position, data object ,and boolean to be used when the button is focused
 */
@Composable
fun RadialQuickActionVisual(
    modifier: Modifier = Modifier,
    viewModel: RadialQuickActionViewModel,
    radios: Float = 400F,
    actionItem: @Composable (modifier: Modifier, quickAction: QuickAction, selected: Boolean)->Unit
) {
    val openVal by animateFloatAsState(targetValue = if (viewModel.opened) 1f else 0f, label = "radial_actions_open_val")
    Box(modifier = modifier){
        if(viewModel.opened || openVal > 0.2){
            for (i in 0 until viewModel.numberOfActions){
                actionItem(
                    Modifier
                        .offset {
                            IntOffset(
                                viewModel.quickActionsOffset.x + (cos((viewModel.angles[i] + 90) * Math.PI / 180) * radios * openVal).toInt(),
                                viewModel.quickActionsOffset.y + (sin((viewModel.angles[i] + 90) * Math.PI / 180) * radios * openVal).toInt()
                            )
                        },
                    viewModel.actions[i],
                    (i == viewModel.selected)
                )
            }
        }
    }
}
