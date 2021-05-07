package com.example.kip

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import khttp.get
import org.jetbrains.anko.doAsync
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


class Big_object_selection : AppCompatActivity() {
    var cathedras: List<cathedra> = emptyList()
    var buildings: List<cathedra> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_big_object_selection)
        val button = findViewById<ImageButton>(R.id.back_button_LS)
        if(students) {
            button.setOnClickListener {
                val intent = Intent(this, Schedule_swipe::class.java)
                startActivity(intent)
            }
        }
        else{
            button.setOnClickListener {
                val intent = Intent(this, Group_activity::class.java)
                startActivity(intent)
            }
        }

        val gson = Gson()
        val cathedraList = object : TypeToken<List<cathedra>>() {}.type
        val buildingList = object : TypeToken<List<building>>() {}.type


        cathedras.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }


        val gson2 = Gson()
        val profList = object : TypeToken<List<prof>>() {}.type

        var profs: List<prof> = emptyList()// = gson2.fromJson(jsonFileString2, cathedraList2)

        profs.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }

        connectionDone = false

        val c = CountDownLatch(1)
        println(cathedraByFacultyLink)
        println(profByCathedraLink)
        val task = doAsync(){
            if(selectedScheduleType==1) {
                if (cathedras.isEmpty()) {
                    val jsonFileString = get(cathedraByFacultyLink)
                    cathedras = gson2.fromJson(jsonFileString.text, cathedraList)
                } else {
                    if (cathedras.isNotEmpty()) {
                        if (cathedras[0].cathedraID != cathedraID) {
                            val jsonFileString = get(cathedraByFacultyLink)
                            cathedras = gson2.fromJson(jsonFileString.text, cathedraList)
                        }
                    }
                }
            }
            else if(selectedScheduleType==2){
                if (buildings.isEmpty()) {
                    val jsonFileString = get(cathedraByFacultyLink)
                    buildings = gson2.fromJson(jsonFileString.text, cathedraList)
                } else {
                    if (buildings.isNotEmpty()) {
                        if (buildings[0].cathedraID != buildingsID) {
                            val jsonFileString = get(cathedraByFacultyLink)
                            buildings = gson2.fromJson(jsonFileString.text, buildingList)
                        }
                    }
                }
                }

            connectionDone = true
            c.countDown()
            //println(jsonFileString.jsonArray)
        }

        c.await(5, TimeUnit.SECONDS)

        if(!connectionDone){
            popupMessage()
        }
        else {
            if(selectedScheduleType==1) {
                var chipsArray: Array<Chip> = emptyArray()

                for (cathedra in cathedras) {
                    val chip = Chip(this)
                    chip.text = cathedra.cathedraName
                    chip.isClickable = true
                    chip.isCheckable = false

                    chip.setLayoutParams(
                        FrameLayout.LayoutParams(
                            ChipGroup.LayoutParams.MATCH_PARENT,
                            ChipGroup.LayoutParams.WRAP_CONTENT
                        )
                    )

                    chip.textAlignment = View.TEXT_ALIGNMENT_CENTER
                    chip.setOnClickListener {
                        cathedraID = cathedra.cathedraID
                        val intent = Intent(this, Small_object_selection::class.java)
                        startActivity(intent)
                    }

                    findViewById<ChipGroup>(R.id.CathedraChipGroup).addView(chip)

                    chipsArray += chip
                }
            }
            else if(selectedScheduleType ==2){
                var chipsArray: Array<Chip> = emptyArray()

                for (building in buildings) {
                    val chip = Chip(this)
                    chip.text = building.cathedraName
                    chip.isClickable = true
                    chip.isCheckable = false

                    chip.setLayoutParams(
                        FrameLayout.LayoutParams(
                            ChipGroup.LayoutParams.MATCH_PARENT,
                            ChipGroup.LayoutParams.WRAP_CONTENT
                        )
                    )

                    chip.textAlignment = View.TEXT_ALIGNMENT_CENTER
                    chip.setOnClickListener {
                        cathedraID = building.cathedraID
                        val intent = Intent(this, Day_schedule::class.java)
                        startActivity(intent)
                    }

                    findViewById<ChipGroup>(R.id.CathedraChipGroup).addView(chip)

                    chipsArray += chip
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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            //System.exit(0)
        })
        val alertDialog: android.app.AlertDialog? = alertDialogBuilder.create()
        alertDialog?.show()
    }
}