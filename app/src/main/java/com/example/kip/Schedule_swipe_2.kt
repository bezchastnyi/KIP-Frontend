package com.example.kip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class Schedule_swipe_2 : AppCompatActivity() {
    private lateinit var layout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_swipe_2)
        layout = findViewById(R.id.swipe_layout_2)
        layout.setOnTouchListener(object : OnSwipeTouchListener(this@Schedule_swipe_2) {
            override fun onSwipeLeft() {
                val intent = Intent(this@Schedule_swipe_2, Schedule_swipe::class.java)
                startActivity(intent)
            }
            override fun onSwipeRight() {
                val intent = Intent(this@Schedule_swipe_2, Schedule_swipe::class.java)
                startActivity(intent)
            }

        })
    }
}