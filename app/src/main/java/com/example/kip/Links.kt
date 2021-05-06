package com.example.kip

import java.time.DayOfWeek

const val tocken:Int=3012
const val baseLink:String = "https://kip-server-get-b5ntqidyva-ew.a.run.app"

var facultyID:Int=0
    set(value) {
        field = value
        cathedraByFacultyLink="$baseLink/Cathedra/faculty/$facultyID/?token=$tocken"
    }

var groupID:Int=0
    set(value) {
        field = value
        studentScheduleByGroupLink="$baseLink/StudentSchedule/group/$groupID/?token=$tocken"
    }

var profID:Int=0
    set(value) {
        field = value
        profScheduleByProfLink = "$baseLink/profschedule/prof/$profID/?token=$tocken"
    }
var cathedraID:Int=0
    set(value) {
        field = value
        profByCathedraLink = "$baseLink/prof/cathedra/${cathedraID}/?token=$tocken"
    }


var DayOfWeekName = ""
var dayOfTheWeek:Int=0
    set(value) {
        field = value
        studentScheduleByGroupDayLink = "$baseLink/StudentSchedule/Group/$groupID/Day/$dayOfTheWeek/?token=$tocken"
    }


var groupLink:String = "$baseLink/Group/?token=$tocken"
var facultyLink:String = "$baseLink/faculty/?token=$tocken"

var cathedraByFacultyLink:String = "$baseLink/Cathedra/faculty/$facultyID/?token=$tocken"

var studentScheduleByGroupDayLink = "$baseLink/prof/cathedra/${cathedraID}/?token=$tocken"
var studentScheduleByGroupLink:String = "$baseLink/StudentSchedule/group/$groupID/?token=$tocken"
var profScheduleByProfLink:String = "$baseLink/profschedule/prof/$profID/?token=$tocken"
var profByCathedraLink:String = "$baseLink/prof/cathedra/${cathedraID}/?token=$tocken"