package com.example.laredoutebetatest


import android.content.Intent

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.example.laredoutebetatest.data.model.ReviewFormResponse
import com.example.laredoutebetatest.data.model.networking.ApiService
import com.example.laredoutebetatest.data.model.networking.ServiceGenerator
import com.example.laredoutebetatest.ui.theme.fragments.BooleanInputFragment
import com.example.laredoutebetatest.ui.theme.viewmodel.DataViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private val myDataViewModel: DataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Passed key from splash
        val passedText = this.intent.getStringExtra("authKey")
        setContentView(R.layout.activity_main)

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getRequest()
        val intent = Intent(this, FeedbackActivity::class.java)



        val feedbackButton = findViewById<Button>(R.id.feedback_button)
        feedbackButton.setOnClickListener {

            call.enqueue(object : Callback<ReviewFormResponse> {

                override fun onResponse(call: Call<ReviewFormResponse>, response: Response<ReviewFormResponse>) {
                    if (response.isSuccessful) {
                        Log.e("Success", response.body().toString())
                        print("success")
                        onDataFetched(response.body()) // Call onDataFetched with the response data
                        startActivity(intent)

                    }
                }

                override fun onFailure(call: Call<ReviewFormResponse>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("error", t.message.toString())
                }
            })
        }

        val clearButton = findViewById<Button>(R.id.clear_button)
        clearButton.setOnClickListener {
            finish() // Close the activity and exit the app
        }
    }


    fun onDataFetched(data: ReviewFormResponse?) {
        if (data != null) {
            myDataViewModel.reviewFormData = data
            print(data)
            Log.d("MyApp", "Data: ${data.name}")
        }
    }
}




//                        val reviewFormResponse = response.body()
//                        val reviewFieldList = reviewFormResponse?.fields
//
//                        if (!reviewFieldList.isNullOrEmpty()) {
//                            val reviewField = reviewFieldList[0] // Assuming you want the first question
//                            val title = reviewField?.title ?: "Default Title"
//
//                            // Create a new fragment instance with the title
//                            val fragment = ReviewQuestionFragment.newInstance(title, R.drawable.dummy_image)
//
//                            // Trigger the fragment to display
//                            supportFragmentManager.beginTransaction()
//                                .replace(R.id.fragmentContainer, fragment) // Use your fragment container ID
//                                .commit()
//
//                    }


