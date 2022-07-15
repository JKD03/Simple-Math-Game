package com.jkdprojects.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    lateinit var finalscore : TextView
    lateinit var playagainbutton : Button
    lateinit var exitbutton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        finalscore=findViewById(R.id.finalscore)
        playagainbutton=findViewById(R.id.playagain)
        exitbutton=findViewById(R.id.exit)

        val score = intent.getIntExtra("score",0)

        finalscore.text=""+score

        playagainbutton.setOnClickListener {
            val intent = Intent(this@ResultActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        exitbutton.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

    }
}