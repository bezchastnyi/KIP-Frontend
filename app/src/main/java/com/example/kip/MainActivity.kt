package com.example.kip

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kip_start)


        val animation = AnimationUtils.loadAnimation(this,R.anim.kip_logo)
        val animation3 = AnimationUtils.loadAnimation(this,R.anim.kip_logo2)
        val animation2 = AnimationUtils.loadAnimation(this,R.anim.kip_button)

        val displayMetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(displayMetrics)
        var width =  displayMetrics.widthPixels

        println("Width $width")

        animation2.startOffset=1000
        animation.startOffset = 550
        animation.duration = 1250
        animation.setFillAfter(true)
        animation3.setFillAfter(true)



        val image = findViewById<ImageView>(R.id.imageView2)
        val image2 = findViewById<ImageView>(R.id.imageView4)

        //ObjectAnimator.ofFloat(image, "translationX", -(width/4).toFloat()).apply {
        //    startDelay=200
        //    duration = 1500
        //    start()
       // }


        image2.startAnimation(animation)
        image.startAnimation(animation3)

        val button = findViewById<ImageButton>(R.id.Profs)
        button.startAnimation(animation2)
        button.setOnClickListener{
            students = false
            val intent = Intent(this, Group_activity::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<ImageButton>(R.id.Student)
        button2.startAnimation(animation2)
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
