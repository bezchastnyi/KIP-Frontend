package com.example.kip

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


class Class_schedule : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_schedule)
        val button = findViewById<ImageButton>(R.id.back_button)

        button.setOnClickListener{
            val intent = Intent(this, Schedule_swipe::class.java)
            startActivity(intent)
        }

        val gson2 = Gson()
        val schedulesList = object : TypeToken<List<schedule>>() {}.type

        //var schedules: List<schedule> = emptyList()// = gson2.fromJson(jsonFileString2, cathedraList2)

        //schedules.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }
        /*
        val c = CountDownLatch(1)
        val task = doAsync(){

            //val jsonFileString = get("https://kip-server-get-e3gw2ud6pa-ew.a.run.app/StudentSchedule/group/$groupID/?token=PLEASE_SPECIFY_VIA_ENV")

            schedules = gson2.fromJson(jsonFileString.text, schedulesList)


            c.countDown()
            //println(jsonFileString.jsonArray)
        }
        c.await()
        */


        val jsonFileString = getJsonDataFromAsset(applicationContext, "StudentSchedule.json")
        //jsonFileString?.let { Log.i("data", it) }

        val gson = Gson()
        val scheduleList = object : TypeToken<List<schedule>>() {}.type

        var schedulesTemp: List<schedule> = gson.fromJson(jsonFileString, scheduleList)

        var schedules:List<schedule> = emptyList()

        for (schedule in schedulesTemp){
            if(schedule.groupID == groupID){
                schedules += schedule
            }
        }

        //schedules.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }


        var textviews: Array<TextView> = emptyArray()
        var textviews_time: Array<String> = emptyArray()

        textviews += findViewById<TextView>(R.id.textViewD1)
        textviews += findViewById<TextView>(R.id.textViewD2)
        textviews += findViewById<TextView>(R.id.textViewD3)
        textviews += findViewById<TextView>(R.id.textViewD4)
        textviews += findViewById<TextView>(R.id.textViewD5)
        //chips += findViewById<TextView>(R.id.textViewD6)

        textviews_time+= "8:30 - "
        textviews_time+= "10:10 - "
        textviews_time+= "12:35 - "
        textviews_time+= "14:30 - "
        textviews_time+= "16:10 - "

        var day_of_the_week: Int = 0

        var currentWeek: Boolean = false

        findViewById<Switch>(R.id.switchWeek).setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                currentWeek = false
                changeSchedule(schedules,day_of_the_week,currentWeek,textviews,textviews_time)
            }else{
                currentWeek = true
                changeSchedule(schedules,day_of_the_week,currentWeek,textviews,textviews_time)
            }
        }

        findViewById<Chip>(R.id.chipMonday).setOnTouchListener { v, event ->
            if (v is Chip) {
                day_of_the_week = 0
                changeSchedule(schedules,day_of_the_week,currentWeek,textviews,textviews_time)
            }
            false
        }

        findViewById<Chip>(R.id.chipThuesday).setOnTouchListener { v, event ->
            if (v is Chip) {
                day_of_the_week = 1
                changeSchedule(schedules,day_of_the_week,currentWeek,textviews,textviews_time)
            }
            false
        }

        findViewById<Chip>(R.id.chipWednesday).setOnTouchListener { v, event ->
            if (v is Chip) {
                day_of_the_week = 2
                changeSchedule(schedules,day_of_the_week,currentWeek,textviews,textviews_time)
            }
            false
        }

        findViewById<Chip>(R.id.chipThursday).setOnTouchListener { v, event ->
            if (v is Chip) {
                day_of_the_week = 3
                changeSchedule(schedules,day_of_the_week,currentWeek,textviews,textviews_time)
            }
            false
        }

        findViewById<Chip>(R.id.chipFriday).setOnTouchListener { v, event ->
            if (v is Chip) {
                day_of_the_week = 4
                changeSchedule(schedules,day_of_the_week,currentWeek,textviews,textviews_time)
            }
            false
        }

        findViewById<Chip>(R.id.chipSaturday).setOnTouchListener { v, event ->
            if (v is Chip) {
                day_of_the_week = 5
                changeSchedule(schedules,day_of_the_week,currentWeek,textviews,textviews_time)
            }

            false
        }


        var i:Int=0


        button.setOnClickListener{
            val intent = Intent(this, Schedule_swipe::class.java)
            startActivity(intent)
        }


    }
    fun changeSchedule(schedules :List<schedule>, day_of_the_week:Int, currentWeek:Boolean, textviews:Array<TextView>, textviews_time:Array<String>){

        var i:Int=0

        for (textview in textviews){
            textviews[i].text = textviews_time[i]
            i++
        }
        i=0
        for (schedule in schedules){
            if(schedule.day == day_of_the_week){
                if(schedule.week == currentWeek){
                    textviews[i].text = textviews_time[i] +  schedule.subject
                        i++
                        continue
                }
            }
        }

    }
}