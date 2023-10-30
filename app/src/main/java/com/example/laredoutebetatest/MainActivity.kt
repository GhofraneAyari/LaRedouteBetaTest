package com.example.laredoutebetatest


import android.content.Intent

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.laredoutebetatest.ui.theme.fragments.ImageRatingInputFragment


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Passed key from splash
        val authorizationKey = intent.getStringExtra("authorizationKey")

        setContentView(R.layout.activity_main)

        //BUTTON
        val intent = Intent(this, FeedbackActivity::class.java)
        val feedbackButton = findViewById<Button>(R.id.feedback_button)
        intent.putExtra("authorizationKey", authorizationKey)



        feedbackButton.setOnClickListener {
            startActivity(intent)



        }

        val clearButton = findViewById<Button>(R.id.clear_button)
        clearButton.setOnClickListener {
            finish() // Close the activity and exit the app
        }
    }


}








