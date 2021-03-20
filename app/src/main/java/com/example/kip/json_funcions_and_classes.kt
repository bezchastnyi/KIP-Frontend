package com.example.kip

import android.content.Context
import java.io.IOException

data class group(val facultyID: Int, val faculty: Int, val groupID: Int, val course: Int, val groupName: String) {
}

data class faculty(val facultyID: Int, val facultyShortName: String, val facultyName: String) {
}

data class schedule(val day: Int, val subjectName: String, val time: String , val week: Int) {
}

data class cathedra(val facultyID: Int,val cathedraID:Int, val cathedraShortName: String, val cathedraName: String) {
}

data class prof(val cathedra: Int,val cathedraID:Int, val profName: String, val profSurname: String,val profPatronymic: String) {
}

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}