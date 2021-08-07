package com.example.kip

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.parseColor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import khttp.get
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class select_BuidlingFaculty_page : AppCompatActivity() {
    var cathedras: List<cathedra> = emptyList()
    var buildings: List<building> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select__buidling_faculty_page)
        val button = findViewById<ImageButton>(R.id.back_button_LS)

        val button8 = findViewById<ImageView>(R.id.proffButtonR)

        button8.setOnClickListener{
            selectedScheduleType=1
            val intent = Intent(this, select_BuidlingFaculty_page::class.java)
            startActivity(intent)
        }

        val gson = Gson()
        val cathedraList = object : TypeToken<List<cathedra>>() {}.type
        val buildingList = object : TypeToken<List<building>>() {}.type

        val button5 = findViewById<ImageView>(R.id.buidlingButtonR)

        button5.setOnClickListener{
            selectedScheduleType=2
            val intent = Intent(this, select_BuidlingFaculty_page::class.java)
            startActivity(intent)
        }
        val button6 = findViewById<ImageButton>(R.id.imageButton)

        button6.setOnClickListener{
            val intent = Intent(this, MainScreen_page::class.java)
            startActivity(intent)
        }

        val gson2 = Gson()
        val profList = object : TypeToken<List<prof>>() {}.type

        var profs: List<prof> = emptyList()// = gson2.fromJson(jsonFileString2, cathedraList2)

        profs.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }

        connectionDone = false

        val c = CountDownLatch(1)
        println(cathedraByFacultyLink)
        println(buildingsLink)
        val task = doAsync(){
            if(selectedScheduleType==1) {
                val jsonFileString = get(cathedraByFacultyLink)
                cathedras = gson2.fromJson(jsonFileString.text, cathedraList)

            }
            else if(selectedScheduleType==2){
                val jsonFileString = get(buildingsLink)
                buildings = gson2.fromJson(jsonFileString.text, buildingList)

            }
            this.activityUiThread {
                connectionDone()
            }
            //println(jsonFileString.jsonArray)
        }
        checkSatus()
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

    fun connectionDone() {
        connectionDone= true
        var chipsArray: Array<Chip> = emptyArray()
        if(selectedScheduleType==1) {

            findViewById<ImageView>(R.id.proffButtonR).setBackgroundColor(parseColor("#3F51B5"))
            findViewById<ImageView>(R.id.proffButtonR).setImageResource(R.drawable.toolbar_2_inv)

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
                    cathedraID = cathedra.cathedraId
                    val intent = Intent(this, select_AuditoryProff_page::class.java)
                    startActivity(intent)
                }

                findViewById<ChipGroup>(R.id.ChipBFgroup).addView(chip)

                chipsArray += chip
            }
        }
        else if(selectedScheduleType ==2){
            findViewById<ImageView>(R.id.buidlingButtonR).setBackgroundColor(parseColor("#3F51B5"))
            findViewById<ImageView>(R.id.buidlingButtonR).setImageResource(R.drawable.toolbar_1_inv)

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
                    buildingsID = building.buildingId
                    val intent = Intent(this, select_AuditoryProff_page::class.java)
                    startActivity(intent)
                }

                findViewById<ChipGroup>(R.id.ChipBFgroup).addView(chip)

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

    fun checkSatus(){
        var task= doAsync() {
            val c = CountDownLatch(1)
            c.await(8, java.util.concurrent.TimeUnit.SECONDS)
            if (!connectionDone) {
                this.activityUiThread {
                    popupMessage()
                }
            }
        }
    }
}