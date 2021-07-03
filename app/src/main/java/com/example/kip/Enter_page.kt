package com.example.kip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class Enter_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_page)


        val button = findViewById<Button>(R.id.enter_button)
        button.setOnClickListener{
            val intent = Intent(this, log_in_page::class.java)
            startActivity(intent)
        }
        val button2 = findViewById<Button>(R.id.enter_button_guest)
        button2.setOnClickListener{
            val intent = Intent(this, log_in_page::class.java)
            startActivity(intent)
        }
    }
}