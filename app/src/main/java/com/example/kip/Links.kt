package com.example.kip

const val token:Int=3012
const val baseLink:String = "https://kip-server-no-auth-v1-0-b5ntqidyva-lm.a.run.app"
const val baseLinkAuth:String = "https://kip-server-auth-v1-0-b5ntqidyva-lm.a.run.app"


var mail:String = "Oleksii.Bezchastnyi@cs.khpi.edu.ua"
    set(value) {
        field = value
        semesterMarksListLink = "$baseLinkAuth/v1/SemesterMarksList/${mail}/${pass}/${semester}"
        semesterStudyingPlanLink = "$baseLinkAuth/v1/SemesterStudyingPlan/${mail}/${pass}/${semester}"
        personalInfoLink = "$baseLinkAuth/v1/PersonalInformation/${mail}/${pass}/"
        studentLogIn = "$baseLinkAuth/v1/PersonalInformation/${mail}/${pass}"
        currentRankLink = "$baseLinkAuth/v1/CurrentRank/${mail}/${pass}"
    }
var pass:String = "mQT2vL"
    set(value) {
        field = value
        semesterMarksListLink = "$baseLinkAuth/v1/SemesterMarksList/${mail}/${pass}/${semester}"
        semesterStudyingPlanLink = "$baseLinkAuth/v1/SemesterStudyingPlan/${mail}/${pass}/${semester}"
        personalInfoLink = "$baseLinkAuth/v1/PersonalInformation/${mail}/${pass}/"
        studentLogIn = "$baseLinkAuth/v1/PersonalInformation/${mail}/${pass}"
        currentRankLink = "$baseLinkAuth/v1/CurrentRank/${mail}/${pass}"
    }

var facultyID:Int=21
    set(value) {
        field = value
        cathedraByFacultyLink="$baseLink/v1/Cathedra/faculty/$facultyID"
    }

var groupID:Int=0
    set(value) {
        field = value
        studentScheduleByGroupLink="$baseLink/v1/StudentSchedule/group/$groupID"
        studentScheduleByGroupDayLink = "$baseLink/v1/StudentSchedule/Group/$groupID/Day/$dayOfTheWeek/"
        groupLink = "$baseLink/v1/Group/faculty/${facultyID}"
    }

var profID:Int=0
    set(value) {
        field = value
        profScheduleByProfLink = "$baseLink/v1/profschedule/prof/$profID"
        profscheduleByProfDayLink = "$baseLink/v1/ProfSchedule/Prof/$profID/Day/$dayOfTheWeek"
    }
var cathedraID:Int=0
    set(value) {
        field = value
        profByCathedraLink = "$baseLink/v1/prof/cathedra/${cathedraID}"
    }

var buildingsID:Int=0
    set(value){
        field = value
        audienceByBuilingLink = "$baseLink/v1/Audience/building/$buildingsID"
    }



var audioryID:Int=0
    set(value) {
        field = value
        audienceScheduleByAudienceDayLink = "$baseLink/v1/AudienceSchedule/Audience/${audioryID}/Day/$dayOfTheWeek"
    }


var DayOfWeekName = ""
var dayOfTheWeek:Int=0
    set(value) {
        field = value
        studentScheduleByGroupDayLink = "$baseLink/v1/StudentSchedule/Group/$groupID/Day/$dayOfTheWeek/"
        profscheduleByProfDayLink = "$baseLink/v1/ProfSchedule/Prof/$profID/Day/$dayOfTheWeek"
        audienceScheduleByAudienceDayLink = "$baseLink/v1/AudienceSchedule/Audience/${audioryID}/Day/$dayOfTheWeek"
    }
var semester:Int=5
    set(value) {
        field = value
        semesterMarksListLink = "$baseLinkAuth/v1/SemesterMarksList/${mail}/${pass}/${semester}"
        semesterStudyingPlanLink = "$baseLinkAuth/v1/SemesterStudyingPlan/${mail}/${pass}/${semester}"
    }

var groupLink:String = "$baseLink/v1/Group/faculty/${facultyID}"
var facultyLink:String = "$baseLink/v1/faculty"

var cathedraByFacultyLink:String = "$baseLink/v1/Cathedra/faculty/$facultyID"

var studentScheduleByGroupDayLink = "$baseLink/v1/prof/cathedra/${cathedraID}"
var buildingsLink = "$baseLink/v1/building"
var audienceByBuilingLink = "$baseLink/v1Audience/building/$buildingsID"
var audienceScheduleByAudienceDayLink = "$baseLink/v1/AudienceSchedule/Audience//${cathedraID}/Day/$dayOfTheWeek"
var studentScheduleByGroupLink:String = "$baseLink/v1/StudentSchedule/group/$groupID"
var profScheduleByProfLink:String = "$baseLink/v1/profschedule/prof/$profID"
var profscheduleByProfDayLink = "$baseLink/v1/ProfSchedule/Prof/$profID/Day/$dayOfTheWeek"
var profByCathedraLink:String = "$baseLink/v1/prof/cathedra/${cathedraID}"
var studentLogIn:String = "$baseLinkAuth/v1/PersonalInformation/${mail}/${pass}"
var semesterMarksListLink:String = "$baseLinkAuth/v1/SemesterMarksList/${mail}/${pass}/${semester}"
var semesterStudyingPlanLink:String = "$baseLinkAuth/v1/SemesterStudyingPlan/${mail}/${pass}/${semester}"
var currentRankLink:String = "$baseLinkAuth/v1/CurrentRank/${mail}/${pass}"
var debtListLink:String = "$baseLinkAuth/v1/debtlist/${mail}/${pass}/"
var personalInfoLink:String = "$baseLinkAuth/v1/PersonalInformation/${mail}/${pass}/"