package com.example.kip

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import com.google.android.material.chip.Chip
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import khttp.get
import org.jetbrains.anko.doAsync
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class Lecturer_schedule : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecturer_schedule)

        val button = findViewById<ImageButton>(R.id.back_button2)

        button.setOnClickListener{
            val intent = Intent(this, Small_object_selection::class.java)
            startActivity(intent)
        }

        val gson = Gson()
        val profSchelueList = object : TypeToken<List<profschedule>>() {}.type

        //val jsonFileString = getJsonDataFromAsset(applicationContext, "profschedule.json")
        var profschedules:List<profschedule> = emptyList()

        val c = CountDownLatch(1)
        connectionDone=false
        val task = doAsync(){
            println(profID)
            println(profScheduleByProfLink)
            val jsonFileString = get(profScheduleByProfLink)

            profschedules = gson.fromJson(jsonFileString.text, profSchelueList)

            connectionDone=true
            c.countDown()
            //println(jsonFileString.jsonArray)
        }
        c.await(7, TimeUnit.SECONDS)
        if(!connectionDone){
            popupMessage()
        }
        else {


            findViewById<Chip>(R.id.chipMonday2).setOnTouchListener { v, event ->
                if (v is Chip) {
                    dayOfTheWeek = 0
                    DayOfWeekName = "Понедельник"
                    val intent = Intent(this, lecturer_schedule_extended::class.java)
                    startActivity(intent)
                }
                false
            }

            findViewById<Chip>(R.id.chipThuesday2).setOnTouchListener { v, event ->
                if (v is Chip) {
                    dayOfTheWeek = 1
                    DayOfWeekName = "Вторник"
                    val intent = Intent(this, lecturer_schedule_extended::class.java)
                    startActivity(intent)
                }
                false
            }

            findViewById<Chip>(R.id.chipWednesday2).setOnTouchListener { v, event ->
                if (v is Chip) {
                    dayOfTheWeek = 2
                    DayOfWeekName = "Среда"
                    val intent = Intent(this, lecturer_schedule_extended::class.java)
                    startActivity(intent)
                }
                false
            }

            findViewById<Chip>(R.id.chipThursday2).setOnTouchListener { v, event ->
                if (v is Chip) {
                    dayOfTheWeek = 3
                    DayOfWeekName = "Четверг"
                    val intent = Intent(this, lecturer_schedule_extended::class.java)
                    startActivity(intent)
                }
                false
            }

            findViewById<Chip>(R.id.chipFriday2).setOnTouchListener { v, event ->
                if (v is Chip) {
                    dayOfTheWeek = 4
                    DayOfWeekName = "Пятница"
                    val intent = Intent(this, lecturer_schedule_extended::class.java)
                    startActivity(intent)
                }
                false
            }

            findViewById<Chip>(R.id.chipSaturday2).setOnTouchListener { v, event ->
                if (v is Chip) {
                    dayOfTheWeek = 5
                    DayOfWeekName = "Суббота"
                    val intent = Intent(this, lecturer_schedule_extended::class.java)
                    startActivity(intent)
                }

                false
            }


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