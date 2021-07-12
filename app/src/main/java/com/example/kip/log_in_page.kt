package com.example.kip

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import khttp.get
import org.jetbrains.anko.doAsync
import java.util.concurrent.CountDownLatch

class log_in_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in_page)
        /*
        val studList = object : TypeToken<List<student>>() {}.type
        val FacultyList = object : TypeToken<List<group>>() {}.type

        val gson = Gson()

        val c = CountDownLatch(1)
        val task = doAsync() {
            println(studentLogIn)
            val jsonFileString = get(studentLogIn)
            studentList = gson.fromJson(jsonFileString.text, studList)
            groupID = studentList[0].groupId
            facultyID = studentList[0].facultyId
            cathedraID = studentList[0].cathedraId



            val jsonFileStringG = get(groupLink)
            val jsonFileString2 = get(facultyLink)


            Facultys = gson.fromJson(jsonFileString2.text, FacultyList)


            val GroupList = object : TypeToken<List<group>>() {}.type
            println(jsonFileString.content.toString())

            Groups = gson.fromJson(jsonFileStringG.text, GroupList)


            connectionDone = true
            c.countDown()
            //println(jsonFileString.jsonArray)
        }
        c.await(10, java.util.concurrent.TimeUnit.SECONDS)

        if (!connectionDone) {
            popupMessage()
        }
        else {

         */
            selectedScheduleType = 0
            val button = findViewById<Button>(R.id.log_in_button)
            button.setOnClickListener {
                mail = findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()
                pass = findViewById<EditText>(R.id.editTextTextPassword).text.toString()
                val intent = Intent(this, MainScreen_page::class.java)
                startActivity(intent)
            }
            val button2 = findViewById<ImageButton>(R.id.back_button_log_in)
            button2.setOnClickListener {
                val intent = Intent(this, Enter_page::class.java)
                startActivity(intent)
            }
        //}
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
            val intent = Intent(this, Day_schedule_selection::class.java)
            startActivity(intent)
            //System.exit(0)
        })
        val alertDialog: android.app.AlertDialog? = alertDialogBuilder.create()
        alertDialog?.show()
    }
}