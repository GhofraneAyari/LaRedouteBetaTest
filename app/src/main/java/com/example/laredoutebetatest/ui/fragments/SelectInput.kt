package com.example.laredoutebetatest.ui.fragments

import android.content.Context
import android.os.Bundle
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
import com.example.laredoutebetatest.data.model.ReviewFormResponse

class SelectInput : Fragment(), FragmentListener {
    private var selectedValue: String? = null
    private val dataViewModel: DataViewModel by activityViewModels()
    private var dataCollectingListener: DataCollecting? = null
    private var userInputProvided: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_select_input, container, false)
        val spinner = rootView.findViewById<Spinner>(R.id.selectSpinner)
        val titleTextView = rootView.findViewById<TextView>(R.id.titleTextView)

        val fieldId = arguments?.getString("fieldId")

        if (fieldId != null) {
            val reviewFormData = dataViewModel.reviewFormData

            if (reviewFormData != null) {
                val field = reviewFormData.fields?.find { it.id == fieldId }
                if (field != null) {
                    titleTextView.text = field.title

                    val options = getSelectOptions(field)
                    val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options)
                    spinner.adapter = adapter

                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            selectedValue = options[position]
                            collectUserData()
                            userInputProvided = true
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            // Handle when nothingis selected
                        }
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Review data is not available", Toast.LENGTH_SHORT).show()
            }
        }

        return rootView
    }

    private fun getSelectOptions(field: ReviewFormResponse.ReviewField): List<String> {
        return field.options?.map { it.name ?: "" } ?: emptyList()
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