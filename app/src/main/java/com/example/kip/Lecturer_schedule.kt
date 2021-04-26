package com.example.kip

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import com.google.android.material.chip.Chip
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import khttp.get
import org.jetbrains.anko.doAsync
import java.util.concurrent.CountDownLatch

class Lecturer_schedule : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecturer_schedule)

        val button = findViewById<ImageButton>(R.id.back_button2)

        button.setOnClickListener{
            val intent = Intent(this, Schedule_swipe_2::class.java)
            startActivity(intent)
        }

        val gson = Gson()
        val profSchelueList = object : TypeToken<List<prof>>() {}.type

        //val jsonFileString = getJsonDataFromAsset(applicationContext, "profschedule.json")
        var profschedules:List<profschedule> = emptyList()

        val c = CountDownLatch(1)
        val task = doAsync(){
            println(profID)
            println(profScheduleByProfLink)
            val jsonFileString = get(profScheduleByProfLink)

            profschedules = gson.fromJson(jsonFileString.text, profSchelueList)


            c.countDown()
            //println(jsonFileString.jsonArray)
        }
        c.await()


        /*
        for (prof in profschedules){
            if(prof.profID == profID){
                profschedules += prof
            }
        }
*/
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

        var currentWeek: Int = 0

        findViewById<Switch>(R.id.switchWeek).setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                currentWeek = 0
                changeSchedule(profschedules,day_of_the_week,currentWeek,textviews,textviews_time)
            }else{
                currentWeek = 1
                changeSchedule(profschedules,day_of_the_week,currentWeek,textviews,textviews_time)
            }
        }

        findViewById<Chip>(R.id.chipMonday).setOnTouchListener { v, event ->
            if (v is Chip) {
                day_of_the_week = 0
                changeSchedule(profschedules,day_of_the_week,currentWeek,textviews,textviews_time)
            }
            false
        }

        findViewById<Chip>(R.id.chipThuesday).setOnTouchListener { v, event ->
            if (v is Chip) {
                day_of_the_week = 1
                changeSchedule(profschedules,day_of_the_week,currentWeek,textviews,textviews_time)
            }
            false
        }

        findViewById<Chip>(R.id.chipWednesday).setOnTouchListener { v, event ->
            if (v is Chip) {
                day_of_the_week = 2
                changeSchedule(profschedules,day_of_the_week,currentWeek,textviews,textviews_time)
            }
            false
        }

        findViewById<Chip>(R.id.chipThursday).setOnTouchListener { v, event ->
            if (v is Chip) {
                day_of_the_week = 3
                changeSchedule(profschedules,day_of_the_week,currentWeek,textviews,textviews_time)
            }
            false
        }

        findViewById<Chip>(R.id.chipFriday).setOnTouchListener { v, event ->
            if (v is Chip) {
                day_of_the_week = 4
                changeSchedule(profschedules,day_of_the_week,currentWeek,textviews,textviews_time)
            }
            false
        }

        findViewById<Chip>(R.id.chipSaturday).setOnTouchListener { v, event ->
            if (v is Chip) {
                day_of_the_week = 5
                changeSchedule(profschedules,day_of_the_week,currentWeek,textviews,textviews_time)
            }

            false
        }


        var i:Int=0


    }

    fun changeSchedule(schedules :List<profschedule>, day_of_the_week:Int, currentWeek:Int, textviews:Array<TextView>, textviews_time:Array<String>){

        var i:Int=0

        for (textview in textviews){
            textviews[i].text = textviews_time[i]
            i++
        }
        for (schedule in schedules){
            if(schedule.day == day_of_the_week){
                if(schedule.week == currentWeek){
                    textviews[schedule.number-1].text = "${textviews_time[schedule.number-1]} ${schedule.SubjectName}"
                    continue
                }
            }
        }

    }

}