package com.example.laredoutebetatest.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.laredoutebetatest.R
import com.example.laredoutebetatest.ui.viewmodel.DataViewModel
import com.example.laredoutebetatest.data.model.DataCollecting
import com.example.laredoutebetatest.data.model.NameValue

class SelectInput : Fragment(), FragmentListener {
    private var selectedValue: String? = null
    private val dataViewModel: DataViewModel by activityViewModels()
    private var dataCollectingListener: DataCollecting? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_select_input, container, false)
        val spinner = rootView.findViewById<Spinner>(R.id.selectSpinner)
        val reviewFormData = dataViewModel.reviewFormData

        if (reviewFormData != null) {
            val options = getSelectOptions()

            Log.e("DATA FROM SELECT", reviewFormData.fields.toString())

            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options)

            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedValue = options[position]
                    collectUserData()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle when nothing is selected, if needed
                }
            }

        } else {
            Toast.makeText(requireContext(), "Review data is not available", Toast.LENGTH_SHORT).show()
        }

           reviewFormData?.let { response ->

            val textField = response.fields?.find { it.id == "lengthOfOwnership" }
            if (textField != null) {

                val titleTextView = rootView.findViewById<TextView>(R.id.titleTextView)
                titleTextView.text = textField.title.toString()
            }
        }
        return rootView
    }

    private fun getSelectOptions(): List<String> {
        val reviewFormData = dataViewModel.reviewFormData
        val firstSelectField = reviewFormData?.fields?.find { it.type == "SelectInput" }
        val selectedOptions = firstSelectField?.options?.map { it.name ?: "" } ?: emptyList()
        return selectedOptions
    }

    override fun collectUserData() {
        if (selectedValue != null) {
            sendData("selectedValue", selectedValue)
        }
    }

    private fun sendData(name: String, value: String?) {
        val nameValue = NameValue(name, value ?: "")
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
