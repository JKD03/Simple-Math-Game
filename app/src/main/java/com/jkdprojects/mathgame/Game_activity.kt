package com.jkdprojects.mathgame

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.random.Random

class Game_activity : AppCompatActivity() {

    lateinit var textScore : TextView
    lateinit var score : TextView
    lateinit var textlife : TextView
    lateinit var life : TextView
    lateinit var textTime : TextView
    lateinit var time : TextView
    lateinit var textQuestion : TextView
    lateinit var textanswer : TextView
    lateinit var ok : Button
    lateinit var next : Button
    var sum = 0
    var userscore = 0
    var userlife = 3

    lateinit var timer : CountDownTimer
    private val starttimer_millis : Long = 30000
    var timeleft_millis = starttimer_millis
    var check=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        supportActionBar!!.title="Addition"

        textScore=findViewById(R.id.textScore)
        score=findViewById(R.id.score)
        textlife=findViewById(R.id.textLife)
        life=findViewById(R.id.life)
        textTime=findViewById(R.id.textTime)
        time=findViewById(R.id.time)
        textQuestion=findViewById(R.id.textQuestion)
        textanswer=findViewById(R.id.editTextAnswer)
        ok=findViewById(R.id.buttonok)
        next=findViewById(R.id.buttonnext)

        gamecontinue()
        check=0
        ok.setOnClickListener {
            check=1
            pauseTimer()
            val input = textanswer.text.toString()
            if(input == "")
            {
                Toast.makeText(applicationContext,"Please enter an answer before clicking next button",
                    Toast.LENGTH_LONG).show()
                starttimer()
            }
            else{
                val userans = input.toInt()
                if(userans==sum){
                    userscore+=10
                    textQuestion.text="Congratulations,Your answer is correct"

                }
                else{
                    textQuestion.text="Your answer is Wrong!"
                    userlife=userlife-1
                }
                score.text=""+userscore
                life.text=""+userlife
            }
        }


        next.setOnClickListener {
            pauseTimer()
            resetTimer()
            if(check==0)
            {
                Toast.makeText(applicationContext,"please click OK Button First",Toast.LENGTH_SHORT).show()
            }
            else{
                textanswer.setText("")
                if(userlife==0)
                {
                    Toast.makeText(applicationContext,"Game Over",
                        Toast.LENGTH_LONG).show()
                    val intent = Intent(this@Game_activity,ResultActivity::class.java)
                    intent.putExtra("score",userscore)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    gamecontinue()
                    check=0
                }
            }

        }
    }

    fun gamecontinue()
    {
        val num01 = Random.nextInt(0,200)
        val num02 = Random.nextInt(0,500)
        val num1 = Random.nextInt(num01,1000)
        val num2 = Random.nextInt(num02,1000)
        sum=num1+num2
        textQuestion.text="$num1 + $num2"
        starttimer()
    }

    fun starttimer()
    {
        timer=object : CountDownTimer(timeleft_millis,1000){
            override fun onTick(millis_untilfinished: Long) {
                timeleft_millis=millis_untilfinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()
                check=1
                userlife--
                life.text=userlife.toString()
                textQuestion.text="Sorry,Time is up!!"
            }
        }.start()
    }

    fun updateText()
    {
        val remainingtime : Int =(timeleft_millis/1000).toInt()
        time.text= String.format(Locale.getDefault(),"%02d",remainingtime)
    }
    fun pauseTimer()
    {
        timer.cancel()
    }

    fun resetTimer()
    {
        timeleft_millis=starttimer_millis
        updateText()
    }
}