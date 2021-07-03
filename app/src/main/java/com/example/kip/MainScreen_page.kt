package com.example.kip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainScreen_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen_page)
        val button = findViewById<Button>(R.id.button1)
        button.setOnClickListener{
            students = false
            selectedScheduleType = 0
            val intent = Intent(this, select_day_schedule_page::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<ImageButton>(R.id.buidlingButton)
        button2.setOnClickListener{
            selectedScheduleType = 1
            val intent = Intent(this, select_BuidlingFaculty_page::class.java)
            startActivity(intent)
        }

        val button3 = findViewById<ImageButton>(R.id.proffButton)
        button3.setOnClickListener{
            selectedScheduleType = 2
            val intent = Intent(this, select_BuidlingFaculty_page::class.java)
            startActivity(intent)
        }
    }
}