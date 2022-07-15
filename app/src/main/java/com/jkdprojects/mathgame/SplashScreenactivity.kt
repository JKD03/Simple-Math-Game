package com.jkdprojects.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class SplashScreenactivity : AppCompatActivity() {

    lateinit var img : ImageView
    lateinit var text : TextView
    lateinit var creator : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screenactivity)

        val imganim =AnimationUtils.loadAnimation(this,R.anim.img_animation)
        img=findViewById(R.id.logo)
        text=findViewById(R.id.text5)
        creator=findViewById(R.id.textView2)
        img.animate().alpha(0.0F).setDuration(500).setStartDelay(0)
        text.animate().alpha(0.0F).setDuration(500).setStartDelay(0)
        creator.animate().alpha(0.0F).setDuration(500).setStartDelay(100)
        val splashScreenTimeOut =500
        val intent=Intent(this,MainActivity::class.java)
        Handler().postDelayed({
            startActivity(intent)
            finish()
        },splashScreenTimeOut.toLong())
    }
}