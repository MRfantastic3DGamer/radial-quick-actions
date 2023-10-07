package com.dhruv.radial_quick_actions.model

abstract class QuickAction(
    val name: String,
    val onSelect: (()->Unit)?
)