package com.zerdasoftware.countuptimer

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    private var timerStarted = false

    private lateinit var  timer:Timer
    private lateinit var  timerTask: TimerTask
    private var time:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timer =  Timer()

    }



    fun resetTapped(view:View){

        if (timer !=null){

            button_start.text = "START"
            button_start.setBackgroundColor(Color.GREEN)
            time = 0.0
            timerStarted = false
            textView_timer_count.setText(formatTime(0,0,0))
            timer.cancel()

        }

    }
    fun startStopTapped(view:View){
        if (!timerStarted){
            timerStarted = true
            button_start.text = "STOP"
            button_start.setBackgroundColor(Color.RED)
            startTimer()
        }else{
            timerStarted = false
            button_start.text = "START"
            button_start.setBackgroundColor(Color.GREEN)
            timer.cancel()

        }
    }

    fun startTimer(){
        timer = Timer()
        timer.schedule(timerTask{
            time++
            textView_timer_count.setText(getTimerText())
        },0,1000)
    }

     fun getTimerText(): String {

         var seconds:Int =((time.toInt() % 86400) % 3600) % 60
         var minutes:Int =((time.toInt() % 86400) % 3600) / 60
         var hours:Int =((time.toInt() % 86400) / 3600)

         return formatTime(seconds,minutes,hours)

    }

    private fun formatTime(seconds: Int, minutes: Int, hours: Int): String {
        return "${String.format("%02d",hours)} : ${String.format("%02d",minutes)} : ${String.format("%02d",seconds)}"
    }
}