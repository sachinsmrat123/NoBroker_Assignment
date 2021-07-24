package com.example.nobroker_assignment.ui.activities


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.nobroker_assignment.R
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreen : AppCompatActivity() {

    lateinit var frombottom: Animation
    lateinit var froTop: Animation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        frombottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)
        cdBtn.setAnimation(frombottom)
        froTop = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        voteimage.animation = froTop
        cdBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
        })
    }
}