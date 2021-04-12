package com.example.kip

import android.content.Context
import java.io.IOException

data class group(val facultyID: Int, val faculty: Int, val groupID: Int, val course: Int, val groupName: String) {
}

data class faculty(val facultyID: Int, val facultyShortName: String, val facultyName: String) {
}

data class schedule(val groupID: Int,val group: String,val subjectID:Int,val subject:String,val buildingID:Int,val building:String,val audience: String,val audienceID:Int,val studentScheduleID:Int,val week:Boolean,val day:Int) {
}

data class cathedra(val facultyID: Int,val cathedraID:Int, val cathedraShortName: String, val cathedraName: String) {
}

data class prof(val cathedra: Int,val cathedraID:Int,val profID:Int, val profName: String, val profSurname: String,val profPatronymic: String) {
}

data class profschedule(val ProfScheduleID: Int,val SubjectName: String,val week:Int,val day:Int,val Type: String,val profID:Int, val BuildingID: Int, val AudienceID: Int,val GroupID: Int) {
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