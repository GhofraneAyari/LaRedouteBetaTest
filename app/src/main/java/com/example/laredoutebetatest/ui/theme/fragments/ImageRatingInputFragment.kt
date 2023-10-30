package com.example.laredoutebetatest.ui.theme.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.laredoutebetatest.R
import com.example.laredoutebetatest.data.model.DataReceiver
import com.example.laredoutebetatest.data.model.ReviewFormResponse

class ImageRatingInputFragment : Fragment() {
    private var userRating: Float? = null
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_image_rating_input, container, false)

        val ratingBar = rootView.findViewById<RatingBar>(R.id.ratingBar)
//        val nextButton = rootView.findViewById<Button>(R.id.nextButton)

        // Set a click listener for the "Next" button
//        nextButton.setOnClickListener {
//            userRating = ratingBar.rating
//
//            // Handle the user's rating input (userRating) as needed, e.g., save it in a ViewModel
//
//            // Proceed to the next step or perform other actions
//
//            val selectInputFragment = SelectInputFragment()
//            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.fragmentGroup, selectInputFragment)
//            fragmentTransaction.addToBackStack(null) // Add to back stack for back navigation
//            fragmentTransaction.commit()
//        }

        return rootView
    }

//    override fun receiveData(reviewField: ReviewFormResponse.ReviewField) {
//        val titleTextView = rootView.findViewById<TextView>(R.id.titleTextView)
//
//
//        titleTextView.text = reviewField.title
//
//
//
//    }
}
