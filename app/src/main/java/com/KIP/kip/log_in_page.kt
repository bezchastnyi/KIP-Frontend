package com.KIP.kip

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.PrintWriter

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
        val letDirectory = File(this.filesDir, "LoginData.txt")
        //letDirectory.mkdirs()
        val created = letDirectory.createNewFile()
        println("created file $created")
        val file = File(letDirectory, "")
        file.forEachLine {
            println(it.toString())
            if(!mailGet){
                mail = it.toString()
                mailGet = true
            }
            else{
                pass = it.toString()
                mailGet = false
            }
        }

        println(file.path)
        // create a new file
        val isNewFileCreated :Boolean = file.createNewFile()
        println(isNewFileCreated)
        findViewById<EditText>(R.id.editTextTextEmailAddress).setText(mail)
        findViewById<EditText>(R.id.editTextTextPassword).setText(pass)

            selectedScheduleType = 0
            val button = findViewById<Button>(R.id.log_in_button)
            button.setOnClickListener {
                mail = findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()
                pass = findViewById<EditText>(R.id.editTextTextPassword).text.toString()

                val check = findViewById<CheckBox>(R.id.checkBox)
                if(check.isChecked) {
                    val writer = PrintWriter(file)
                    writer.print("")
                    writer.close()
                    file.appendText(mail)
                    file.appendText("\n")
                    file.appendText(pass)
                }

                val intent = Intent(this, MainScreen_page::class.java)
                students=false
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
        val alertDialogBuilder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(
            this
        )
        alertDialogBuilder.setMessage("Відсутнє інтернет-з'єднання.")
        alertDialogBuilder.setIcon(R.drawable.kip_logo)
        alertDialogBuilder.setTitle("Увага!")
        alertDialogBuilder.setNegativeButton(
            "Ок",
            DialogInterface.OnClickListener { dialogInterface, i ->
                Log.d("internet", "Ok btn pressed")
                // add these two lines, if you wish to close the app:
                //finishAffinity()
                val intent = Intent(this, Enter_page::class.java)
                startActivity(intent)
                //System.exit(0)
            })
        val alertDialog: android.app.AlertDialog? = alertDialogBuilder.create()
        alertDialog?.show()
    }
    var mailGet = false

}