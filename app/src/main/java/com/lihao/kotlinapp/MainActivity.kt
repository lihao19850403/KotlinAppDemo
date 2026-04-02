package com.lihao.kotlinapp

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.lihao.kotlinapp.pages.compose.ComposeActivity
import com.lihao.kotlinapp.pages.datetime.DateTimePickActivity
import com.lihao.kotlinapp.pages.lightemu.LightEmuActivity
import com.lihao.kotlinapp.pages.touchdot.TouchDotActivity

class MainActivity : AppCompatActivity() {

    val mainListItems = arrayListOf(
        MainListItem("触摸原点测试") { startActivity(Intent(this, TouchDotActivity::class.java)) },
        MainListItem("霓虹灯效果") { startActivity(Intent(this, LightEmuActivity::class.java)) },
        MainListItem("日期&时间选择器") { startActivity(Intent(this, DateTimePickActivity::class.java)) },
        MainListItem("Compose页面测试") { startActivity(Intent(this, ComposeActivity::class.java)) },
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val contentList = findViewById<ListView>(R.id.contentList)
        contentList.adapter = MainListAdapter(this,mainListItems)
    }
}