package com.example.kip

import android.content.Context
import java.io.IOException

var students:Boolean=false

var selectedScheduleType:Int=0

var schedulesStudent: List<studentScheduleDay> = emptyList()
var schedulesProfs: List<profscheduleDay> = emptyList()
var schedulesAuditoryDay: List<auditoryscheduleDay> = emptyList()

var profs: List<prof> = emptyList()
var auditorys: List<auditory> = emptyList()

var Facultys: List<faculty> = emptyList()
var Groups: List<group> = emptyList()

var studentList: List<student> = emptyList()


data class group(val facultyID: Int, val faculty: String, val groupID: Int, val course: Int,val scheduleIsPresent:List<Boolean>, val groupName: String) {
}

data class faculty(val facultyID: Int, val facultyShortName: String, val facultyName: String) {
}

data class building(val buildingID: Int, val buildingName: String, val buildingShortName: String) {
}

data class auditory(val audienceID:Int,val audienceName:String, val buildingID: Int, val numberOfSeats: Int,val scheduleIsPresent:List<Boolean>, val building: String) {
}

data class schedule(val studentScheduleID: Int,val subjectName: String,val week: Int,val day: Int,val type: String,val number:Int,val output:String,val groupID: Int,val group: String,val buildingID: Int,val building: String,val audienceID: Int,val audience: String,val profID: Int,val prof: String) {
}

data class studentScheduleDay(val subjectName:String, val type:String,val number:Int,val week:Int,val audienceName:String,val profName:String){
}

data class cathedra(val facultyID: Int,val cathedraID:Int, val cathedraShortName: String, val cathedraName: String) {
}

data class prof(val cathedra: Int,val cathedraID:Int,val profID:Int, val profName: String, val profSurname: String,val profPatronymic: String,val scheduleIsPresent:List<Boolean>) {
}

data class profschedule(val profScheduleID: Int,val subjectName: String,val week:Int,val day:Int,val type: String,val number:Int,val output:String,val profID:Int,val prof: String, val buildingID: Int,val building: String, val audienceID: Int,val audience: String,val groupID: List<Int>,val group: String) {
}

data class profscheduleDay(val subjectName:String, val type:String,val number:Int,val week:Int,val audienceName:String,val groupNames:String) {
}

data class auditoryscheduleDay(val subjectName:String, val type:String,val number:Int,val week:Int,val audienceName:String,val groupNames:String,val profName:String) {
}
data class student(val studentId: Int,val firstName: String,val lastName: String,val patronymic: String,val course: Int,val groupId: Int,val group: String,val facultyId:Int,val faculty:String,val cathedraId:Int,val cathedra:String,val specialization:String,val specialty:String,val studyingProgram:String,val studyingLevel:String,val studyingForm:String,val budgetForm:String) {
}

class profCompare: Comparator<prof>{
    override fun compare(o1: prof, o2: prof): Int {
        var i:Int = 0
        val surname= o1.profSurname.toLowerCase()
        val surnameAnother= o2.profSurname.toLowerCase()
        for(letter in surname){
            if(letter.toInt()<surnameAnother[i].toInt()){
                return -1
            }
            else if (letter.toInt()>surnameAnother[i].toInt()){
                return 1
            }
            i++
            if(i>=surnameAnother.length){
                return -1
            }
        }
        return 0
    }
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

var groupValid = false
var profValid = true
var buidlingValid = true