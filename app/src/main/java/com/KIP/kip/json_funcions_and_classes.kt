package com.KIP.kip

import android.content.Context
import java.io.IOException

var students:Boolean=false

var connectionDone = false

var selectedScheduleType:Int=0

var schedulesStudent: List<studentScheduleDay> = emptyList()
var schedulesProfs: List<profscheduleDay> = emptyList()
var schedulesAuditoryDay: List<auditoryscheduleDay> = emptyList()

var profs: List<prof> = emptyList()
var auditorys: List<auditory> = emptyList()

var Facultys: List<faculty> = emptyList()
var Groups: List<group> = emptyList()

var studentList: List<student> = emptyList()

var semesterMarkLists: List<semesterMarkList> = emptyList()
var semesterStudyingPlanLists: List<semesterStudyingPlan> = emptyList()
var debtLists: List<debt> = emptyList()
var currentRankLists: List<currentRank> = emptyList()
var personalinfoLists: List<debt> = emptyList()

data class group(val groupId: Int, val groupName: String, val course: Int,val scheduleIsPresent:List<Boolean>, val facultyId: Int) {
}

data class faculty(val facultyId: Int, val facultyName: String, val facultyShortName: String) {
}

data class building(val buildingId: Int, val buildingName: String, val buildingShortName: String,val numberOfAudiences: Int) {
}

data class auditory(val audienceID:Int,val audienceName:String, val buildingID: Int, val numberOfSeats: Int,val scheduleIsPresent:List<Boolean>, val building: String) {
}

data class schedule(val studentScheduleID: Int,val subjectName: String,val week: Int,val day: Int,val type: String,val number:Int,val output:String,val groupID: Int,val group: String,val buildingID: Int,val building: String,val audienceID: Int,val audience: String,val profID: Int,val prof: String) {
}

data class studentScheduleDay(val subjectName:String, val type:String,val number:Int,val week:Int,val audienceName:String,val profName:String){
}

data class cathedra(val cathedraId: Int,val cathedraName:String, val cathedraShortName: String, val facultyId: Int) {
}

data class prof(val profId: Int,val profSurname: String,val profName:String,val profPatronymic:String,val scheduleIsPresent:List<Boolean>, val cathedraId: Int) {
}

data class profschedule(val profScheduleID: Int,val subjectName: String,val week:Int,val day:Int,val type: String,val number:Int,val output:String,val profID:Int,val prof: String, val buildingID: Int,val building: String, val audienceID: Int,val audience: String,val groupID: List<Int>,val group: String) {
}

data class profscheduleDay(val subjectName:String, val type:String,val number:Int,val week:Int,val audienceName:String,val groupNames:String) {
}

data class auditoryscheduleDay(val subjectName:String, val type:String,val number:Int,val week:Int,val audienceName:String,val groupNames:String,val profName:String) {
}
data class student(val studentId: Int,val firstName: String,val lastName: String,val patronymic: String,val course: Int,val groupId: Int,val group: String,val facultyId:Int,val faculty:String,val cathedraId:Int,val cathedra:String,val specialization:String,val specialty:String,val studyingProgram:String,val studyingLevel:String,val studyingForm:String,val budgetForm:String) {
}

data class semesterMarkList(val subjectId: Int,val subject: String,val prof: String,val shortCathedra: String,val cathedra: String,val shortMark: Int,val nationalMark: String,val fullMark: Int,val ectsMark: String,val credits: Int,val control: String,val isDebt: Boolean,val date: String) {
}

data class semesterStudyingPlan(val subjectId: Int,val subject: String,val shortCathedra: String,val cathedra: String,val course: Int,val semester: Int,val credits: Int,val audits: String,val control: String) {
}

data class debt(val subjectId: Int,val subject: String,val prof: String,val shortCathedra: String,val cathedra: String,val credits: Int,val control: String,val date: String) {
}

data class currentRank(val rank: Int,val studentId: Int,val fio: String,val group: String,val fullRankMark: Int,val shortRankMark: Int,val rankFormula: String) {
}

data class personalinfo(val studentId: Int,val firstName: String,val lastName: String,val patronymic: String,val course: Int,val groupId: Int,val group: String,val facultyId: Int,val faculty: String,val cathedraId: Int,val cathedra: String,val specialization: String,val specialty: String,val studyingProgram: String,val studyingLevel: String,val studyingForm: String,val budgetForm: String) {
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