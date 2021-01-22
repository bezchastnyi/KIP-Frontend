package com.example.kip

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kip_start)

        val button = findViewById<Button>(R.id.button_start)
        button.setOnClickListener{
            val intent = Intent(this, Autorization::class.java)
            startActivity(intent)
        }
        
        /*
        val button2 = findViewById<Button>(R.id.autorizate)
        button2.setOnClickListener{

            val intent = Intent(this, main_window::class.java)
            startActivity(intent)
        }
        */
    }
}
