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


class Class_schedule : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_schedule)
        val button = findViewById<ImageButton>(R.id.back_button_LS)

        button.setOnClickListener{
            val intent = Intent(this, Schedule_swipe::class.java)
            startActivity(intent)
        }

        val gson2 = Gson()
        val schedulesList = object : TypeToken<List<schedule>>() {}.type

        var schedules: List<schedule> = emptyList()// = gson2.fromJson(jsonFileString2, cathedraList2)

        schedules.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }

        connectionDone = false

        val c = CountDownLatch(1)
        val task = doAsync(){
            println(groupID)
            println(studentScheduleByGroupLink)
            val jsonFileString = get(studentScheduleByGroupLink)

            schedules = gson2.fromJson(jsonFileString.text, schedulesList)

            connectionDone = true
            c.countDown()
            //println(jsonFileString.jsonArray)
        }
        c.await(7, TimeUnit.SECONDS)

        if(!connectionDone){
            popupMessage()
        }
        else {
            /*
        val jsonFileString = getJsonDataFromAsset(applicationContext, "StudentSchedule.json")
        //jsonFileString?.let { Log.i("data", it) }

        val gson = Gson()
        val scheduleList = object : TypeToken<List<schedule>>() {}.type

        var schedulesTemp: List<schedule> = gson.fromJson(jsonFileString, scheduleList)

        var schedules:List<schedule> = emptyList()
*/

            //schedules.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }


            var textviews: Array<TextView> = emptyArray()
            var textviews_time: Array<String> = emptyArray()

            textviews += findViewById<TextView>(R.id.textViewD1)
            textviews += findViewById<TextView>(R.id.textViewD2)
            textviews += findViewById<TextView>(R.id.textViewD3)
            textviews += findViewById<TextView>(R.id.textViewD4)
            textviews += findViewById<TextView>(R.id.textViewD5)
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
                    changeSchedule(schedules, day_of_the_week, currentWeek, textviews, textviews_time)
                } else {
                    currentWeek = 1
                    changeSchedule(schedules, day_of_the_week, currentWeek, textviews, textviews_time)
                }
            }

            findViewById<Chip>(R.id.chipMonday).setOnTouchListener { v, event ->
                if (v is Chip) {
                    day_of_the_week = 0
                    changeSchedule(schedules, day_of_the_week, currentWeek, textviews, textviews_time)
                }
                false
            }

            findViewById<Chip>(R.id.chipThuesday).setOnTouchListener { v, event ->
                if (v is Chip) {
                    day_of_the_week = 1
                    changeSchedule(schedules, day_of_the_week, currentWeek, textviews, textviews_time)
                }
                false
            }

            findViewById<Chip>(R.id.chipWednesday).setOnTouchListener { v, event ->
                if (v is Chip) {
                    day_of_the_week = 2
                    changeSchedule(schedules, day_of_the_week, currentWeek, textviews, textviews_time)
                }
                false
            }

            findViewById<Chip>(R.id.chipThursday).setOnTouchListener { v, event ->
                if (v is Chip) {
                    day_of_the_week = 3
                    changeSchedule(schedules, day_of_the_week, currentWeek, textviews, textviews_time)
                }
                false
            }

            findViewById<Chip>(R.id.chipFriday).setOnTouchListener { v, event ->
                if (v is Chip) {
                    day_of_the_week = 4
                    changeSchedule(schedules, day_of_the_week, currentWeek, textviews, textviews_time)
                }
                false
            }

            findViewById<Chip>(R.id.chipSaturday).setOnTouchListener { v, event ->
                if (v is Chip) {
                    day_of_the_week = 5
                    changeSchedule(schedules, day_of_the_week, currentWeek, textviews, textviews_time)
                }

                false
            }


            var i: Int = 0


            button.setOnClickListener {
                val intent = Intent(this, Schedule_swipe::class.java)
                startActivity(intent)
            }

        }
    }
    fun changeSchedule(schedules :List<schedule>, day_of_the_week:Int, currentWeek:Int, textviews:Array<TextView>, textviews_time:Array<String>){

        var i:Int=0

        for (textview in textviews){
            textviews[i].text = textviews_time[i]
            i++
        }
        for (schedule in schedules){
            if(schedule.day == day_of_the_week){
                if(schedule.week == currentWeek){
                        textviews[schedule.number].text = "${textviews_time[schedule.number]} ${schedule.output}"
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
            val intent = Intent(this, Schedule_swipe::class.java)
            startActivity(intent)
            //System.exit(0)
        })
        val alertDialog: android.app.AlertDialog? = alertDialogBuilder.create()
        alertDialog?.show()
    }
}