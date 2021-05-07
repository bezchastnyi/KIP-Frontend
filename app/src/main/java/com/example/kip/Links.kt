package com.example.kip

const val token:Int=3012
const val baseLink:String = "https://kip-server-get-b5ntqidyva-nw.a.run.app"

var facultyID:Int=0
    set(value) {
        field = value
        cathedraByFacultyLink="$baseLink/Cathedra/faculty/$facultyID/?token=$token"
    }

var groupID:Int=0
    set(value) {
        field = value
        studentScheduleByGroupLink="$baseLink/StudentSchedule/group/$groupID/?token=$token"
        studentScheduleByGroupDayLink = "$baseLink/StudentSchedule/Group/$groupID/Day/$dayOfTheWeek/?token=$token"
    }

var profID:Int=0
    set(value) {
        field = value
        profScheduleByProfLink = "$baseLink/profschedule/prof/$profID/?token=$token"
        profscheduleByProfDayLink = "$baseLink/ProfSchedule/Prof/$profID/Day/$dayOfTheWeek?token=$token"
    }
var cathedraID:Int=0
    set(value) {
        field = value
        profByCathedraLink = "$baseLink/prof/cathedra/${cathedraID}/?token=$token"
    }

var buildingsID:Int=0
var audioryID:Int=0

var DayOfWeekName = ""
var dayOfTheWeek:Int=0
    set(value) {
        field = value
        studentScheduleByGroupDayLink = "$baseLink/StudentSchedule/Group/$groupID/Day/$dayOfTheWeek/?token=$token"
        profscheduleByProfDayLink = "$baseLink/ProfSchedule/Prof/$profID/Day/$dayOfTheWeek?token=$token"
    }


var groupLink:String = "$baseLink/Group/?token=$token"
var facultyLink:String = "$baseLink/faculty/?token=$token"

var cathedraByFacultyLink:String = "$baseLink/Cathedra/faculty/$facultyID/?token=$token"

var studentScheduleByGroupDayLink = "$baseLink/prof/cathedra/${cathedraID}/?token=$token"
var studentScheduleByGroupLink:String = "$baseLink/StudentSchedule/group/$groupID/?token=$token"
var profScheduleByProfLink:String = "$baseLink/profschedule/prof/$profID/?token=$token"
var profscheduleByProfDayLink = "$baseLink/ProfSchedule/Prof/$profID/Day/$dayOfTheWeek?token=$token"
var profByCathedraLink:String = "$baseLink/prof/cathedra/${cathedraID}/?token=$token"