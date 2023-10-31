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
import com.example.laredoutebetatest.data.model.NameValue
import com.example.laredoutebetatest.data.model.ReviewFormResponse
import com.example.laredoutebetatest.ui.viewmodel.DataViewModel
import com.example.laredoutebetatest.util.Constants

class IntegerInput : Fragment(),FragmentListener {
    private var userRating: Int? = null
    private val myDataViewModel: DataViewModel by activityViewModels()
    private var dataCollectingListener: DataCollecting? = null
    private var userInputProvided: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_integer_input, container, false)
        val integerEditText = rootView.findViewById<EditText>(R.id.integerEditText)
        val ratingText = integerEditText.text.toString().trim()

        if (ratingText.isNotEmpty()) {
            userRating = ratingText.toInt()
            collectUserData()
        }

        val titleTextView = rootView.findViewById<TextView>(R.id.titleTextView)

        // Retrieve the field ID from the arguments
        val fieldId = arguments?.getString("fieldId")
        if (fieldId != null) {

            if (myDataViewModel.isIntegerInputField(fieldId)) {
                handleIntegerInput(fieldId, titleTextView)
            }
        }

        return rootView
    }

    private fun handleIntegerInput(fieldId: String, textView: TextView) {
        val field = myDataViewModel.getReviewFormField(fieldId)
        Log.d("IntegerInputFragment", "fieldId: $fieldId, field title: ${field?.title}")
        textView.text = field?.title ?: "Default Title"
    }

    override fun collectUserData() {
        if (userRating != null) {
            sendData(Constants.DATA_KEY_USER_RATING, userRating.toString())
        }
    }

    private fun sendData(name: String, value: String) {
        val nameValue = NameValue(name, value)
        Log.e("RECIEVED DATA FROM IMAGE RATING", nameValue.toString())
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

