package com.dhruv.radial_quick_actions.presentation

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.dhruv.radial_quick_actions.RadialQuickActionViewModel

/**
 * the trigger used for taking input and updating state values
 */
@Composable
fun RadialQuickActionTrigger (
    modifier: Modifier = Modifier,
    viewModel: RadialQuickActionViewModel,

    ) {
    Box (
        modifier = modifier
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { it ->
                        viewModel.open(it)
                    },
                    onDrag = { _, offset ->
                        viewModel.drag(offset)
                    },
                    onDragEnd = {
                        viewModel.end()
                    },
                    onDragCancel = {
                        viewModel.end()
                    }
                )
            }
    )
}
