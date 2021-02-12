package com.example.kip

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Group_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_activity)

        val button = findViewById<Button>(R.id.button_select)

        button.setOnClickListener {
            val intent = Intent(this, Autorization::class.java)
            startActivity(intent)
        }
    }
}