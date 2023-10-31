package com.example.laredoutebetatest.ui.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.laredoutebetatest.R
import com.example.laredoutebetatest.ui.viewmodel.DataViewModel

class IntegerInput : Fragment() {
    private var userRating: Int? = null
    private val myDataViewModel: DataViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_integer_input, container, false)

        val integerEditText = rootView.findViewById<EditText>(R.id.integerEditText)
            val ratingText = integerEditText.text.toString().trim()
            if (ratingText.isNotEmpty()) {
                userRating = ratingText.toInt()

            }

        myDataViewModel.reviewFormData?.let { response ->

            val textField = response.fields?.find { it.id == "ratingValue" }
            if (textField != null) {

                val titleTextView = rootView.findViewById<TextView>(R.id.titleTextView)
                titleTextView.text = textField.title.toString()
            }
        }

        return rootView
    }
}

