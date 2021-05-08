package com.example.kip

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import khttp.get
import org.jetbrains.anko.doAsync
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class Small_object_selection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_small_object_selection)

        val gson = Gson()
        val profList = object : TypeToken<List<prof>>() {}.type

        val auditoryList = object : TypeToken<List<auditory>>() {}.type


        val button = findViewById<ImageButton>(R.id.back_button_LS)

        button.setOnClickListener{
            val intent = Intent(this, Big_object_selection::class.java)
            startActivity(intent)
        }


        profs.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }

        val c = CountDownLatch(1)
        connectionDone=false

        val task = doAsync(){
            if(selectedScheduleType==1) {
                println(profByCathedraLink)
                val jsonFileString2 = get(profByCathedraLink)
                profs = gson.fromJson(jsonFileString2.text, profList)
            }
            else if(selectedScheduleType==2){
                println(audienceByBuilingLink)
                val jsonFileString2 = get(audienceByBuilingLink)
                auditorys = gson.fromJson(jsonFileString2.text, auditoryList)
            }

            connectionDone=true
            c.countDown()
            //println(jsonFileString.jsonArray)
        }
        c.await(5, TimeUnit.SECONDS)
        if(!connectionDone){
            popupMessage()
        }
        else {
            if(selectedScheduleType==1) {
                val comparator = profCompare()
                println(profs)
                profs = profs.sortedWith(comparator)
                println(profs)

                for (prof in profs) {
                    //for (cathedra in cathedras) {
                    val chip2 = Chip(this)
                    var scheduleIsPresent=false
                    for(day in prof.scheduleIsPresent){
                        if(day){
                            scheduleIsPresent=true
                            break
                        }
                    }

                    if (scheduleIsPresent) {
                        chip2.text = "${prof.profSurname} ${prof.profName} ${prof.profPatronymic}"
                    } else {
                        chip2.text =
                            "${prof.profSurname} ${prof.profName} ${prof.profPatronymic} (без расписания)"
                        chip2.isEnabled = false
                    }
                    chip2.isClickable = true
                    chip2.isCheckable = false

                    chip2.setLayoutParams(
                        FrameLayout.LayoutParams(
                            ChipGroup.LayoutParams.MATCH_PARENT,
                            ChipGroup.LayoutParams.WRAP_CONTENT
                        )
                    )

                    chip2.textAlignment = View.TEXT_ALIGNMENT_CENTER

                    chip2.setOnClickListener {
                        profID = prof.profID
                        profValid = scheduleIsPresent
                        val intent = Intent(this, Day_schedule_selection::class.java)
                        startActivity(intent)
                    }

                    findViewById<ChipGroup>(R.id.ChipProfGroup).addView(chip2)
                }

            }
            else if(selectedScheduleType==2){
                for (audiory in auditorys) {
                    //for (cathedra in cathedras) {
                    val chip2 = Chip(this)

                    var scheduleIsPresent=false
                    for(day in audiory.scheduleIsPresent){
                        if(day){
                            scheduleIsPresent=true
                            break
                        }
                    }

                    if(scheduleIsPresent){
                        chip2.text = "${audiory.audienceName}"
                    }
                    else{
                        chip2.text = "${audiory.audienceName} (без расписания)"
                        chip2.isEnabled=false
                    }
                    chip2.isClickable = true
                    chip2.isCheckable = false

                    chip2.setLayoutParams(FrameLayout.LayoutParams(ChipGroup.LayoutParams.MATCH_PARENT, ChipGroup.LayoutParams.WRAP_CONTENT))

                    chip2.textAlignment = View.TEXT_ALIGNMENT_CENTER

                    chip2.setOnClickListener {
                        audioryID = audiory.audienceID
                        buidlingValid = scheduleIsPresent
                        val intent = Intent(this, Day_schedule_selection::class.java)
                        startActivity(intent)
                    }

                    findViewById<ChipGroup>(R.id.ChipProfGroup).addView(chip2)
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
            val intent = Intent(this, Big_object_selection::class.java)
            startActivity(intent)
            //System.exit(0)
        })
        val alertDialog: android.app.AlertDialog? = alertDialogBuilder.create()
        alertDialog?.show()
    }
}