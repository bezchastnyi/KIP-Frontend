package com.example.kip

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class lecture_schedule : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecture_schedule)
        val button = findViewById<ImageButton>(R.id.back_button)

        button.setOnClickListener{
            val intent = Intent(this, Schedule_swipe_2::class.java)
            startActivity(intent)
        }

        val jsonFileString = getJsonDataFromAsset(applicationContext, "cathedra.json")
        //jsonFileString?.let { Log.i("data", it) }

        val gson = Gson()
        val cathedraList = object : TypeToken<List<cathedra>>() {}.type

        var cathedras: List<cathedra> = gson.fromJson(jsonFileString, cathedraList)

        cathedras.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }

        val jsonFileString2 = getJsonDataFromAsset(applicationContext, "prof.json")
        //jsonFileString?.let { Log.i("data", it) }

        val gson2 = Gson()
        val cathedraList2 = object : TypeToken<List<prof>>() {}.type

        var profs: List<prof> = gson2.fromJson(jsonFileString2, cathedraList2)

        profs.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }

        var chipsArray: Array<Chip> = emptyArray()

        for (cathedra in cathedras){
            // Initialize a new chip instance
            val chip = Chip(this)
            chip.text = cathedra.cathedraName

            // Set the chip icon
            //chip.chipIcon = ContextCompat.getDrawable(this,R.drawable.ic_action_android)
            //chip.setChipIconTintResource(R.color.abc_search_url_text)

            // Make the chip clickable
            chip.isClickable = true
            chip.isCheckable = false

            //chip.layoutParams.width = findViewById<LinearLayout>(R.id.Lecture_scedule_LL).width


            chip.setLayoutParams(FrameLayout.LayoutParams(ChipGroup.LayoutParams.MATCH_PARENT, ChipGroup.LayoutParams.WRAP_CONTENT))

            chip.textAlignment = View.TEXT_ALIGNMENT_CENTER

            // Show the chip icon in chip
            //chip.isCloseIconVisible = true

            // Set the chip click listener
            chip.setOnClickListener{
                findViewById<ChipGroup>(R.id.ChipProfGroup).removeAllViews()
                for(prof in profs){
                    //for (cathedra in cathedras) {
                        if (prof.cathedraID == cathedra.cathedraID) {
                            println("${prof.cathedraID} ${cathedra.cathedraID}")
                            val chip2 = Chip(this)
                            chip2.text = "${prof.profSurname} ${prof.profName}"

                            chip2.isClickable = true
                            chip2.isCheckable = false

                            chip2.setLayoutParams(FrameLayout.LayoutParams(ChipGroup.LayoutParams.MATCH_PARENT, ChipGroup.LayoutParams.WRAP_CONTENT))

                            chip2.textAlignment = View.TEXT_ALIGNMENT_CENTER
                            findViewById<ChipGroup>(R.id.ChipProfGroup).addView(chip2)
                        }
                    //}
                }
            }

            // Set chip close icon click listener
            //chip.setOnCloseIconClickListener{
                // Smoothly remove chip from chip group
           //     TransitionManager.beginDelayedTransition(chipGroup)
           //     chipGroup.removeView(chip)
           // }

            // Finally, add the chip to chip group
            findViewById<ChipGroup>(R.id.CathedraChipGroup).addView(chip)

            chipsArray+=chip
        }
        /*
        for (chip in chipsArray) {
            findViewById<Chip>(chip.id).setOnTouchListener { v, event ->
                if (v is Chip) {

                    val jsonFileString = getJsonDataFromAsset(applicationContext, "cathedra.json")
                    //jsonFileString?.let { Log.i("data", it) }

                    val gson = Gson()
                    val cathedraList = object : TypeToken<List<cathedra>>() {}.type

                    var cathedras: List<cathedra> = gson.fromJson(jsonFileString, cathedraList)

                    //cathedras.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }

                    val jsonFileString2 = getJsonDataFromAsset(applicationContext, "prof.json")
                    //jsonFileString?.let { Log.i("data", it) }

                    val gson2 = Gson()
                    val cathedraList2 = object : TypeToken<List<prof>>() {}.type

                    var profs: List<prof> = gson2.fromJson(jsonFileString2, cathedraList2)

                    profs.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }

                    for(prof in profs){
                        for (cathedra in cathedras) {
                            if (prof.cathedraID == cathedra.cathedraID) {
                                val chip2 = Chip(this)
                                chip2.text = "${prof.profSurname} ${prof.profName}"

                                chip2.isClickable = true
                                chip2.isCheckable = false

                                chip2.setLayoutParams(FrameLayout.LayoutParams(ChipGroup.LayoutParams.MATCH_PARENT, ChipGroup.LayoutParams.WRAP_CONTENT))

                                chip2.textAlignment = View.TEXT_ALIGNMENT_CENTER
                                findViewById<ChipGroup>(R.id.ChipProfGroup).addView(chip2)
                            }
                        }
                    }
                }
                false
            }

        }

         */
    }
}