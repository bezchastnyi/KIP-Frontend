package com.example.kip

import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
//import khttp.get
import org.jetbrains.anko.doAsync
import java.io.IOException
import java.util.concurrent.CountDownLatch


var groupID:Int=0

class Group_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_activity)

        val button = findViewById<Button>(R.id.button_select)


        val gson = Gson()
        //var Groups: List<group> = emptyList()

        var groupArray: Array<String> = emptyArray()
        var groupIDArray: Array<Int> = emptyArray()
        var facultyArray: Array<String> = emptyArray()

        val gson2 = Gson()
        val FacultyList = object : TypeToken<List<faculty>>() {}.type
        val GroupList = object : TypeToken<List<group>>() {}.type


        //var Facultys: List<faculty> = gson2.fromJson(jsonFileString2, FacultyList)

        var Groups: List<group> = emptyList()

        /*
        val c = CountDownLatch(1)
        val task = doAsync(){

                val jsonFileString = get("https://31.202.49.107:5001/Group/?token=3012")

                val jsonFileString2 = get("https://31.202.49.107:5001/faculty/?token=3012")


                var Facultys: List<faculty> = gson2.fromJson(jsonFileString2.text, FacultyList)

                 for (faculty in Facultys) {
                    facultyArray +=faculty.facultyShortName
                 }

                val GroupList = object : TypeToken<List<group>>() {}.type
                println(jsonFileString.content.toString())

                Groups = gson.fromJson(jsonFileString.text, GroupList)



                for (group in Groups) {
                    groupArray += group.groupName
                    groupIDArray +=group.groupID
                }
            c.countDown()
            //println(jsonFileString.jsonArray)
        }
        c.await()
        */

        val jsonFileString = getJsonDataFromAsset(applicationContext, "group.json")
        val jsonFileString2 = getJsonDataFromAsset(applicationContext, "faculty.json")


        var Facultys: List<faculty> = gson2.fromJson(jsonFileString2, FacultyList)

        for (faculty in Facultys) {
            facultyArray +=faculty.facultyShortName
        }

        Groups = gson.fromJson(jsonFileString, GroupList)

        for (group in Groups) {
            groupArray += group.groupName
            groupIDArray +=group.groupID
        }

        val spinner2: Spinner = findViewById(R.id.spinner2)

        val spinnerArrayAdapter2: ArrayAdapter<String> = ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, facultyArray)

        spinner2.adapter = spinnerArrayAdapter2



        val spinner: Spinner = findViewById(R.id.spinner)

        spinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View, position: Int, id: Long) {
                val spinner: Spinner = findViewById<Spinner>(R.id.spinner)

                var groupArray2: Array<String> = emptyArray()

                val text: String = spinner2.selectedItem.toString()
                println(text)

                for (group in Groups) {
                    for(faculty in Facultys)
                        if(group.facultyID == faculty.facultyID && faculty.facultyShortName == text) {
                            groupID = group.groupID
                             groupArray2 += group.groupName
                     }
                }

                val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                        applicationContext, android.R.layout.simple_spinner_item, groupArray2)

                spinner.adapter = spinnerArrayAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                groupID=0
            }
        }

        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                applicationContext, android.R.layout.simple_spinner_item, groupArray)

        spinner.adapter = spinnerArrayAdapter



        button.setOnClickListener {
            val intent = Intent(this, Schedule_swipe::class.java)
            startActivity(intent)
        }

    }

}
