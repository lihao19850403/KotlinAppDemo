package com.lihao.kotlinapp.components

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class RootFrameLayout : FrameLayout {

    constructor(context: Context): super(context)

    constructor(context: Context, set: AttributeSet): super(context, set)

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }
}