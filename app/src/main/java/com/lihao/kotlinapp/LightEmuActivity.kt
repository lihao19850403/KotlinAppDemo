package com.lihao.kotlinapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference
import java.util.Timer
import java.util.TimerTask

class LightEmuActivity : AppCompatActivity() {

    internal val names =
        intArrayOf(R.id.view01, R.id.view02, R.id.view03, R.id.view04, R.id.view05, R.id.view06)

    private var views = arrayOfNulls<FrameLayout>(names.size)

    private var handler: Handler = MyHandler(WeakReference(this))

    class MyHandler(private val activity: WeakReference<LightEmuActivity>) :
        Handler(Looper.myLooper()!!) {
        private var currentColor = 0
        internal val colors = intArrayOf(
            android.R.color.holo_red_light,
            android.R.color.holo_blue_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_green_light,
            android.R.color.holo_purple,
            android.R.color.holo_red_dark
        )

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 0x123) {
                for (i in activity.get()?.names?.indices!!) {
                    activity.get()?.views!![i]?.setBackgroundResource(colors[(i + currentColor) % colors.size])
                }
                currentColor++
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_light_emu)

        for (i in names.indices) {
            views[i] = findViewById(names[i])
        }

        Timer().schedule(object : TimerTask() {
            override fun run() {
                handler.sendEmptyMessage(0x123)
            }
        }, 0, 100)
    }

    override fun onDestroy() {
        super.onDestroy()

        Timer().cancel()
    }
}