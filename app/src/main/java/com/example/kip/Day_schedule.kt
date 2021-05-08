package com.example.kip

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import khttp.get
import org.jetbrains.anko.doAsync
import java.util.concurrent.CountDownLatch

class Day_schedule : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_schedule)

        val day = findViewById<TextView>(R.id.textViewDay)

        day.text = DayOfWeekName


        val gson2 = Gson()
        val schedulesList = object : TypeToken<List<studentScheduleDay>>() {}.type
        val profsList = object : TypeToken<List<profscheduleDay>>() {}.type
        val auditoryList = object : TypeToken<List<auditoryscheduleDay>>() {}.type

        schedulesStudent.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }

        connectionDone = false

        val c = CountDownLatch(1)
        val task = doAsync() {
            println("$groupID $dayOfTheWeek")
            if(selectedScheduleType==0){
                println(studentScheduleByGroupDayLink)
                val jsonFileString = get(studentScheduleByGroupDayLink)
                schedulesStudent = gson2.fromJson(jsonFileString.text, schedulesList)
            }
            else if(selectedScheduleType==1){
                println(profscheduleByProfDayLink)
                val jsonFileString = get(profscheduleByProfDayLink)
                schedulesProfs = gson2.fromJson(jsonFileString.text, profsList)
            }
            else if(selectedScheduleType==2){
                println(audienceScheduleByAudienceDayLink)
                val jsonFileString = get(audienceScheduleByAudienceDayLink)
                schedulesAuditoryDay = gson2.fromJson(jsonFileString.text, auditoryList)
            }

            connectionDone = true
            c.countDown()
            //println(jsonFileString.jsonArray)
        }
        c.await(5, java.util.concurrent.TimeUnit.SECONDS)

        if (!connectionDone) {
            popupMessage()
        }
        else {
            var textview: Array<TextView> = emptyArray()
            var ScheduleText: Array<Array<TextView>> = emptyArray()

            textview += findViewById<TextView>(R.id.textViewA1)
            textview += findViewById<TextView>(R.id.textViewB1)
            textview += findViewById<TextView>(R.id.textViewC1)
            textview += findViewById<TextView>(R.id.textViewType1)
            ScheduleText+=textview

            textview = emptyArray()

            textview += findViewById<TextView>(R.id.textViewA2)
            textview += findViewById<TextView>(R.id.textViewB2)
            textview += findViewById<TextView>(R.id.textViewC2)
            textview += findViewById<TextView>(R.id.textViewType2)
            ScheduleText+=textview

            textview = emptyArray()

            textview += findViewById<TextView>(R.id.textViewA3)
            textview += findViewById<TextView>(R.id.textViewB3)
            textview += findViewById<TextView>(R.id.textViewC3)
            textview += findViewById<TextView>(R.id.textViewType3)
            ScheduleText+=textview

            textview = emptyArray()

            textview += findViewById<TextView>(R.id.textViewA4)
            textview += findViewById<TextView>(R.id.textViewB4)
            textview += findViewById<TextView>(R.id.textViewC4)
            textview += findViewById<TextView>(R.id.textViewType4)
            ScheduleText+=textview

            textview = emptyArray()

            textview += findViewById<TextView>(R.id.textViewA5)
            textview += findViewById<TextView>(R.id.textViewB5)
            textview += findViewById<TextView>(R.id.textViewC5)
            textview += findViewById<TextView>(R.id.textViewType5)
            ScheduleText+=textview

            var currentWeek: Int = 0
            if(selectedScheduleType==0) {
                changeSchedule(schedulesStudent, currentWeek, ScheduleText)

                findViewById<Switch>(R.id.switchWeek).setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                        currentWeek = 0
                        changeSchedule(schedulesStudent, currentWeek, ScheduleText)
                    } else {
                        currentWeek = 1
                        changeSchedule(schedulesStudent, currentWeek, ScheduleText)
                    }
                }
                findViewById<Switch>(R.id.switchWeek).isChecked=true
            }

            else if(selectedScheduleType==1) {
                changeSchedule2(schedulesProfs, currentWeek, ScheduleText)

                findViewById<Switch>(R.id.switchWeek).setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                        currentWeek = 0
                        changeSchedule2(schedulesProfs, currentWeek, ScheduleText)
                    } else {
                        currentWeek = 1
                        changeSchedule2(schedulesProfs, currentWeek, ScheduleText)
                    }
                }
                findViewById<Switch>(R.id.switchWeek).isChecked=true
            }
            else if(selectedScheduleType==2) {
                changeSchedule3(schedulesAuditoryDay, currentWeek, ScheduleText)

                findViewById<Switch>(R.id.switchWeek).setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                        currentWeek = 0
                        changeSchedule3(schedulesAuditoryDay, currentWeek, ScheduleText)
                    } else {
                        currentWeek = 1
                        changeSchedule3(schedulesAuditoryDay, currentWeek, ScheduleText)
                    }
                }
                findViewById<Switch>(R.id.switchWeek).isChecked=true
            }


            var i: Int = 0

            val button = findViewById<ImageButton>(R.id.back_button_SE)
            button.setOnClickListener {
                val intent = Intent(this, Day_schedule_selection::class.java)
                startActivity(intent)
            }

        }
    }
    fun changeSchedule(schedules :List<studentScheduleDay>, currentWeek:Int, ScheduleText:Array<Array<TextView>>){
        for(schedule in ScheduleText){
            for(text in schedule){
                text.text=""
            }
        }
        for (schedule in schedules){
                if(schedule.week == currentWeek){
                    ScheduleText[schedule.number][0].text = "${schedule.subjectName}"
                    ScheduleText[schedule.number][1].text = "${schedule.audienceName}"
                    ScheduleText[schedule.number][2].text = "${schedule.profName}"
                    ScheduleText[schedule.number][3].text = "${schedule.type}"
                }

        }

    }

    fun changeSchedule2(schedules:List<profscheduleDay>, currentWeek:Int, ScheduleText:Array<Array<TextView>>){
        for(schedule in ScheduleText){
            for(text in schedule){
                text.text=""
            }
        }
        for (schedule in schedules){
            if(schedule.week == currentWeek){
                ScheduleText[schedule.number][0].text = "${schedule.subjectName}"
                ScheduleText[schedule.number][1].text = "${schedule.audienceName}"
                ScheduleText[schedule.number][2].text = "${schedule.groupNames}"
                ScheduleText[schedule.number][3].text = "${schedule.type}"
            }

        }

    }

    fun changeSchedule3(schedules:List<auditoryscheduleDay>, currentWeek:Int, ScheduleText:Array<Array<TextView>>){
        for(schedule in ScheduleText){
            for(text in schedule){
                text.text=""
            }
        }
        for (schedule in schedules){
            if(schedule.week == currentWeek){
                ScheduleText[schedule.number][0].text = "${schedule.subjectName}"
                ScheduleText[schedule.number][1].text = "${schedule.profName}"
                ScheduleText[schedule.number][2].text = "${schedule.groupNames}"
                ScheduleText[schedule.number][3].text = "${schedule.type}"
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
            val intent = Intent(this, Day_schedule_selection::class.java)
            startActivity(intent)
            //System.exit(0)
        })
        val alertDialog: android.app.AlertDialog? = alertDialogBuilder.create()
        alertDialog?.show()
    }
}