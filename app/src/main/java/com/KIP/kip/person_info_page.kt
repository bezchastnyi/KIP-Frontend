package com.KIP.kip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class person_info_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_info_page)
        findViewById<TextView>(R.id.Name).text = "${studentList[0].firstName} ${studentList[0].lastName}"
        val button = findViewById<Button>(R.id.button5)
        button.setOnClickListener {
            val intent = Intent(this, self_info_page::class.java)
            startActivity(intent)
        }
        val button2 = findViewById<Button>(R.id.button6)
        button2.setOnClickListener {
            val intent = Intent(this, Enter_page::class.java)
            startActivity(intent)
        }
        val button3 = findViewById<ImageButton>(R.id.imageButton5)
        button3.setOnClickListener {
            val intent = Intent(this, MainScreen_page::class.java)
            startActivity(intent)
        }
    }
}