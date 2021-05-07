package com.example.kip

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import com.google.android.material.chip.Chip


class Day_schedule_selection : AppCompatActivity() {


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_schedule_selection)
        val button = findViewById<ImageButton>(R.id.back_button_LS)

        button.setOnClickListener{
            val intent = Intent(this, Schedule_swipe::class.java)
            startActivity(intent)
        }

        findViewById<Chip>(R.id.chipMonday).setOnTouchListener { v, event ->
            if (v is Chip) {
                dayOfTheWeek = 0
                DayOfWeekName = "Понедельник"
                val intent = Intent(this, Day_schedule::class.java)
                startActivity(intent)
            }
            false
        }

        findViewById<Chip>(R.id.chipThuesday).setOnTouchListener { v, event ->
            if (v is Chip) {
                dayOfTheWeek = 1
                DayOfWeekName = "Вторник"
                val intent = Intent(this, Day_schedule::class.java)
                startActivity(intent)
            }
            false
        }

        findViewById<Chip>(R.id.chipWednesday).setOnTouchListener { v, event ->
            if (v is Chip) {
                dayOfTheWeek = 2
                DayOfWeekName = "Среда"
                val intent = Intent(this, Day_schedule::class.java)
                startActivity(intent)
            }
            false
        }

        findViewById<Chip>(R.id.chipThursday).setOnTouchListener { v, event ->
            if (v is Chip) {
                dayOfTheWeek = 3
                DayOfWeekName = "Четверг"
                val intent = Intent(this, Day_schedule::class.java)
                startActivity(intent)
            }
            false
        }

        findViewById<Chip>(R.id.chipFriday).setOnTouchListener { v, event ->
            if (v is Chip) {
                dayOfTheWeek = 4
                DayOfWeekName = "Пятница"
                val intent = Intent(this, Day_schedule::class.java)
                startActivity(intent)
            }
            false
        }

        findViewById<Chip>(R.id.chipSaturday).setOnTouchListener { v, event ->
            if (v is Chip) {
                dayOfTheWeek = 5
                DayOfWeekName = "Суббота"
                val intent = Intent(this, Day_schedule::class.java)
                startActivity(intent)
            }

            false
        }



        }

    fun popupMessage() {
        val alertDialogBuilder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Отсутствует интернет-соединение или сервера не отвечают.")
        alertDialogBuilder.setIcon(R.drawable.kip_logo)
        alertDialogBuilder.setTitle("Произошла ошибка")
        alertDialogBuilder.setNegativeButton("Ок", DialogInterface.OnClickListener { dialogInterface, i ->
            Log.d("internet", "Ok btn pressed")
            // add these two lines, if you wish to close the app:
            //finishAffinity()
            val intent = Intent(this, Schedule_swipe::class.java)
            startActivity(intent)
            //System.exit(0)
        })
        val alertDialog: android.app.AlertDialog? = alertDialogBuilder.create()
        alertDialog?.show()
    }
}