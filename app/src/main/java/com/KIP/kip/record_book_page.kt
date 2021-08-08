package com.KIP.kip

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import khttp.get
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import java.util.concurrent.CountDownLatch


class record_book_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_book_page)

        val button = findViewById<ImageButton>(R.id.imageButton2)
            button.setOnClickListener {
                val intent = Intent(this, MainScreen_page::class.java)
                startActivity(intent)
            }

        val button4 = findViewById<ImageButton>(R.id.imageButton4)
        button4.setOnClickListener{
            students = false
            selectedScheduleType = 0
            val intent = Intent(this, select_day_schedule_page::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<ImageButton>(R.id.imageButton)
        button2.setOnClickListener{
            selectedScheduleType = 1
            val intent = Intent(this, select_BuidlingFaculty_page::class.java)
            startActivity(intent)
        }

        val button3 = findViewById<ImageButton>(R.id.proffButton)
        button3.setOnClickListener{
            selectedScheduleType = 2
            val intent = Intent(this, select_BuidlingFaculty_page::class.java)
            startActivity(intent)
        }

        val gson = Gson()
        val semesterMarkListList = object : TypeToken<List<semesterMarkList>>() {}.type
        val semesterStudyingPlanlist = object : TypeToken<List<semesterStudyingPlan>>() {}.type
        val debtList = object : TypeToken<List<debt>>() {}.type
        val currentRankList = object : TypeToken<List<currentRank>>() {}.type

        val auditoryList = object : TypeToken<List<auditory>>() {}.type


        semesterMarkLists.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }

        val c = CountDownLatch(1)
        connectionDone=false

        val task = doAsync(){
            if(selectedScheduleType==3) {
                println(semesterMarksListLink)
                val jsonFileString2 = get(semesterMarksListLink)
                semesterMarkLists = gson.fromJson(jsonFileString2.text, semesterMarkListList)
            }
            else if(selectedScheduleType==4){
                println(audienceByBuilingLink)
                val jsonFileString2 = get(audienceByBuilingLink)
                auditorys = gson.fromJson(jsonFileString2.text, auditoryList)
            }
            else if(selectedScheduleType==5){
                println(semesterStudyingPlanLink)
                val jsonFileString2 = get(semesterStudyingPlanLink)
                semesterStudyingPlanLists = gson.fromJson(
                        jsonFileString2.text,
                        semesterStudyingPlanlist
                )
            }
            else if(selectedScheduleType==6){
                println(debtListLink)
                val jsonFileString2 = get(debtListLink)
                debtLists = gson.fromJson(jsonFileString2.text, debtList)
            }
            else if(selectedScheduleType==7){
                println(currentRankLink)
                val jsonFileString2 = get(currentRankLink)
                currentRankLists = gson.fromJson(jsonFileString2.text, currentRankList)
            }


            this.activityUiThread {
                connectionDone()
            }
            //println(jsonFileString.jsonArray)
        }
        checkSatus()

    }

    fun popupMessage() {
        val alertDialogBuilder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(
                this
        )
        alertDialogBuilder.setMessage("Відсутнє інтернет-з'єднання.")
        alertDialogBuilder.setIcon(R.drawable.kip_logo)
        alertDialogBuilder.setTitle("Увага!")
        alertDialogBuilder.setNegativeButton(
                "Ок",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    Log.d("internet", "Ok btn pressed")
                    // add these two lines, if you wish to close the app:
                    //finishAffinity()
                    semester = 2
                    val intent = Intent(this, MainScreen_page::class.java)
                    startActivity(intent)
                    //System.exit(0)
                })
        val alertDialog: android.app.AlertDialog? = alertDialogBuilder.create()
        alertDialog?.show()
    }

    fun getAngleDrawable(
            solidColor: Int,
            _radius: FloatArray?,
            strokeWidth: Int,
            strokeColor: Int
    ): GradientDrawable? {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(solidColor)
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        gradientDrawable.cornerRadii = _radius
        gradientDrawable.setStroke(strokeWidth, strokeColor)
        return gradientDrawable
    }


    fun connectionDone() {
        connectionDone=true
        var chipsArray: Array<Chip> = emptyArray()
        var number:Int =1

        println("selected schedule $selectedScheduleType")

        var semestersToDraw:Int = studentList[0].course*2
        if(selectedScheduleType==5){
            semestersToDraw=12
        }

        while(number<=semestersToDraw){
            val but = Button(this) // = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            //LinearLayout.LayoutParams.MATCH_PARENT)
            but.layoutParams  = LinearLayout.LayoutParams(
                50,
                LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f
            )
            //text.setPadding(20,20,0,10)
            but.setTextColor(Color.parseColor("#1C2C84"))
            but.setBackgroundColor(Color.parseColor("#46B6E1"))
            but.text= number.toString()
            number++
            but.gravity = Gravity.CENTER
            but.textSize = 8.0F
            val layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
            but.layoutParams = layoutParams
            but.background = getAngleDrawable(
                Color.parseColor("#FFFFFF"),
                floatArrayOf(0f, 0f, 10f, 10f, 10f, 10f, 0f, 0f),
                3,
                Color.parseColor("#3F51B5")
            )

            but.setOnClickListener{
                semester = but.text.toString().toInt()
                val intent = Intent(this, record_book_page::class.java)
                startActivity(intent)
            }

            val finalParent = this.findViewById(R.id.SemesterButtonsLayout) as ViewGroup
            finalParent.addView(but)
        }

        if(selectedScheduleType==3) {
            val comparator = profCompare()
            println(profs)
            profs = profs.sortedWith(comparator)
            println(profs)

            for (semesterMarklist in semesterMarkLists) {
                val parent = LinearLayout(this)
                parent.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                parent.orientation = LinearLayout.HORIZONTAL
                parent.setPadding(30, 0, 20, 0)
                val frame = FrameLayout(this)
                frame.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1.0f
                )


                //children of parent linearlayout
                val imButton = ImageButton(this)
                //val text = TextView(ContextThemeWrapper(this, R.style.back_text), null, 0)
                val text = TextView(this) // = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                //LinearLayout.LayoutParams.MATCH_PARENT)
                text.layoutParams  = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, 0.5f
                )
                text.setPadding(20, 20, 0, 10)
                text.setTextColor(Color.parseColor("#1C2C84"))
                text.text= semesterMarklist.subject
                text.gravity = Gravity.CENTER
                text.textSize = 15.0F

                text.background = getAngleDrawable(
                    Color.parseColor("#FFFFFF"),
                    floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f),
                    3,
                    Color.parseColor("#3F51B5")
                )

                //gradientDrawableDefault.setColor(Color.TRANSPARENT)

                val params = LinearLayout.LayoutParams(40, 40)
                params.setMargins(5, 5, 5, 5)

                imButton.layoutParams = params
                imButton.setImageResource(R.drawable.raiting)
                imButton.scaleType = ImageView.ScaleType.CENTER_CROP
                imButton.setBackgroundColor(Color.parseColor("#000000"))

                frame.removeAllViews()
                frame.addView(text)
                frame.addView(imButton)

                val text2 = TextView(this) // = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                //LinearLayout.LayoutParams.MATCH_PARENT)
                text2.layoutParams  = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1.0f
                )
                text2.setPadding(20, 20, 0, 10)
                text2.setTextColor(Color.parseColor("#1C2C84"))
                text2.text= semesterMarklist.fullMark.toString()
                text2.gravity = Gravity.CENTER
                text2.textSize = 15.0F
                text2.background = getAngleDrawable(
                    Color.parseColor("#FFFFFF"),
                    floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f),
                    3,
                    Color.parseColor("#3F51B5")
                )

                parent.removeAllViews()
                parent.addView(frame)
                parent.addView(text2)

                val finalParent = this.findViewById(R.id.contentLayout) as ViewGroup

                finalParent.addView(parent)

            }

        }
        else if(selectedScheduleType==4) {
            findViewById<TextView>(R.id.textView10).text = "Рейтинг"
            findViewById<ImageView>(R.id.imageView15).setImageResource(R.drawable.raiting)
            val comparator = profCompare()
            println(profs)
            profs = profs.sortedWith(comparator)
            println(profs)

            for (semesterMarklist in semesterMarkLists) {
                val parent = LinearLayout(this)
                parent.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                parent.orientation = LinearLayout.HORIZONTAL
                parent.setPadding(30, 0, 20, 0)
                val frame = FrameLayout(this)
                frame.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1.0f
                )


                //children of parent linearlayout
                val imButton = ImageButton(this)
                val text = TextView(this) // = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                //LinearLayout.LayoutParams.MATCH_PARENT)
                text.layoutParams  = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, 0.5f
                )
                text.setPadding(20, 20, 0, 10)
                text.setTextColor(Color.parseColor("#1C2C84"))
                text.text= number.toString()
                number++
                text.gravity = Gravity.CENTER
                text.textSize = 15.0F
                text.background = getAngleDrawable(
                    Color.parseColor("#FFFFFF"),
                    floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f),
                    3,
                    Color.parseColor("#3F51B5")
                )

                val params = LinearLayout.LayoutParams(40, 40)
                params.setMargins(5, 5, 5, 5)

                imButton.layoutParams = params
                imButton.setImageResource(R.drawable.raiting)
                imButton.scaleType = ImageView.ScaleType.CENTER_CROP
                imButton.setBackgroundColor(Color.parseColor("#000000"))

                frame.removeAllViews()
                frame.addView(text)
                frame.addView(imButton)

                val text2 = TextView(this) // = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                //LinearLayout.LayoutParams.MATCH_PARENT)
                text2.layoutParams  = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1.0f
                )
                text2.setPadding(20, 20, 0, 10)
                text2.setTextColor(Color.parseColor("#1C2C84"))
                text2.text= semesterMarklist.fullMark.toString()
                text2.gravity = Gravity.CENTER
                text2.textSize = 15.0F
                text2.background = getAngleDrawable(
                    Color.parseColor("#FFFFFF"),
                    floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f),
                    3,
                    Color.parseColor("#3F51B5")
                )

                val text3 = TextView(this) // = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                //LinearLayout.LayoutParams.MATCH_PARENT)
                text2.layoutParams  = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1.0f
                )
                text3.setPadding(20, 20, 0, 10)
                text3.setTextColor(Color.parseColor("#1C2C84"))
                text3.text= semesterMarklist.fullMark.toString()
                text3.gravity = Gravity.CENTER
                text3.textSize = 15.0F
                text3.background = getAngleDrawable(
                    Color.parseColor("#FFFFFF"),
                    floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f),
                    3,
                    Color.parseColor("#3F51B5")
                )

                parent.removeAllViews()
                parent.addView(frame)
                parent.addView(text2)
                parent.addView(text3)

                val finalParent = this.findViewById(R.id.contentLayout) as ViewGroup

                finalParent.addView(parent)

            }

        }
        else if(selectedScheduleType==5) {
            findViewById<TextView>(R.id.textView10).text = "Навчальний план"
            findViewById<ImageView>(R.id.imageView15).setImageResource(R.drawable.plan)
            val comparator = profCompare()
            println(profs)
            profs = profs.sortedWith(comparator)
            println(profs)

            for (semesterStudyingPlan in semesterStudyingPlanLists) {
                val parent = LinearLayout(this)
                parent.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                parent.orientation = LinearLayout.HORIZONTAL
                parent.setPadding(30, 0, 20, 0)
                val frame = FrameLayout(this)
                frame.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1.0f
                )


                //children of parent linearlayout
                val imButton = ImageButton(this)
                val text = TextView(this) // = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                //LinearLayout.LayoutParams.MATCH_PARENT)
                text.layoutParams  = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, 0.5f
                )
                text.setPadding(20, 20, 0, 10)
                text.setTextColor(Color.parseColor("#1C2C84"))
                text.text= semesterStudyingPlan.subject
                text.gravity = Gravity.CENTER
                text.textSize = 15.0F
                text.background = getAngleDrawable(
                    Color.parseColor("#FFFFFF"),
                    floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f),
                    3,
                    Color.parseColor("#3F51B5")
                )

                val params = LinearLayout.LayoutParams(40, 40)
                params.setMargins(5, 5, 5, 5)

                imButton.layoutParams = params
                imButton.setImageResource(R.drawable.raiting)
                imButton.scaleType = ImageView.ScaleType.CENTER_CROP
                imButton.setBackgroundColor(Color.parseColor("#000000"))

                frame.removeAllViews()
                frame.addView(text)
                frame.addView(imButton)

                parent.removeAllViews()
                parent.addView(frame)

                val finalParent = this.findViewById(R.id.contentLayout) as ViewGroup

                finalParent.addView(parent)

            }

        }
        else if(selectedScheduleType==6) {
            findViewById<TextView>(R.id.textView10).text = "Борги"
            findViewById<ImageView>(R.id.imageView15).setImageResource(R.drawable.debts)
            val comparator = profCompare()
            println(profs)
            profs = profs.sortedWith(comparator)
            println(profs)

            for (debt in debtLists) {
                val parent = LinearLayout(this)
                parent.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                parent.orientation = LinearLayout.HORIZONTAL
                parent.setPadding(30, 0, 20, 0)
                val frame = FrameLayout(this)
                frame.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1.0f
                )


                //children of parent linearlayout
                val imButton = ImageButton(this)
                val text = TextView(this) // = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                //LinearLayout.LayoutParams.MATCH_PARENT)
                text.layoutParams  = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, 0.5f
                )
                text.setPadding(20, 20, 0, 10)
                text.setTextColor(Color.parseColor("#1C2C84"))
                text.text= debt.subject
                text.gravity = Gravity.CENTER
                text.textSize = 15.0F
                text.background = getAngleDrawable(
                    Color.parseColor("#FFFFFF"),
                    floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f),
                    3,
                    Color.parseColor("#3F51B5")
                )
                val params = LinearLayout.LayoutParams(40, 40)
                params.setMargins(5, 5, 5, 5)

                imButton.layoutParams = params
                imButton.setImageResource(R.drawable.raiting)
                imButton.scaleType = ImageView.ScaleType.CENTER_CROP
                imButton.setBackgroundColor(Color.parseColor("#000000"))

                frame.removeAllViews()
                frame.addView(text)
                frame.addView(imButton)

                parent.removeAllViews()
                parent.addView(frame)

                val finalParent = this.findViewById(R.id.contentLayout) as ViewGroup

                finalParent.addView(parent)

            }

        }
        else if(selectedScheduleType==7) {
            val comparator = profCompare()
            println(profs)
            profs = profs.sortedWith(comparator)
            println(profs)

            for (currentrank in currentRankLists) {
                val parent = LinearLayout(this)
                parent.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                parent.orientation = LinearLayout.HORIZONTAL
                parent.setPadding(30, 0, 20, 0)
                val frame = FrameLayout(this)
                frame.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1.0f
                )


                //children of parent linearlayout
                val imButton = ImageButton(this)
                //val text = TextView(ContextThemeWrapper(this, R.style.back_text), null, 0)
                val text = TextView(this) // = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                //LinearLayout.LayoutParams.MATCH_PARENT)
                text.layoutParams  = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, 0.5f
                )
                text.setPadding(20, 20, 0, 10)
                text.setTextColor(Color.parseColor("#1C2C84"))
                text.text= currentrank.fio.toString()
                text.gravity = Gravity.CENTER
                text.textSize = 15.0F

                text.background = getAngleDrawable(
                    Color.parseColor("#FFFFFF"),
                    floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f),
                    3,
                    Color.parseColor("#3F51B5")
                )

                //gradientDrawableDefault.setColor(Color.TRANSPARENT)

                val params = LinearLayout.LayoutParams(40, 40)
                params.setMargins(5, 5, 5, 5)

                imButton.layoutParams = params
                imButton.setImageResource(R.drawable.raiting)
                imButton.scaleType = ImageView.ScaleType.CENTER_CROP
                imButton.setBackgroundColor(Color.parseColor("#000000"))

                frame.removeAllViews()
                frame.addView(text)
                frame.addView(imButton)

                val text2 = TextView(this) // = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                //LinearLayout.LayoutParams.MATCH_PARENT)
                text2.layoutParams  = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1.0f
                )
                text2.setPadding(20, 20, 0, 10)
                text2.setTextColor(Color.parseColor("#1C2C84"))
                text2.text= currentrank.rank.toString()
                text2.gravity = Gravity.CENTER
                text2.textSize = 15.0F
                text2.background = getAngleDrawable(
                    Color.parseColor("#FFFFFF"),
                    floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f),
                    3,
                    Color.parseColor("#3F51B5")
                )

                parent.removeAllViews()
                parent.addView(frame)
                parent.addView(text2)

                val finalParent = this.findViewById(R.id.contentLayout) as ViewGroup

                finalParent.addView(parent)

            }

        }
        var AnimationDay: Array<Animation> = emptyArray()

        var i:Long=0
        for(chip in chipsArray){
            val animation = AnimationUtils.loadAnimation(this, R.anim.kip_button_left)
            animation.duration=250
            animation.startOffset=100+i*50
            i+=1
            AnimationDay+=animation
        }
        var k=0
        for(chip in chipsArray){
            chip.startAnimation(AnimationDay[k])
            k+=1
        }

    }

    fun checkSatus(){
        var task= doAsync() {
            val c = CountDownLatch(1)
            c.await(8, java.util.concurrent.TimeUnit.SECONDS)
            if (!connectionDone) {
                this.activityUiThread {
                    semester = 1
                    popupMessage()
                }
            }
        }
    }
}