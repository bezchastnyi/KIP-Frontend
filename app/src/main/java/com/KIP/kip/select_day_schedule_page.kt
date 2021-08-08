package com.KIP.kip

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.google.android.material.chip.Chip

class select_day_schedule_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_day_schedule_page)

        if(students){
            val layout = findViewById<LinearLayout>(R.id.dawnLay)
            for (i in 0 until layout.childCount) {
                val child: View = layout.getChildAt(i)
                child.isVisible = false
            }
        }

        val button = findViewById<ImageButton>(R.id.imageButton)

        val animation = AnimationUtils.loadAnimation(this,R.anim.kip_button_left)
        var ChipDay: Array<Chip> = emptyArray()
        var AnimationDay: Array<Animation> = emptyArray()

        ChipDay += findViewById<Chip>(R.id.chipMonday)
        ChipDay += findViewById<Chip>(R.id.chipThuesday)
        ChipDay += findViewById<Chip>(R.id.chipWednesday)
        ChipDay += findViewById<Chip>(R.id.chipThursday)
        ChipDay += findViewById<Chip>(R.id.chipFriday)
        ChipDay += findViewById<Chip>(R.id.chipSaturday)


        var i:Long=0
        for(chip in ChipDay){
            val animation = AnimationUtils.loadAnimation(this,R.anim.kip_button_left)
            animation.duration=300
            animation.startOffset=100+i*100
            i+=1
            AnimationDay+=animation
        }
        var k=0
        for(chip in ChipDay){
            chip.startAnimation(AnimationDay[k])
            k+=1
        }


        if(selectedScheduleType==0) {
            button.setOnClickListener {
                val intent = Intent(this, MainScreen_page::class.java)
                startActivity(intent)
            }
        }
        else{
            button.setOnClickListener {
                val intent = Intent(this, MainScreen_page::class.java)
                startActivity(intent)
            }
        }
        if (selectedScheduleType==0){
            for (student in Groups) {
                if (student.groupId == groupID) {
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
                if (prof.profId == profID) {
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
                DayOfWeekName = "Понеділок"
                val intent = Intent(this, schedule_by_day_page::class.java)
                startActivity(intent)
            }
            false
        }

        findViewById<Chip>(R.id.chipThuesday).setOnTouchListener { v, event ->
            if (v is Chip) {
                dayOfTheWeek = 1
                DayOfWeekName = "Вівторок"
                val intent = Intent(this, schedule_by_day_page::class.java)
                startActivity(intent)
            }
            false
        }

        findViewById<Chip>(R.id.chipWednesday).setOnTouchListener { v, event ->
            if (v is Chip) {
                dayOfTheWeek = 2
                DayOfWeekName = "Середа"
                val intent = Intent(this, schedule_by_day_page::class.java)
                startActivity(intent)
            }
            false
        }

        findViewById<Chip>(R.id.chipThursday).setOnTouchListener { v, event ->
            if (v is Chip) {
                dayOfTheWeek = 3
                DayOfWeekName = "Четвер"
                val intent = Intent(this, schedule_by_day_page::class.java)
                startActivity(intent)
            }
            false
        }

        findViewById<Chip>(R.id.chipFriday).setOnTouchListener { v, event ->
            if (v is Chip) {
                dayOfTheWeek = 4
                DayOfWeekName = "П'ятниця"
                val intent = Intent(this, schedule_by_day_page::class.java)
                startActivity(intent)
            }
            false
        }

        findViewById<Chip>(R.id.chipSaturday).setOnTouchListener { v, event ->
            if (v is Chip) {
                dayOfTheWeek = 5
                DayOfWeekName = "Субота"
                val intent = Intent(this, schedule_by_day_page::class.java)
                startActivity(intent)
            }

            false
        }



    }

    fun popupMessage() {
        val alertDialogBuilder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Відсутнє інтернет-з'єднання.")
        alertDialogBuilder.setIcon(R.drawable.kip_logo)
        alertDialogBuilder.setTitle("Увага!")
        alertDialogBuilder.setNegativeButton("Ок", DialogInterface.OnClickListener { dialogInterface, i ->
            Log.d("internet", "Ok btn pressed")
            // add these two lines, if you wish to close the app:
            //finishAffinity()
            val intent = Intent(this, MainScreen_page::class.java)
            startActivity(intent)
            //System.exit(0)
        })
        val alertDialog: android.app.AlertDialog? = alertDialogBuilder.create()
        alertDialog?.show()
    }
}