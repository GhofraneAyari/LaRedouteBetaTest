package com.example.laredoutebetatest.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.laredoutebetatest.R
import com.example.laredoutebetatest.data.model.DataCollecting
import com.example.laredoutebetatest.data.model.ReviewFormResponse
import com.example.laredoutebetatest.ui.viewmodel.DataViewModel
import com.example.laredoutebetatest.util.Constants
import com.example.laredoutebetatest.data.model.NameValue


class TextInput : Fragment() {
    private var userTitle: String? = null
    private var dataCollectingListener: DataCollecting? = null
    private val myDataViewModel: DataViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_text_input, container, false)
        val titleEditText = rootView.findViewById<EditText>(R.id.titleEditText)
        userTitle = titleEditText.text.toString().trim()

        myDataViewModel.reviewFormData?.let { response ->
            val textField = response.fields?.find { it.id == "reviewtext" }
            if (textField != null) {
                val titleTextView = rootView.findViewById<TextView>(R.id.titleTextView)
                titleTextView.text = textField.title.toString()
            }
        }

        return rootView
    }

    fun collectUserData() {
        userTitle = view?.findViewById<EditText>(R.id.titleEditText)?.text?.toString()

        if (!userTitle.isNullOrBlank()) {
            sendData(Constants.DATA_KEY_USER_TEXT_INPUT, userTitle!!)
        }
    }

    private fun sendData(name: String, value: String) {
        val nameValue = NameValue(name, value)
        Log.e("RECEIVED DATA FROM text input", nameValue.toString())
        dataCollectingListener?.onUserDataCollected(listOf(NameValue(name, value)))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DataCollecting) {
            dataCollectingListener = context
        } else {
            throw RuntimeException("$context must implement DataCollecting")
        }
    }
}

