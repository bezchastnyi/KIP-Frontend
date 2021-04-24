package com.example.kip

const val tocken:Int=3012
const val baseLink:String = "https://server-get-b5ntqidyva-ew.a.run.app"

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
        profScheduleByProfLink = "$baseLink/StudentSchedule/profschedule/prof/$profID/?token=$tocken"
    }
var cathedraID:Int=0

var groupLink:String = "$baseLink/Group/?token=$tocken"
var facultyLink:String = "$baseLink/faculty/?token=$tocken"

var cathedraByFacultyLink:String = "$baseLink/Cathedra/faculty/$facultyID/?token=$tocken"
var profLink:String = "$baseLink/prof/?token=$tocken"

var studentScheduleByGroupLink:String = "$baseLink/StudentSchedule/group/$groupID/?token=$tocken"
var profScheduleByProfLink:String = "$baseLink/StudentSchedule/profschedule/prof/$profID/?token=$tocken"