package com.example.kip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class self_info_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_self_info_page)

        findViewById<TextView>(R.id.info1).text = "${studentList[0].course}"
        findViewById<TextView>(R.id.info2).text = "${studentList[0].group}"
        findViewById<TextView>(R.id.info3).text = "${studentList[0].faculty}"
        findViewById<TextView>(R.id.info4).text = "${studentList[0].cathedra}"
        findViewById<TextView>(R.id.info5).text = "${studentList[0].specialty}"
        findViewById<TextView>(R.id.info6).text = "${studentList[0].specialization}"
        findViewById<TextView>(R.id.info7).text = "${studentList[0].studyingProgram}"
        findViewById<TextView>(R.id.info8).text = "${studentList[0].studyingLevel}"
        findViewById<TextView>(R.id.info9).text = "${studentList[0].studyingForm}"
        findViewById<TextView>(R.id.info10).text = "${studentList[0].budgetForm}"

        val button = findViewById<ImageButton>(R.id.imageButton6)
        button.setOnClickListener {
            val intent = Intent(this, person_info_page::class.java)
            startActivity(intent)
        }
    }
}