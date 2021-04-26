package com.example.kip

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Autorization : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autorization)
        val button = findViewById<Button>(R.id.autorizate)
        val button2 = findViewById<Button>(R.id.Enter_button_wtiout_auto)
        
        button.setOnClickListener {
            val intent = Intent(this, Main_screen::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener {
            val intent = Intent(this, Group_activity::class.java)
            startActivity(intent)
        }
    }
}