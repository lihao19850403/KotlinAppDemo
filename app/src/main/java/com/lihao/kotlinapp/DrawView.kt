package com.lihao.kotlinapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawView : View {

    private var currentX: Float = 40f

    private var currentY: Float = 50f

    private var p = Paint()

    constructor(context: Context): super(context)

    constructor(context: Context, set: AttributeSet): super(context, set)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        p.color = Color.RED
        canvas?.drawCircle(currentX, currentY, 150f, p)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            currentX = it.x
            currentY = it.y
            invalidate()
        }
        return true
    }






}









