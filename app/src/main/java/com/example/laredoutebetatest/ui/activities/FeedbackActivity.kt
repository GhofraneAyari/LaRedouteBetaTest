package com.example.laredoutebetatest.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.laredoutebetatest.R
import com.example.laredoutebetatest.util.Constants
import com.example.laredoutebetatest.data.model.DataCollecting
import com.example.laredoutebetatest.data.model.DataReceiver
import com.example.laredoutebetatest.data.model.ReviewFormResponse
import com.example.laredoutebetatest.data.model.NameValue
import com.example.laredoutebetatest.data.networking.ApiService
import com.example.laredoutebetatest.data.networking.ServiceGenerator
import com.example.laredoutebetatest.ui.fragments.FragmentFactory
import com.example.laredoutebetatest.ui.viewmodel.DataViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedbackActivity : AppCompatActivity(), DataReceiver, DataCollecting {
    private val myDataViewModel: DataViewModel by viewModels()
    private lateinit var viewPager: ViewPager2
    private lateinit var coilImage: ImageView
    private var currentPosition = 0
    private val fragmentFactory: FragmentFactory by lazy { FragmentFactory(this) }
    private var userInputProvided = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        setupViews()
        setupButtonClickListeners()
        fetchFormData()
    }

    private fun setupViews() {
        val nextButton = findViewById<Button>(Constants.BUTTON_NEXT)
        val clearButton = findViewById<Button>(Constants.BUTTON_CLEAR)
        viewPager = findViewById(R.id.view_pager2)
        coilImage = findViewById(R.id.coilImage)
    }

    private fun setupButtonClickListeners() {
        val nextButton = findViewById<Button>(Constants.BUTTON_NEXT)
        val clearButton = findViewById<Button>(Constants.BUTTON_CLEAR)

        nextButton.setOnClickListener {
            if (userInputProvided) {
                navigateToNextFragment()
            } else {
                // Show a toast to inform the user to provide input
                Toast.makeText(this, "Please provide input before proceeding", Toast.LENGTH_SHORT).show()
            }
        }
        clearButton.setOnClickListener {
            finish()
        }
    }

    private fun fetchFormData() {
        val authorizationKey = intent.getStringExtra("authorizationKey")

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getFormData()

        call.enqueue(object : Callback<ReviewFormResponse> {
            override fun onResponse(call: Call<ReviewFormResponse>, response: Response<ReviewFormResponse>) {
                if (response.isSuccessful) {
                    onDataFetched(response.body())
                    val reviewFormResponse = response.body()
                    if (reviewFormResponse != null) {
                        fragmentFactory.createFragments(reviewFormResponse)
                        fragmentFactory.setupViewPager(viewPager)


                    }
                }
            }

            override fun onFailure(call: Call<ReviewFormResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
                showFailureMessage(Constants.MESSAGE_API_ERROR)
            }
        })
    }

    private fun showFailureMessage(errorMessage: String) {
        runOnUiThread {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun onDataFetched(data: ReviewFormResponse?) {
        if (data != null) {
            myDataViewModel.reviewFormData = data
            Log.e("DATA FROM FEEDBACK", data.fields?.get(0)?.type.toString())

            // Load the image using Coil
            val imageUrl = data.backgroundUrl
            coilImage.load(imageUrl) {
                placeholder(R.drawable.loading)
            }
        }
    }

    private fun navigateToNextFragment() {
        currentPosition++
        if (currentPosition < fragmentFactory.fragments.size) {
            viewPager.setCurrentItem(currentPosition, true)
        } else {
            showCompletionMessage()
        }
    }

    override fun receiveData(reviewField: ReviewFormResponse.ReviewField) {
    }

    override fun onUserDataCollected(data: List<NameValue>) {
        userInputProvided = true
    }

    override fun sendUserData(userData: List<NameValue>) {
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.postUserInputData(userData)


        call.enqueue(object : Callback<List<NameValue>> {
            override fun onResponse(call: Call<List<NameValue>>, response: Response<List<NameValue>>) {
                if (response.isSuccessful) {
                    Log.e("Success", Constants.MESSAGE_POST_SUCCESS)

                }
            }

            override fun onFailure(call: Call<List<NameValue>>, t: Throwable) {
                t.printStackTrace()
                Log.e("Error", t.message.toString())

            }
        })
    }

    private fun showCompletionMessage() {
        Toast.makeText(this, "Your feedback has been sent!", Toast.LENGTH_SHORT).show()
        finish()
    }
}
