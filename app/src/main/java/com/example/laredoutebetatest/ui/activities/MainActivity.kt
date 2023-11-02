package com.example.laredoutebetatest.ui.activities


import android.content.Intent

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.laredoutebetatest.R


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val authorizationKey = intent.getStringExtra("authorizationKey")
        setContentView(R.layout.activity_main)
        val intent = Intent(this, FeedbackActivity::class.java)
        val feedbackButton = findViewById<Button>(R.id.feedback_button)
        intent.putExtra("authorizationKey", authorizationKey)

        feedbackButton.setOnClickListener {
            startActivity(intent)
        }

        val clearButton = findViewById<Button>(R.id.clear_button)
        clearButton.setOnClickListener {
            finish()
        }
    }


}








