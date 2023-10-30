package com.example.laredoutebetatest.ui.theme.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import com.example.laredoutebetatest.R
import com.example.laredoutebetatest.data.model.DataCollecting
import com.example.laredoutebetatest.data.model.NameValue

class BooleanInputFragment : Fragment() {
    private lateinit var rootView: View
    private lateinit var yesCheckBox: CheckBox
    private lateinit var noCheckBox: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_boolean_input, container, false)

        yesCheckBox = rootView.findViewById(R.id.yesCheckBox)
        noCheckBox = rootView.findViewById(R.id.NoCheckBox)

        // Set up CheckBox listeners here if needed

        return rootView
    }

    // Implement DataCollecting interface
    private var dataCollectingListener: DataCollecting? = null

    fun collectUserData() {
        if (yesCheckBox.isChecked) {
            sendData("isRecommended", "true")
        } else if (noCheckBox.isChecked) {
            sendData("isRecommended", "false")
        } else {
            // Handle the case where neither checkbox is checked
        }
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
