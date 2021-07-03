package com.example.kip

const val token:Int=3012
const val baseLink:String = "https://kip-server-get-b5ntqidyva-nw.a.run.app/v1"
const val mail:String = "Oleksii.Bezchastnyi@cs.khpi.edu.ua"
const val pass:String = "mQT2vL"

var facultyID:Int=0
    set(value) {
        field = value
        cathedraByFacultyLink="$baseLink/Cathedra/faculty/$facultyID"
    }

var groupID:Int=0
    set(value) {
        field = value
        studentScheduleByGroupLink="$baseLink/StudentSchedule/group/$groupID"
        studentScheduleByGroupDayLink = "$baseLink/StudentSchedule/Group/$groupID/Day/$dayOfTheWeek/"
    }

var profID:Int=0
    set(value) {
        field = value
        profScheduleByProfLink = "$baseLink/profschedule/prof/$profID"
        profscheduleByProfDayLink = "$baseLink/ProfSchedule/Prof/$profID/Day/$dayOfTheWeek"
    }
var cathedraID:Int=0
    set(value) {
        field = value
        profByCathedraLink = "$baseLink/prof/cathedra/${cathedraID}"
    }

var buildingsID:Int=0
    set(value){
        field = value
        audienceByBuilingLink = "$baseLink/Audience/building/$buildingsID"
    }


var audioryID:Int=0
    set(value) {
        field = value
        audienceScheduleByAudienceDayLink = "$baseLink/AudienceSchedule/Audience/${audioryID}/Day/$dayOfTheWeek"
    }


var DayOfWeekName = ""
var dayOfTheWeek:Int=0
    set(value) {
        field = value
        studentScheduleByGroupDayLink = "$baseLink/StudentSchedule/Group/$groupID/Day/$dayOfTheWeek/"
        profscheduleByProfDayLink = "$baseLink/ProfSchedule/Prof/$profID/Day/$dayOfTheWeek"
        audienceScheduleByAudienceDayLink = "$baseLink/AudienceSchedule/Audience/${audioryID}/Day/$dayOfTheWeek"
    }

var groupLink:String = "$baseLink/Group"
var facultyLink:String = "$baseLink/faculty"

var cathedraByFacultyLink:String = "$baseLink/Cathedra/faculty/$facultyID"

var studentScheduleByGroupDayLink = "$baseLink/prof/cathedra/${cathedraID}"
var buildingsLink = "$baseLink/building"
var audienceByBuilingLink = "$baseLink/Audience/building/$buildingsID"
var audienceScheduleByAudienceDayLink = "$baseLink/AudienceSchedule/Audience//${cathedraID}/Day/$dayOfTheWeek"
var studentScheduleByGroupLink:String = "$baseLink/StudentSchedule/group/$groupID"
var profScheduleByProfLink:String = "$baseLink/profschedule/prof/$profID"
var profscheduleByProfDayLink = "$baseLink/ProfSchedule/Prof/$profID/Day/$dayOfTheWeek"
var profByCathedraLink:String = "$baseLink/prof/cathedra/${cathedraID}"
var studentLogIn:String = "https://kip-server-auth-b5ntqidyva-nw.a.run.app/v1/PersonalInformation/${mail}/${pass}"