package com.example.kip

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class Schedule_swipe : AppCompatActivity() {
        private lateinit var layout:LinearLayout
        private lateinit var layout2:LinearLayout
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_schedule_swipe)


            val button3 = findViewById<ImageButton>(R.id.back_button_SW)
            button3.setOnClickListener {
                val intent = Intent(this, Group_activity::class.java)
                startActivity(intent)
            }

            val button = findViewById<ImageButton>(R.id.swipe2)
            if(groupValid) {
                button.setOnClickListener {
                    selectedScheduleType=0
                    val intent = Intent(this, Day_schedule_selection::class.java)
                    startActivity(intent)
                }
            }
            val button2 = findViewById<ImageButton>(R.id.swipe)
            if(profValid) {
                button2.setOnClickListener {
                    selectedScheduleType=1
                    val intent = Intent(this, Big_object_selection::class.java)
                    startActivity(intent)
                }
            }

        }

}