package com.example.kip

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
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
    var buildings: List<building> = emptyList()

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
                val intent = Intent(this, Schedule_swipe::class.java)
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
                    val jsonFileString = get(cathedraByFacultyLink)
                    cathedras = gson2.fromJson(jsonFileString.text, cathedraList)

            }
            else if(selectedScheduleType==2){
                val jsonFileString = get(buildingsLink)
                buildings = gson2.fromJson(jsonFileString.text, buildingList)

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
            var chipsArray: Array<Chip> = emptyArray()
            if(selectedScheduleType==1) {

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

                for (building in buildings) {
                    val chip = Chip(this)
                    chip.text = building.buildingName
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
                        buildingsID = building.buildingID
                        val intent = Intent(this, Small_object_selection::class.java)
                        startActivity(intent)
                    }

                    findViewById<ChipGroup>(R.id.CathedraChipGroup).addView(chip)

                    chipsArray += chip
                }
            }

            var AnimationDay: Array<Animation> = emptyArray()

            var i:Long=0
            for(chip in chipsArray){
                val animation = AnimationUtils.loadAnimation(this,R.anim.kip_button_left)
                animation.duration=250
                animation.startOffset=100+i*50
                i+=1
                AnimationDay+=animation
            }
            var k=0
            for(chip in chipsArray){
                chip.startAnimation(AnimationDay[k])
                k+=1
            }
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
            val intent = Intent(this, Schedule_swipe::class.java)
            startActivity(intent)
            //System.exit(0)
        })
        val alertDialog: android.app.AlertDialog? = alertDialogBuilder.create()
        alertDialog?.show()
    }
}