package com.example.laredoutebetatest.ui.theme.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.laredoutebetatest.R
import com.example.laredoutebetatest.ui.theme.viewmodel.DataViewModel

class SelectInputFragment : Fragment() {
    private var selectedValue: String? = null
    private val dataViewModel: DataViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_select_input, container, false)

        val spinner = rootView.findViewById<Spinner>(R.id.selectSpinner)

        // Access the reviewFormData from the shared ViewModel
        val reviewFormData = dataViewModel.reviewFormData


        if (reviewFormData != null) {
            val options = getSelectOptions()

            Log.e("DATA FROM SELECT", reviewFormData.fields.toString())

//             Create an ArrayAdapter using the options from the API response
           val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options)

//             Set the adapter to the spinner
            spinner.adapter = adapter

            // Set a listener to capture the selected value
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedValue = options[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle when nothing is selected, if needed
                }
            }

//            val nextButton = rootView.findViewById<Button>(R.id.nextButton)
//
//            nextButton.setOnClickListener {
//
//                // Handle the user's rating input (selectedValue) as needed, e.g., save it in a ViewModel
//
//                // Navigate to the next fragment (IntegerInputFragment)
//                val integerInputFragment = IntegerInputFragment()
//                val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
//                fragmentTransaction.replace(R.id.fragmentGroup, integerInputFragment)
//                fragmentTransaction.addToBackStack(null) // Add to back stack for back navigation
//                fragmentTransaction.commit()
//            }
        } else {
//             Handle the case where reviewFormData is not available, e.g., show an error message
            Toast.makeText(requireContext(), "Review data is not available", Toast.LENGTH_SHORT).show()
        }
        return rootView
    }
    private fun getSelectOptions(): List<String> {

        val reviewFormData = dataViewModel.reviewFormData
        val firstSelectField = reviewFormData?.fields?.find { it.type == "SelectInput" }
        val selectedOptions = firstSelectField?.options?.map { it.name ?: "" } ?: emptyList()

        return selectedOptions
    }


}

