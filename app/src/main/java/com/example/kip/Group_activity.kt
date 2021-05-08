package com.example.kip

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import khttp.get
import org.jetbrains.anko.doAsync
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


var connectionDone = false

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
        var courseArray: Array<String> = emptyArray()
        val button3 = findViewById<Button>(R.id.button_select)
        button3.isEnabled=false
        courseArray += resources.getStringArray(R.array.Course)

        facultyArray+="Выберите факультет"

        val gson2 = Gson()
        val FacultyList = object : TypeToken<List<faculty>>() {}.type
        val GroupList = object : TypeToken<List<group>>() {}.type




        connectionDone = false

        val c = CountDownLatch(1)

        val task = doAsync() {
                println(groupLink)


                    val jsonFileString = get(groupLink)
                    val jsonFileString2 = get(facultyLink)


                    Facultys = gson2.fromJson(jsonFileString2.text, FacultyList)

                    for (faculty in Facultys) {
                        facultyArray += faculty.facultyShortName
                    }

                    val GroupList = object : TypeToken<List<group>>() {}.type
                    println(jsonFileString.content.toString())

                    Groups = gson.fromJson(jsonFileString.text, GroupList)



                    for (group in Groups) {
                        var scheduleIsPresent=false
                        for(day in group.scheduleIsPresent){
                            if(day){
                                scheduleIsPresent=true
                                break
                            }
                        }
                        if (scheduleIsPresent) {
                            groupArray += group.groupName
                        } else {
                            groupArray += "${group.groupName} (без расписания)"
                        }
                        groupIDArray += group.groupID
                    }

                connectionDone = true
                c.countDown()

                //println(jsonFileString.jsonArray)
            }



        c.await(5,TimeUnit.SECONDS)
        if(!connectionDone){
            popupMessage()
        }
        else {

            /*
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
        */

            val spinnerFaculty: Spinner = findViewById(R.id.spinnerFaculty)
            val spinnerCourse: Spinner = findViewById(R.id.spinnerCourse)
            val spinnerGroup: Spinner = findViewById(R.id.spinnerGroup)

            if(students) {


                val spinnerArrayAdapter2: ArrayAdapter<String> = ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, facultyArray
                )


                val courseArrayAdapter =
                    ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, courseArray)


                spinnerCourse.adapter = courseArrayAdapter
                spinnerFaculty.adapter = spinnerArrayAdapter2

                spinnerFaculty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: View,
                            position: Int,
                            id: Long
                        ) {

                            var groupArray2: Array<String> = emptyArray()

                            val text: String = spinnerFaculty.selectedItem.toString()

                            val course: String = spinnerCourse.selectedItem.toString()
                            var courseInt: Int = 0
                            if (course.substring(0, 1) != "В") {
                                button3.isEnabled = true
                                courseInt = course.substring(0, 1).toInt()

                            } else {
                                button3.isEnabled = false
                                button3.setBackgroundColor(Color.GRAY)
                            }
                            if (text.substring(0, 1) == "В") {
                                facultyID = 0
                                button3.isEnabled = false
                            }

                            println(text)
                            println(Facultys)

                            for (faculty in Facultys) {
                                if (faculty.facultyShortName == text) {
                                    facultyID = faculty.facultyID;
                                }
                            }

                            println(facultyID)
                            println(courseInt)
                            for (group in Groups) {
                                if (group.facultyID == facultyID && group.course == courseInt) {
                                    var scheduleIsPresent=false
                                    for(day in group.scheduleIsPresent){
                                        if(day){
                                            scheduleIsPresent=true
                                            break
                                        }
                                    }
                                    if (scheduleIsPresent) {
                                        groupArray2 += group.groupName
                                    } else {
                                        groupArray2 += "${group.groupName} (без расписания)"
                                    }
                                }
                            }

                            val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                                applicationContext,
                                android.R.layout.simple_spinner_item,
                                groupArray2
                            )

                            spinnerGroup.onItemSelectedListener =
                                object : AdapterView.OnItemSelectedListener {
                                    override fun onItemSelected(
                                        parent: AdapterView<*>,
                                        view: View,
                                        position: Int,
                                        id: Long
                                    ) {
                                        val text: String =
                                            spinnerGroup.selectedItem.toString().split(" ")[0]


                                        for (group in Groups) {
                                            if (group.groupName == text) {
                                                var scheduleIsPresent=false
                                                for(day in group.scheduleIsPresent){
                                                    if(day){
                                                        scheduleIsPresent=true
                                                        break
                                                    }
                                                }
                                                groupID = group.groupID
                                                groupValid = scheduleIsPresent
                                                button3.isEnabled = true
                                                button3.setBackgroundColor(Color.parseColor("#3700B3"))
                                            }
                                        }
                                        println("$groupID $text")
                                    }

                                    override fun onNothingSelected(parent: AdapterView<*>) {
                                        groupID = 0
                                        button3.isEnabled = false
                                        button3.setBackgroundColor(Color.GRAY)
                                    }
                                }

                            spinnerGroup.adapter = spinnerArrayAdapter
                        }

                        override fun onNothingSelected(parent: AdapterView<*>) {
                            facultyID = 0
                            button3.isEnabled = false
                            button3.setBackgroundColor(Color.GRAY)
                        }
                    }

                spinnerCourse.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        var groupArray2: Array<String> = emptyArray()
                        val text: String = spinnerFaculty.selectedItem.toString()


                        val course: String = spinnerCourse.selectedItem.toString()
                        var courseInt: Int = 0
                        if (course.substring(0, 1) != "В") {
                            courseInt = course.substring(0, 1).toInt()

                        } else {
                            button3.isEnabled = false
                            button3.setBackgroundColor(Color.GRAY)
                        }



                        println(text)
                        println(Facultys)

                        for (faculty in Facultys) {
                            if (faculty.facultyShortName == text) {
                                facultyID = faculty.facultyID;
                            }
                        }

                        println(facultyID)
                        println(courseInt)
                        for (group in Groups) {
                            if (group.facultyID == facultyID && group.course == courseInt) {
                                var scheduleIsPresent=false
                                for(day in group.scheduleIsPresent){
                                    if(day){
                                        scheduleIsPresent=true
                                        break
                                    }
                                }
                                if (scheduleIsPresent) {
                                    groupArray2 += group.groupName
                                } else {
                                    groupArray2 += "${group.groupName} (без расписания)"
                                }
                            }
                        }

                        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                            applicationContext, android.R.layout.simple_spinner_item, groupArray2
                        )

                        spinnerGroup.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parent: AdapterView<*>,
                                    view: View,
                                    position: Int,
                                    id: Long
                                ) {
                                    val text: String =
                                        spinnerGroup.selectedItem.toString().split(" ")[0]


                                    for (group in Groups) {
                                        if (group.groupName == text) {
                                            var scheduleIsPresent=false
                                            for(day in group.scheduleIsPresent){
                                                if(day){
                                                    scheduleIsPresent=true
                                                    break
                                                }
                                            }
                                            groupID = group.groupID
                                            groupValid = scheduleIsPresent
                                            button3.isEnabled = true
                                            button3.setBackgroundColor(Color.parseColor("#3700B3"))
                                        }
                                    }
                                    println("$groupID $text")

                                }

                                override fun onNothingSelected(parent: AdapterView<*>) {
                                    groupID = 0
                                    button3.isEnabled = false
                                    button3.setBackgroundColor(Color.GRAY)
                                }
                            }

                        spinnerGroup.adapter = spinnerArrayAdapter

                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        facultyID = 0
                        button3.isEnabled = false
                        button3.setBackgroundColor(Color.GRAY)
                    }
                }


                button.setOnClickListener {
                    val intent = Intent(this, Schedule_swipe::class.java)
                    startActivity(intent)
                }
            }
            else{
                val tv = findViewById<TextView>(R.id.textView3)
                tv.text = "Выберите ваш факультет"

                spinnerCourse.isEnabled = false
                spinnerGroup.isEnabled = false

                val layout = findViewById<LinearLayout>(R.id.LinearGroup)

                layout.removeView(spinnerCourse)
                layout.removeView(spinnerGroup)



                val spinnerArrayAdapter2: ArrayAdapter<String> = ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, facultyArray
                )
                spinnerFaculty.adapter = spinnerArrayAdapter2
                button3.isEnabled = false
                spinnerFaculty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        val text: String = spinnerFaculty.selectedItem.toString()

                        if (text.substring(0, 1) == "В") {
                            facultyID = 0
                            button3.isEnabled = false
                        }
                        else{
                            button3.setBackgroundColor(Color.parseColor("#3700B3"))
                            button3.isEnabled = true
                        }

                        println(text)
                        println(Facultys)

                        for (faculty in Facultys) {
                            if (faculty.facultyShortName == text) {
                                facultyID = faculty.facultyID;
                            }
                        }

                        println(facultyID)


                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        facultyID = 0
                        button3.isEnabled = false
                        button3.setBackgroundColor(Color.GRAY)
                    }
                }

                button.setOnClickListener {
                    val intent = Intent(this, Schedule_swipe::class.java)
                    startActivity(intent)
                }
            }
        }

    }

    private fun popupMessage() {
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
