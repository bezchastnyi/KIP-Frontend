package com.example.kip

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.redundent.kotlin.xml.xml
import java.io.IOException



class Group_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_activity)

        val button = findViewById<Button>(R.id.button_select)

        button.setOnClickListener {
            val intent = Intent(this, Schedule_swipe::class.java)
            startActivity(intent)
        }

        val jsonFileString = getJsonDataFromAsset(applicationContext, "group.json")
        //jsonFileString?.let { Log.i("data", it) }

        val gson = Gson()
        val GroupList = object : TypeToken<List<group>>() {}.type

        var Groups: List<group> = gson.fromJson(jsonFileString, GroupList)



        val jsonFileString2 = getJsonDataFromAsset(applicationContext, "faculty.json")
        jsonFileString2?.let { Log.i("data", it) }

        val gson2 = Gson()
        val FacultyList = object : TypeToken<List<faculty>>() {}.type

        var Facultys: List<faculty> = gson2.fromJson(jsonFileString2, FacultyList)

        //Facultys.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }

        var groupArray: Array<String> = emptyArray()
        var facultyArray: Array<String> = emptyArray()

        for (group in Groups) {
            groupArray += group.groupName
        }
        for (faculty in Facultys) {
            facultyArray +=faculty.facultyShortName
        }

        val spinner: Spinner = findViewById(R.id.spinner)

        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, groupArray)

        spinner.adapter = spinnerArrayAdapter

        val spinner2: Spinner = findViewById(R.id.spinner2)

        val spinnerArrayAdapter2: ArrayAdapter<String> = ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, facultyArray)

        spinner2.adapter = spinnerArrayAdapter2

        //println("Writed to file")
        /*
        println("YA TUT 1")
        doAsync{
            println("YA  2")
            //val r = get("https://api.github.com/events")
            val r = get("https://10.0.2.2:5001/group/?token=3012")
            println(r)
            println("YA  3")
            println(r.jsonArray)
        }

        */


        /*
        doAsync {
            val url = URL("https://10.0.2.2:5001/group/?token=3012")
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            try {
                val `in`: InputStream = BufferedInputStream(urlConnection.getInputStream())
                //readStream(`in`)
            } finally {
                urlConnection.disconnect()
            }
        }

         */
    }

}
