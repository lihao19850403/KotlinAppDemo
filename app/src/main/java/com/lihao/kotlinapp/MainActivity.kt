package com.lihao.kotlinapp

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val contentView: LinearLayout = findViewById(R.id.root)

        val draw = DrawView(this)
        draw.minimumWidth = 300
        draw.minimumHeight = 500
        contentView.addView(draw)
    }
}