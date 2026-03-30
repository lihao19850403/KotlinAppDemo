package com.lihao.kotlinapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var draw: DrawView? = null
    private var drawLayoutParams: FrameLayout.LayoutParams? = null

    private var drawTouchX = 0
    private var drawTouchY = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val contentView: RootFrameLayout = findViewById(R.id.root)
        contentView.setOnTouchListener { view, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    drawTouchX = event.x.toInt()
                    drawTouchY = event.y.toInt()
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    val newX = event.x.toInt()
                    val newY = event.y.toInt()
                    val deltaX = newX - drawTouchX
                    val deltaY = newY - drawTouchY
                    val oldLeftMargin = drawLayoutParams?.leftMargin!!
                    val oldTopMargin = drawLayoutParams?.topMargin!!
                    val newLeftMargin = oldLeftMargin + deltaX
                    val newTopMargin = oldTopMargin + deltaY
                    drawLayoutParams?.leftMargin = 0.coerceAtLeast(newLeftMargin)
                    drawLayoutParams?.topMargin = 0.coerceAtLeast(newTopMargin)
                    draw?.layoutParams = drawLayoutParams
                    drawTouchX = newX
                    drawTouchY = newY
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    contentView.performClick()
                }
            }
            true
        }

        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener { view ->
            draw?.fitMode = !draw?.fitMode!!
            drawLayoutParams = if (draw?.fitMode == true) FrameLayout.LayoutParams(300, 300)
            else FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            drawLayoutParams?.leftMargin = 0
            drawLayoutParams?.topMargin = 0
            draw?.layoutParams = drawLayoutParams
            Toast.makeText(this, if (draw?.fitMode == true) "触摸屏幕" else "触摸圆盘", Toast.LENGTH_SHORT).show()
            Unit
        }

        val jumpBtn = findViewById<Button>(R.id.jumpPage)
        jumpBtn.setOnClickListener { view ->
            val intent = Intent(this, LightEmuActivity::class.java)
            startActivity(intent)
        }

        draw = DrawView(this)
        drawLayoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        draw?.layoutParams = drawLayoutParams
        contentView.addView(draw)

//        manualLayout()
    }

    /* ---------- 另一种操作布局的方式。 ---------- */

    fun manualLayout() {
        val layout = LinearLayout(this)
        setContentView(layout)
        layout.orientation = LinearLayout.VERTICAL
        layout.setBackgroundColor(Color.GREEN)
        val btn = Button(this)
        btn.text = "按钮"
        btn.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layout.addView(btn)
        btn.setOnClickListener { it -> Toast.makeText(this, "单击：$it", Toast.LENGTH_SHORT).show() }
    }
}