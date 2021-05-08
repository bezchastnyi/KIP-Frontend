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
        if(selectedScheduleType==0) {
            button.setOnClickListener {
                val intent = Intent(this, Schedule_swipe::class.java)
                startActivity(intent)
            }
        }
        else{
            button.setOnClickListener {
                val intent = Intent(this, Small_object_selection::class.java)
                startActivity(intent)
            }
        }
        if (selectedScheduleType==0){
            for (student in Groups) {
                if (student.groupID == groupID) {
                    findViewById<Chip>(R.id.chipMonday).isEnabled = student.scheduleIsPresent[0]
                    findViewById<Chip>(R.id.chipThuesday).isEnabled = student.scheduleIsPresent[1]
                    findViewById<Chip>(R.id.chipWednesday).isEnabled = student.scheduleIsPresent[2]
                    findViewById<Chip>(R.id.chipThursday).isEnabled = student.scheduleIsPresent[3]
                    findViewById<Chip>(R.id.chipFriday).isEnabled = student.scheduleIsPresent[4]
                    findViewById<Chip>(R.id.chipSaturday).isEnabled = student.scheduleIsPresent[5]
                    break
                }
            }
        }
        else if(selectedScheduleType==1) {
            for (prof in profs) {
                if (prof.profID == profID) {
                    findViewById<Chip>(R.id.chipMonday).isEnabled = prof.scheduleIsPresent[0]
                    findViewById<Chip>(R.id.chipThuesday).isEnabled = prof.scheduleIsPresent[1]
                    findViewById<Chip>(R.id.chipWednesday).isEnabled = prof.scheduleIsPresent[2]
                    findViewById<Chip>(R.id.chipThursday).isEnabled = prof.scheduleIsPresent[3]
                    findViewById<Chip>(R.id.chipFriday).isEnabled = prof.scheduleIsPresent[4]
                    findViewById<Chip>(R.id.chipSaturday).isEnabled = prof.scheduleIsPresent[5]
                    break
                }
            }
        }
        else if(selectedScheduleType==2) {
            for (auditory in auditorys) {
                if (auditory.audienceID == audioryID) {
                    findViewById<Chip>(R.id.chipMonday).isEnabled = auditory.scheduleIsPresent[0]
                    findViewById<Chip>(R.id.chipThuesday).isEnabled = auditory.scheduleIsPresent[1]
                    findViewById<Chip>(R.id.chipWednesday).isEnabled = auditory.scheduleIsPresent[2]
                    findViewById<Chip>(R.id.chipThursday).isEnabled = auditory.scheduleIsPresent[3]
                    findViewById<Chip>(R.id.chipFriday).isEnabled = auditory.scheduleIsPresent[4]
                    findViewById<Chip>(R.id.chipSaturday).isEnabled = auditory.scheduleIsPresent[5]
                    break
                }
            }
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