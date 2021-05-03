package com.example.kip

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
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
            val intent = Intent(this, Lecture_schedule_selection::class.java)
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


            /*
        for (prof in profschedules){
            if(prof.profID == profID){
                profschedules += prof
            }
        }
*/
            var textviews: Array<TextView> = emptyArray()
            var textviews_time: Array<String> = emptyArray()

            textviews += findViewById<TextView>(R.id.textViewD12)
            textviews += findViewById<TextView>(R.id.textViewD22)
            textviews += findViewById<TextView>(R.id.textViewD32)
            textviews += findViewById<TextView>(R.id.textViewD42)
            textviews += findViewById<TextView>(R.id.textViewD52)
            //chips += findViewById<TextView>(R.id.textViewD6)

            textviews_time += "8:30 - "
            textviews_time += "10:25 - "
            textviews_time += "12:35 - "
            textviews_time += "14:30 - "
            textviews_time += "16:25 - "

            var day_of_the_week: Int = 0

            var currentWeek: Int = 0

            findViewById<Switch>(R.id.switchWeek).setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    currentWeek = 0
                    changeSchedule(profschedules, day_of_the_week, currentWeek, textviews, textviews_time)
                } else {
                    currentWeek = 1
                    changeSchedule(profschedules, day_of_the_week, currentWeek, textviews, textviews_time)
                }
            }

            findViewById<Chip>(R.id.chipMonday2).setOnTouchListener { v, event ->
                if (v is Chip) {
                    day_of_the_week = 0
                    changeSchedule(profschedules, day_of_the_week, currentWeek, textviews, textviews_time)
                }
                false
            }

            findViewById<Chip>(R.id.chipThuesday2).setOnTouchListener { v, event ->
                if (v is Chip) {
                    day_of_the_week = 1
                    changeSchedule(profschedules, day_of_the_week, currentWeek, textviews, textviews_time)
                }
                false
            }

            findViewById<Chip>(R.id.chipWednesday2).setOnTouchListener { v, event ->
                if (v is Chip) {
                    day_of_the_week = 2
                    changeSchedule(profschedules, day_of_the_week, currentWeek, textviews, textviews_time)
                }
                false
            }

            findViewById<Chip>(R.id.chipThursday2).setOnTouchListener { v, event ->
                if (v is Chip) {
                    day_of_the_week = 3
                    changeSchedule(profschedules, day_of_the_week, currentWeek, textviews, textviews_time)
                }
                false
            }

            findViewById<Chip>(R.id.chipFriday2).setOnTouchListener { v, event ->
                if (v is Chip) {
                    day_of_the_week = 4
                    changeSchedule(profschedules, day_of_the_week, currentWeek, textviews, textviews_time)
                }
                false
            }

            findViewById<Chip>(R.id.chipSaturday2).setOnTouchListener { v, event ->
                if (v is Chip) {
                    day_of_the_week = 5
                    changeSchedule(profschedules, day_of_the_week, currentWeek, textviews, textviews_time)
                }

                false
            }


            var i: Int = 0

        }
    }

    fun changeSchedule(schedules2 :List<profschedule>, day_of_the_week:Int, currentWeek:Int, textviews:Array<TextView>, textviews_time:Array<String>){

        var i:Int=0
        println(schedules2)
        for (textview in textviews){
            textviews[i].text = textviews_time[i]
            i++
        }
        for (schedule2 in schedules2){
            if(schedule2.day == day_of_the_week){
                if(schedule2.week == currentWeek){
                    textviews[schedule2.number].text = "${textviews_time[schedule2.number]} ${schedule2.output}"
                    continue
                }
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
            val intent = Intent(this, Schedule_swipe_2::class.java)
            startActivity(intent)
            //System.exit(0)
        })
        val alertDialog: android.app.AlertDialog? = alertDialogBuilder.create()
        alertDialog?.show()
    }

}