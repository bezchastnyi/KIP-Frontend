package com.example.kip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Class_schedule : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_schedule)
        val button = findViewById<ImageButton>(R.id.back_button)

        button.setOnClickListener{
            val intent = Intent(this, Schedule_swipe::class.java)
            startActivity(intent)
        }

    }
}