package com.example.timefighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {



    private lateinit var scoreTextView : TextView
    private lateinit var timeTextView : TextView
    private lateinit var button : Button

    var score = 0
    private  var gameStarted = false


    private lateinit var countDownTimer: CountDownTimer
    private var initialCountDown: Long = 60000
    private var countDownInterval: Long = 1000
    private var timeLeft = 60

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scoreTextView = findViewById(R.id.score)
        timeTextView = findViewById(R.id.time)
        button = findViewById(R.id.start_button)

        button.setOnClickListener { increamentScore() }
        resetGame()
    }


    private fun increamentScore(){
        if(!gameStarted){
            startGame()
        }
        score++
        val newScore = getString(R.string.score , score)
        scoreTextView.text = newScore

    }
    private fun resetGame(){
        score= 0
        val initialScore = getString(R.string.score , score)
        scoreTextView.text = initialScore

        val initialtimeLeft = getString(R.string.time , 60)
        timeTextView.text = initialtimeLeft

        countDownTimer = object :  CountDownTimer(initialCountDown , countDownInterval){
            override fun onTick(millisUntilFinished: Long) {
             timeLeft = millisUntilFinished.toInt()
            val timeLeftString = getString(R.string.time , timeLeft)
            timeTextView.text = timeLeftString}

            override fun onFinish() {
                endGame()
            }
        }
      gameStarted = false
    }
    private fun startGame(){
        countDownTimer.start()
        gameStarted= true

    }
    private fun endGame(){
        Toast.makeText(this , getString(R.string.game_over , score ) , Toast.LENGTH_LONG).show()
        resetGame()


    }




}