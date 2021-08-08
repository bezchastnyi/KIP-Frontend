package com.KIP.kip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView

class Enter_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_page)

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

        image2.startAnimation(animation)
        image.startAnimation(animation3)

        val button = findViewById<Button>(R.id.enter_button)
        button.setOnClickListener{
            val intent = Intent(this, log_in_page::class.java)
            startActivity(intent)
        }
        val button2 = findViewById<Button>(R.id.enter_button_guest)
        button2.setOnClickListener{
            val intent = Intent(this, noAuth_page::class.java)
            startActivity(intent)
        }
    }
}