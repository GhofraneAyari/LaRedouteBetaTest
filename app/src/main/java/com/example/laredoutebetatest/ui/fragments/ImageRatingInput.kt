package com.example.laredoutebetatest.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.laredoutebetatest.R
import com.example.laredoutebetatest.data.model.DataCollecting
import com.example.laredoutebetatest.ui.viewmodel.DataViewModel
import com.example.laredoutebetatest.data.model.ReviewFormResponse
import com.example.laredoutebetatest.util.Constants
import com.example.laredoutebetatest.util.Constants.COIL_IMAGE_ALPHA_HIDDEN
import com.example.laredoutebetatest.util.Constants.COIL_IMAGE_ALPHA_VISIBLE
import com.example.laredoutebetatest.util.Constants.DATA_KEY_USER_RATING
import com.example.laredoutebetatest.data.model.NameValue


class ImageRatingInput : Fragment(), FragmentListener {
    private val myDataViewModel: DataViewModel by activityViewModels()
    private var dataCollectingListener: DataCollecting? = null
    private var userRating: Float? = null
    private lateinit var rootView: View
    private lateinit var ratingBar: RatingBar
    private lateinit var imageView: ImageView
    private lateinit var coilImage: ImageView
    private var userInputProvided: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_image_rating_input, container, false)
        ratingBar = rootView.findViewById(R.id.ratingBar)
        imageView = rootView.findViewById(R.id.image_product)
        coilImage = requireActivity().findViewById(R.id.coilImage)
        val data = myDataViewModel.reviewFormData

      //  coilImage.alpha = COIL_IMAGE_ALPHA_HIDDEN

        if (data != null) {
            val imageUrl = data.imageUrl
            imageView.load(imageUrl) {
                placeholder(R.drawable.loading)
            }
        } else {
            Log.e("Error", Constants.MESSAGE_API_ERROR)
        }

        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            userRating = rating
            userInputProvided = true
            collectUserData()
        }

        data?.let { response ->

            val textField = response.fields?.find { it.id == "rating" }
            if (textField != null) {

                val titleTextView = rootView.findViewById<TextView>(R.id.titleTextView)
                val descriptionTextView = rootView.findViewById<TextView>(R.id.description)
                titleTextView.text = textField.title.toString()
                descriptionTextView.text = response.name
            }
        }

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
      //  coilImage.alpha = COIL_IMAGE_ALPHA_VISIBLE
    }

    override fun collectUserData() {
        if (userRating != null) {
            sendData(DATA_KEY_USER_RATING, userRating.toString())
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
