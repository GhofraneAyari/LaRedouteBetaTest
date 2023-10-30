package com.example.laredoutebetatest

import TextInputFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.laredoutebetatest.data.model.DataCollecting
import com.example.laredoutebetatest.data.model.DataReceiver
import com.example.laredoutebetatest.data.model.NameValue
import com.example.laredoutebetatest.data.model.ReviewFormResponse
import com.example.laredoutebetatest.data.networking.ApiService
import com.example.laredoutebetatest.data.networking.ServiceGenerator
import com.example.laredoutebetatest.ui.theme.fragments.BooleanInputFragment
import com.example.laredoutebetatest.ui.theme.fragments.DefaultFragment
import com.example.laredoutebetatest.ui.theme.fragments.EndFormFragment
import com.example.laredoutebetatest.ui.theme.fragments.ImageRatingInputFragment
import com.example.laredoutebetatest.ui.theme.fragments.IntegerInputFragment
import com.example.laredoutebetatest.ui.theme.fragments.SelectInputFragment
import com.example.laredoutebetatest.ui.theme.fragments.VPadapter
import com.example.laredoutebetatest.ui.theme.viewmodel.DataViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedbackActivity : AppCompatActivity(), DataReceiver, DataCollecting {
    private val myDataViewModel: DataViewModel by viewModels()
    private lateinit var viewPager: ViewPager2
    private lateinit var feedbackAdapter: VPadapter
    private val fragments = ArrayList<Fragment>()
    private var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        val nextButton = findViewById<Button>(R.id.nextButton)

        // Passed key from splash
        val authorizationKey = intent.getStringExtra("authorizationKey")

        viewPager = findViewById(R.id.view_pager2)

        // API FETCH
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getRequest()

        // START REQUEST
        call.enqueue(object : Callback<ReviewFormResponse> {
            override fun onResponse(call: Call<ReviewFormResponse>, response: Response<ReviewFormResponse>) {
                if (response.isSuccessful) {
                    Log.e("Success", response.body().toString())
                    onDataFetched(response.body()) // Call onDataFetched with the response data

                    val reviewFormResponse = response.body()
                    if (reviewFormResponse != null) {
                        // Process the JSON response and create fragments
                        processReviewFormResponse(reviewFormResponse)
                    }
                }
            }

            override fun onFailure(call: Call<ReviewFormResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
                failureMessage("API ERROR")
            }
        })

        nextButton.setOnClickListener {
            navigateToNextFragment()
//            TODO("Fragment navigation only with swipe, button not working")
        }

        val clearButton = findViewById<Button>(R.id.clear_button)
        clearButton.setOnClickListener {
            finish() // Close the activity and exit the app
        }
    }

    private fun failureMessage(errorMessage: String) {
        runOnUiThread {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    fun onDataFetched(data: ReviewFormResponse?) {
        if (data != null) {
            myDataViewModel.reviewFormData = data
            Log.e("DATA FROM FEEDBACK", data.fields?.get(0)?.type.toString())

            // Load the image using Coil
            val imageUrl = data.backgroundUrl
            val coilImage = findViewById<ImageView>(R.id.coilImage)

            this@FeedbackActivity?.let { activity ->
                coilImage.load(imageUrl) {
                    placeholder(R.drawable.loading)
                }
            }
        }
    }

    private fun navigateToNextFragment() {
        if (currentPosition < fragments.size - 1) {
            currentPosition++
            viewPager.setCurrentItem(currentPosition, true)
        }
    }




//    private fun navigateToNextFragment() {
//        if (currentPosition < fragments.size - 1) {
//            currentPosition++
//            viewPager.setCurrentItem(currentPosition, true)
//        }
//    }

    private fun processReviewFormResponse(data: ReviewFormResponse) {
        val fragments = ArrayList<Fragment>()

        // Loop through fields in the JSON response and create corresponding fragments
        for (field in data.fields!!) {
            val fragment = when (field.type) {
                "BooleanInput" -> BooleanInputFragment()
                "ImageRatingInput" -> ImageRatingInputFragment()
                "IntegerInput" -> IntegerInputFragment()
                "SelectInput" -> SelectInputFragment()
                "TextInput" -> TextInputFragment()
                else -> EndFormFragment() // End of the form
            }

            if (fragment is DataReceiver) {
                fragment.receiveData(field)
            }

            fragments.add(fragment)
        }

        val feedbackAdapter = VPadapter(fragments, this.supportFragmentManager, lifecycle)
        viewPager.adapter = feedbackAdapter
    }

    override fun receiveData(reviewField: ReviewFormResponse.ReviewField) {
        TODO("Not yet implemented")
    }


    override fun onUserDataCollected(data: List<NameValue>) {
        // Handle the collected user data (received from fragments)
        // You can store the data and send it when needed
    }

    override fun sendUserData(userData: List<NameValue>) {
        // Send a POST request with the user data using your ApiService
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.postRequest(userData)

        // Start the POST request
        call.enqueue(object : Callback<List<NameValue>> {
            override fun onResponse(call: Call<List<NameValue>>, response: Response<List<NameValue>>) {
                if (response.isSuccessful) {
                    Log.e("Success", "POST request successful")

                    // Handle the success response as needed
                }
            }

            override fun onFailure(call: Call<List<NameValue>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())

                // Handle the error case
            }
        })
    }
}
