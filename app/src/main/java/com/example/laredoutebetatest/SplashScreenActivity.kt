package com.example.laredoutebetatest

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //Todo: migrate to splash screen API: double splash screens

        // SplashScreen timer
        Handler(Looper.getMainLooper()).postDelayed({
            var authKey = "LTllMDMtZTE3ZDQyYTIxM2Fi"
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("authorization_data", authKey)
            startActivity(intent)
            finish()
        }, 1000)


    }


}