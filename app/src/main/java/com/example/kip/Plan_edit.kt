package com.example.kip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Plan_edit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_edit)

        val button = findViewById<Button>(R.id.Button_add_plan)
        button.setOnClickListener{
            val intent = Intent(this, Main_screen::class.java)
            startActivity(intent)
        }

    }
}