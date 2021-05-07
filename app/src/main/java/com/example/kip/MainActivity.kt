package com.example.kip

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kip_start)

        val animation = AnimationUtils.loadAnimation(this,R.anim.nav_default_enter_anim)
        animation.duration = 4000
        val image = findViewById<ImageView>(R.id.imageView2)
        image.startAnimation(animation)

        val button = findViewById<ImageButton>(R.id.Profs)
        button.startAnimation(animation)
        button.setOnClickListener{
            students = false
            val intent = Intent(this, Group_activity::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<ImageButton>(R.id.Student)
        button2.startAnimation(animation)
        button2.setOnClickListener{
            students = true
            val intent = Intent(this, Group_activity::class.java)
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
