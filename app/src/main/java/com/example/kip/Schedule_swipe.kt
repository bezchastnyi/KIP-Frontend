package com.example.kip

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class Schedule_swipe : AppCompatActivity() {
        private lateinit var layout:LinearLayout
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_schedule_swipe)

            val button = findViewById<ImageButton>(R.id.Swipe)
            button.setOnClickListener{
                val intent = Intent(this, Class_schedule::class.java)
                startActivity(intent)
            }

            layout = findViewById(R.id.swipe_layout)
            layout.setOnTouchListener(object : OnSwipeTouchListener(this@Schedule_swipe) {
                override fun onSwipeLeft() {
                    val intent = Intent(this@Schedule_swipe, Schedule_swipe_2::class.java)
                    startActivity(intent)
                }
                override fun onSwipeRight() {
                    val intent = Intent(this@Schedule_swipe, Schedule_swipe_2::class.java)
                    startActivity(intent)
                }
                override fun onSwipeUp() {
                    val intent = Intent(this@Schedule_swipe, Main_screen::class.java)
                    startActivity(intent)
                }
            })
        }

}