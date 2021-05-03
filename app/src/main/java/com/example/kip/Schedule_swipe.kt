package com.example.kip

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class Schedule_swipe : AppCompatActivity() {
        private lateinit var layout:LinearLayout
        private lateinit var layout2:LinearLayout
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_schedule_swipe)


            val button = findViewById<ImageButton>(R.id.swipe2)
            if(groupValid) {
                button.setOnClickListener {
                    val intent = Intent(this, Class_schedule::class.java)
                    startActivity(intent)
                }
            }
            val button2 = findViewById<ImageButton>(R.id.swipe)
            if(profValid) {
                button2.setOnClickListener {
                    val intent = Intent(this, lecture_schedule::class.java)
                    startActivity(intent)
                }
            }

        }

}