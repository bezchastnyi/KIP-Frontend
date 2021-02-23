package com.example.kip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class lecture_schedule : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecture_schedule)
        val button = findViewById<ImageButton>(R.id.back_button)

        button.setOnClickListener{
            val intent = Intent(this, Schedule_swipe_2::class.java)
            startActivity(intent)
        }
    }
}