package com.example.kip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import khttp.get
import org.jetbrains.anko.doAsync
import java.util.concurrent.CountDownLatch

class Lecture_schedule_selection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecture_schedule_selection)

        val gson = Gson()
        val profList = object : TypeToken<List<prof>>() {}.type

        var profs: List<prof> = emptyList()// = gson2.fromJson(jsonFileString2, cathedraList2)

        profs.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }

        val c = CountDownLatch(1)
        val task = doAsync(){

            val jsonFileString2 = get(profLink)


            profs = gson.fromJson(jsonFileString2.text, profList)



            c.countDown()
            //println(jsonFileString.jsonArray)
        }
        c.await()
        /*
        val jsonFileString = getJsonDataFromAsset(applicationContext, "cathedra.json")

        val jsonFileString2 = getJsonDataFromAsset(applicationContext, "prof.json")

        cathedras = gson2.fromJson(jsonFileString, cathedraList)


        profs = gson.fromJson(jsonFileString2, profList)
*/
        var chipsArray: Array<Chip> = emptyArray()

                for(prof in profs){
                    //for (cathedra in cathedras) {
                        val chip2 = Chip(this)
                        chip2.text = "${prof.profSurname} ${prof.profName} ${prof.profPatronymic}"

                        chip2.isClickable = true
                        chip2.isCheckable = false

                        chip2.setLayoutParams(FrameLayout.LayoutParams(ChipGroup.LayoutParams.MATCH_PARENT, ChipGroup.LayoutParams.WRAP_CONTENT))

                        chip2.textAlignment = View.TEXT_ALIGNMENT_CENTER

                        chip2.setOnClickListener{
                            profID=prof.profID
                            setContentView(R.layout.activity_lecturer_schedule)
                        }

                        findViewById<ChipGroup>(R.id.ChipProfGroup).addView(chip2)
                    }
                    //}



    }
}