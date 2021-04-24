package com.example.kip

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
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

        val gson2 = Gson()
        val FacultyList = object : TypeToken<List<faculty>>() {}.type
        val GroupList = object : TypeToken<List<group>>() {}.type


        var Facultys: List<faculty> = emptyList()// gson2.fromJson(jsonFileString2, FacultyList)

        var Groups: List<group> = emptyList()

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
                    groupArray += group.groupName
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
            val spinner2: Spinner = findViewById(R.id.spinnerFaculty)

            val spinnerF: Spinner = findViewById(R.id.spinnerFaculty)
// Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter.createFromResource(
                    this,
                    R.array.FacultySel,
                    android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner


                spinnerF.adapter = adapter
            }


            val spinnerArrayAdapter2: ArrayAdapter<String> = ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, facultyArray)


            val spinner3: Spinner = findViewById(R.id.spinnerCourse)

            spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val spinner: Spinner = findViewById<Spinner>(R.id.spinnerGroup)
                    val spinner3: Spinner = findViewById<Spinner>(R.id.spinnerCourse)

                    var groupArray2: Array<String> = emptyArray()

                    val text: String = spinner2.selectedItem.toString()

                    val course: String = spinner3.selectedItem.toString()
                    val courseInt = course.substring(0, 1).toInt()


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
                            groupArray2 += group.groupName
                        }
                    }

                    val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                            applicationContext, android.R.layout.simple_spinner_item, groupArray2)

                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                            val text: String = spinner.selectedItem.toString()


                            for (group in Groups) {
                                if (group.groupName == text) {
                                    groupID = group.groupID;
                                }
                            }
                            println(groupID)
                        }

                        override fun onNothingSelected(parent: AdapterView<*>) {
                            groupID = 0
                        }
                    }

                    spinner.adapter = spinnerArrayAdapter
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    facultyID = 0
                }
            }

            spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val spinner: Spinner = findViewById<Spinner>(R.id.spinnerGroup)
                    val spinner3: Spinner = findViewById<Spinner>(R.id.spinnerCourse)

                    var groupArray2: Array<String> = emptyArray()

                    val text: String = spinner2.selectedItem.toString()

                    val course: String = spinner3.selectedItem.toString()
                    val courseInt = course.substring(0, 1).toInt()


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
                            groupArray2 += group.groupName
                        }
                    }

                    val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                            applicationContext, android.R.layout.simple_spinner_item, groupArray2)

                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                            val text: String = spinner.selectedItem.toString()


                            for (group in Groups) {
                                if (group.groupName == text) {
                                    groupID = group.groupID;
                                }
                            }
                            println(groupID)
                        }

                        override fun onNothingSelected(parent: AdapterView<*>) {
                            groupID = 0
                        }
                    }

                    spinner.adapter = spinnerArrayAdapter
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    facultyID = 0
                }
            }

            spinner2.adapter = spinnerArrayAdapter2

            val spinner: Spinner = findViewById(R.id.spinnerGroup)


            val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                    applicationContext, android.R.layout.simple_spinner_item, groupArray)


            spinner.adapter = spinnerArrayAdapter



            button.setOnClickListener {
                val intent = Intent(this, Schedule_swipe::class.java)
                startActivity(intent)
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
