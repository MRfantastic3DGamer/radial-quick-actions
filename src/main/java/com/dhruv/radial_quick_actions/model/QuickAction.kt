package com.dhruv.radial_quick_actions.model

/**
 * the base class, children of this class are passed around
 */
abstract class QuickAction(
    val name: String,
    val onSelect: (()->Unit)?
)
