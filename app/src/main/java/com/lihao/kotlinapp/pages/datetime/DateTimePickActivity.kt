package com.lihao.kotlinapp.pages.datetime

import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.lihao.kotlinapp.R
import java.util.Calendar

class DateTimePickActivity : AppCompatActivity() {

    private var dateInfo: String = ""
    private var timeInfo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_time_pick)

        val info = findViewById<TextView>(R.id.info)
        val datePicker = findViewById<DatePicker>(R.id.datePicker)
        val timePicker = findViewById<TimePicker>(R.id.timePicker)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        datePicker.init(year, month, day) { view, year, month, day ->
            dateInfo = "${year}年${month}月${day}日"
            info.text = String.format(getString(R.string.two_strings_in_blank), dateInfo, timeInfo)
        }

        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        timePicker.hour = hour
        timePicker.minute = minute
        timePicker.setOnTimeChangedListener { view, hour, minute ->
            timeInfo = "${hour}时${minute}分"
            info.text = String.format(getString(R.string.two_strings_in_blank), dateInfo, timeInfo)
        }

        dateInfo = "${year}年${month}月${day}日"
        timeInfo = "${hour}时${minute}分"
        info.text = String.format(getString(R.string.two_strings_in_blank), dateInfo, timeInfo)
    }
}