package com.example.laredoutebetatest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.laredoutebetatest.R
import com.example.laredoutebetatest.data.model.DataCollecting
import com.example.laredoutebetatest.data.model.NameValue
import com.example.laredoutebetatest.data.model.ReviewFormResponse
import com.example.laredoutebetatest.ui.activities.FeedbackActivity

class EndForm : Fragment(), DataCollecting {
    private var collectedUserData: List<NameValue> = emptyList()
    private var reviewField: ReviewFormResponse.ReviewField? = null
    private var nextButton: Button? = null
    private var userDataProvider: UserDataProvider? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_end_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nextButton = view.findViewById(R.id.nextButton)
        updateNextButtonText()

        nextButton?.setOnClickListener {

            userDataProvider?.provideUserData(collectedUserData)
            activity?.finish()
        }
    }

    override fun onUserDataCollected(data: List<NameValue>) {
        collectedUserData = data
    }

    private fun updateNextButtonText() {
        if (reviewField != null && reviewField?.type == "TextAreaInput") {
            nextButton?.text = reviewField?.buttonText ?: "Next"
        }
    }

    interface UserDataProvider {
        fun provideUserData(userData: List<NameValue>)
    }

    override fun sendUserData(data: List<NameValue>) {
        // Pass user data to FeedbackAct
        if (activity is FeedbackActivity) {
            (activity as FeedbackActivity).sendUserData(data)
        }
    }
}








