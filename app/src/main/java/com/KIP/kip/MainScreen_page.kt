package com.KIP.kip

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import khttp.get
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import java.sql.DriverManager.println
import java.util.concurrent.CountDownLatch

class MainScreen_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen_page)

        val studList = object : TypeToken<List<student>>() {}.type
        val FacultyList = object : TypeToken<List<group>>() {}.type

        val gson = Gson()
        connectionDone=false
        println(studentLogIn)
        val task = doAsync() {
            println(studentLogIn)
            val jsonFileString = get(studentLogIn)
            studentList = gson.fromJson(jsonFileString.text, studList)
            groupID = studentList[0].groupId
            facultyID = studentList[0].facultyId
            cathedraID = studentList[0].cathedraId

            this.activityUiThread {
                findViewById<TextView>(R.id.Name).text =
                    "${studentList[0].firstName} ${studentList[0].lastName}"
            }
            val jsonFileStringG = get(groupLink)
            val jsonFileString2 = get(facultyLink)


            Facultys = gson.fromJson(jsonFileString2.text, FacultyList)


            val GroupList = object : TypeToken<List<group>>() {}.type
            println(jsonFileString.content.toString())

            Groups = gson.fromJson(jsonFileStringG.text, GroupList)



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
            val intent = Intent(this, log_in_page::class.java)
            startActivity(intent)
            //System.exit(0)
        })
        val alertDialog: android.app.AlertDialog? = alertDialogBuilder.create()
        alertDialog?.show()
    }

    fun connectionDone() {
        connectionDone = true
        findViewById<Button>(R.id.button1).isClickable = true

        findViewById<ImageButton>(R.id.buidlingButton).isClickable = true

        findViewById<ImageButton>(R.id.proffButton).isClickable = true

        findViewById<Button>(R.id.button2).isClickable = true

        findViewById<Button>(R.id.button4).isClickable = true

        findViewById<Button>(R.id.button3).isClickable = true
        val button = findViewById<Button>(R.id.button1)
        button.setOnClickListener {
            students = false
            selectedScheduleType = 0
            val intent = Intent(this, select_day_schedule_page::class.java)
            startActivity(intent)
        }

        val button7 = findViewById<ImageButton>(R.id.imageButton7)
        button7.setOnClickListener {
            val intent = Intent(this, person_info_page::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<ImageButton>(R.id.buidlingButton)
        button2.setOnClickListener {
            selectedScheduleType = 1
            val intent = Intent(this, select_BuidlingFaculty_page::class.java)
            startActivity(intent)
        }

        val button3 = findViewById<ImageButton>(R.id.proffButton)
        button3.setOnClickListener {
            selectedScheduleType = 2
            val intent = Intent(this, select_BuidlingFaculty_page::class.java)
            startActivity(intent)
        }

        val button6 = findViewById<Button>(R.id.button2)
        button6.setOnClickListener {
            selectedScheduleType = 3
            val intent = Intent(this, record_book_page::class.java)
            startActivity(intent)
        }
        val button5 = findViewById<Button>(R.id.button)
        button5.setOnClickListener {
            selectedScheduleType = 5
            val intent = Intent(this, record_book_page::class.java)
            startActivity(intent)
        }

        val button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener {
            selectedScheduleType = 6
            val intent = Intent(this, record_book_page::class.java)
            startActivity(intent)
        }
        val button8 = findViewById<Button>(R.id.button3)
        button8.setOnClickListener {
            selectedScheduleType = 7
            val intent = Intent(this, record_book_page::class.java)
            startActivity(intent)
        }

    }

    fun checkSatus(){
        var task= doAsync() {
            val c = CountDownLatch(1)
            c.await(12, java.util.concurrent.TimeUnit.SECONDS)
            if (!connectionDone) {
                this.activityUiThread {
                    popupMessage()
                }
            }
        }
    }
}