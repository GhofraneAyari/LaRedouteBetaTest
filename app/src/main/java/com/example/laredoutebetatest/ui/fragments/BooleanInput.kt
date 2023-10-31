package com.example.laredoutebetatest.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.laredoutebetatest.R
import com.example.laredoutebetatest.data.model.DataCollecting
import com.example.laredoutebetatest.data.model.NameValue
import com.example.laredoutebetatest.util.Constants
import com.example.laredoutebetatest.util.Constants.BOOLEAN_FALSE
import com.example.laredoutebetatest.util.Constants.BOOLEAN_TRUE
import com.example.laredoutebetatest.ui.viewmodel.DataViewModel


class BooleanInput : Fragment(), FragmentListener {
    private lateinit var yesCheckBox: CheckBox
    private lateinit var noCheckBox: CheckBox
    private var dataCollectingListener: DataCollecting? = null
    private val myDataViewModel: DataViewModel by activityViewModels()
    private var userInputProvided: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_boolean_input, container, false)
        yesCheckBox = rootView.findViewById(R.id.yesCheckBox)
        noCheckBox = rootView.findViewById(R.id.NoCheckBox)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myDataViewModel.reviewFormData?.let { response ->
            val booleanInputField = response.fields?.find { it.type == "BooleanInput" }
            if (booleanInputField != null) {
                val titleTextView = view.findViewById<TextView>(R.id.boolTitleTextView)
                titleTextView.text = booleanInputField.title.toString()

                booleanInputField.options?.forEach { option ->
                    when (option.value) {
                        "true" -> {
                            yesCheckBox.text = option.name
                            yesCheckBox.tag = option.value
                        }
                        "false" -> {
                            noCheckBox.text = option.name
                            noCheckBox.tag = option.value
                        }
                    }
                }
            }
        }
    }

    override fun collectUserData() {
        val selectedValue = when {
            yesCheckBox.isChecked -> BOOLEAN_TRUE
            noCheckBox.isChecked -> BOOLEAN_FALSE
            else -> ""
        }
        sendData(Constants.DATA_KEY_IS_RECOMMENDED, selectedValue)
        userInputProvided = true
    }

    private fun sendData(name: String, value: String) {
        val nameValue = NameValue(name, value)
        dataCollectingListener?.onUserDataCollected(listOf(nameValue))
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




