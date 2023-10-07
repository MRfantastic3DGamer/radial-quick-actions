package com.dhruv.radial_quick_actions

import android.graphics.Point
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.round
import androidx.lifecycle.ViewModel
import com.dhruv.radial_quick_actions.model.QuickAction
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

class RadialQuickActionViewModel(
    val actions: List<QuickAction>,
    private val selectionRadios: Float = 200F,
    private val onOpen: (()->Unit)? = null,
    private val onDrag: ((selected: Int, angle: Float, distance: Float)->Unit)? = null,
    private val onEnd: ((selected: Int)->Unit)? = null,
) : ViewModel() {

    var opened by mutableStateOf(false)
    var angles by mutableStateOf(listOf<Float>())

    init {
        this.angles = getAngles(actions.size)
    }

    var selected by mutableStateOf(0)
    var A by mutableStateOf(Point(-1,0))
    var B by mutableStateOf(Point( 0,0))
    var C by mutableStateOf(Point( 0,0))
    private var currentAngle: Float by mutableStateOf(0F)
    private var currentDistance: Float by mutableStateOf(0F)

    val numberOfActions :Int
        get() {
            return actions.size
        }

    var quickActionsOffset by mutableStateOf(IntOffset(0,0))

    fun open(offset: Offset) {
        opened = true
        quickActionsOffset = offset.round()
        C.x = 0
        C.y = 0
        onOpen?.invoke()
    }

    fun drag(offset: Offset){
        C.x += offset.x.toInt()
        C.y += offset.y.toInt()
        currentAngle = 275 - calculateAngle(A, B, C).toFloat()
        currentDistance = sqrt(C.x * C.x + C.y * C.y.toFloat())
        if(currentDistance < selectionRadios) selected = -1
        else
            selected = max(min(if(numberOfActions == 0) 0 else currentAngle.toInt() / (180 / numberOfActions) , actions.size-1 ), 0)
        onDrag?.invoke(selected, currentAngle, currentDistance)
    }

    fun end() {
        opened = false
        onEnd?.invoke(selected)

        if(selected>=0)
            actions[selected].onSelect?.invoke()
    }
}