package com.example.laredoutebetatest.ui.theme.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.laredoutebetatest.R

class IntegerInputFragment : Fragment() {
    private var userRating: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_integer_input, container, false)

        val integerEditText = rootView.findViewById<EditText>(R.id.integerEditText)
//        val nextButton = rootView.findViewById<Button>(R.id.nextButton)

        // Set a click listener for the "Next" button
//        nextButton.setOnClickListener {
            val ratingText = integerEditText.text.toString().trim()
            if (ratingText.isNotEmpty()) {
                userRating = ratingText.toInt()
                // Handle the user's integer input (userRating) as needed, e.g., save it in a ViewModel

                // Proceed to the next step or perform other actions
//                val booleanInputFragment = BooleanInputFragment()
//                val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
//                fragmentTransaction.replace(R.id.fragmentGroup, booleanInputFragment)
//                fragmentTransaction.addToBackStack(null) // Add to back stack for back navigation
//                fragmentTransaction.commit()

            }
//        }

        return rootView
    }
}

