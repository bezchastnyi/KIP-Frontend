package com.example.kip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Lecture_schedule_selected : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecture_schedule_selected)

        val button = findViewById<ImageButton>(R.id.back_button)
        button.setOnClickListener{
            val intent = Intent(this, Main_screen::class.java)
            startActivity(intent)
        }

    }
}