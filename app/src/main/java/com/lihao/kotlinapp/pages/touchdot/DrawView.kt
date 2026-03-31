package com.lihao.kotlinapp.pages.touchdot

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawView : View {

    /**
     * 是否采用固定模式。<br />
     * 固定模式下控件自身不响应触摸，在屏幕上的位置由父容器控制。
     */
    var fitMode: Boolean = false
        set(value) {
            field = value
            if (value) {
                currentX = 150f
                currentY = 150f
                p.color = Color.RED
            } else {
                currentX = 50f
                currentY = 50f
                p.color = Color.GREEN
            }
            postInvalidate()
        }

    private var currentX: Float = 50f

    private var currentY: Float = 50f

    private var p = Paint()

    constructor(context: Context): super(context)

    constructor(context: Context, set: AttributeSet): super(context, set)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        p.color = if (fitMode) Color.RED else Color.GREEN
        canvas?.drawCircle(currentX, currentY, 150f, p)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (!fitMode) {
            event?.let {
                currentX = it.x
                currentY = it.y
                invalidate()
            }
        }
        if (event?.action == MotionEvent.ACTION_UP) {
            performClick()
        }
        return true
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }
}