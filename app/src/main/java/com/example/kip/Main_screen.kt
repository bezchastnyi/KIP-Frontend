package com.example.kip

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

class Main_screen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val button = findViewById<Button>(R.id.Button_plan_edit)

        button.setOnClickListener {
            val intent = Intent(this, Plan_edit::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<Button>(R.id.button_schedule)

        button2.setOnClickListener {
            val intent = Intent(this, Schedule_swipe::class.java)
            startActivity(intent)
        }
        //setSupportActionBar(findViewById(R.id.toolbar))

        //findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
       //     Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
       //             .setAction("Action", null).show()
      //  }


    }
}