package com.example.kip

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import khttp.get
import org.jetbrains.anko.doAsync
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class lecturer_schedule_extended : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecturer_schedule_extended)

        val day = findViewById<TextView>(R.id.textViewDay1)

        day.text = DayOfWeekName

        val gson = Gson()
        val profSchelueList = object : TypeToken<List<profscheduleDay>>() {}.type

        //val jsonFileString = getJsonDataFromAsset(applicationContext, "profschedule.json")
        var profschedules:List<profscheduleDay> = emptyList()

        val c = CountDownLatch(1)
        connectionDone=false
        val task = doAsync(){
            println("$profID $dayOfTheWeek")
            println(profscheduleByProfDayLink)
            val jsonFileString = get(profscheduleByProfDayLink)

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
            var textview: Array<TextView> = emptyArray()
            var ScheduleText: Array<Array<TextView>> = emptyArray()

            textview += findViewById<TextView>(R.id.textViewA11)
            textview += findViewById<TextView>(R.id.textViewB11)
            textview += findViewById<TextView>(R.id.textViewC11)
            ScheduleText+=textview

            textview = emptyArray()

            textview += findViewById<TextView>(R.id.textViewA21)
            textview += findViewById<TextView>(R.id.textViewB21)
            textview += findViewById<TextView>(R.id.textViewC21)
            ScheduleText+=textview

            textview = emptyArray()

            textview += findViewById<TextView>(R.id.textViewA31)
            textview += findViewById<TextView>(R.id.textViewB31)
            textview += findViewById<TextView>(R.id.textViewC31)
            ScheduleText+=textview

            textview = emptyArray()

            textview += findViewById<TextView>(R.id.textViewA41)
            textview += findViewById<TextView>(R.id.textViewB41)
            textview += findViewById<TextView>(R.id.textViewC41)
            ScheduleText+=textview

            textview = emptyArray()

            textview += findViewById<TextView>(R.id.textViewA51)
            textview += findViewById<TextView>(R.id.textViewB51)
            textview += findViewById<TextView>(R.id.textViewC51)
            ScheduleText+=textview

            var currentWeek: Int = 0
            //changeSchedule(profschedules, currentWeek, ScheduleText)

            findViewById<Switch>(R.id.switchWeek).setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    currentWeek = 0
                    //changeSchedule(profschedules, currentWeek, ScheduleText)
                } else {
                    currentWeek = 1
                    //changeSchedule(profschedules, currentWeek, ScheduleText)
                }
            }



            var i: Int = 0

            val button = findViewById<ImageButton>(R.id.back_button_SE)
            button.setOnClickListener {
                val intent = Intent(this, Big_object_selection::class.java)
                startActivity(intent)
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
            val intent = Intent(this, Big_object_selection::class.java)
            startActivity(intent)
            //System.exit(0)
        })
        val alertDialog: android.app.AlertDialog? = alertDialogBuilder.create()
        alertDialog?.show()
    }
}