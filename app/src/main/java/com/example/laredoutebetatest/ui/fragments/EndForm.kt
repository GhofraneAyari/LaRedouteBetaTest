package com.example.laredoutebetatest.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.laredoutebetatest.R
import com.example.laredoutebetatest.data.model.DataCollecting
import com.example.laredoutebetatest.data.model.NameValue
import com.example.laredoutebetatest.data.model.ReviewFormResponse

class EndForm : Fragment(){
    private var collectedUserData: List<NameValue> = emptyList()
    private var reviewField: ReviewFormResponse.ReviewField? = null
    private var nextButton: Button? = null

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
//            sendUserData(collectedUserData)
            activity?.finish()
        }
    }

//    override fun onUserDataCollected(data: List<NameValue>) {
//        collectedUserData = data
//        updateNextButtonText()
//    }

    private fun updateNextButtonText() {
        if (reviewField != null && reviewField?.type == "TextAreaInput") {
            nextButton?.text = reviewField?.buttonText ?: "Next"
        }
    }
}

