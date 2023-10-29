package com.example.laredoutebetatest.ui.theme.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.laredoutebetatest.R

class BooleanInputFragment : Fragment() {
    private var isRecommended: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.boolean_input, container, false)

        val yesNoCheckBox = rootView.findViewById<CheckBox>(R.id.NoCheckBox)
        val nextButton = rootView.findViewById<Button>(R.id.nextButton)

        // Set a click listener for the "Next" button
        nextButton.setOnClickListener {
            isRecommended = yesNoCheckBox.isChecked
            // Handle the user's selection (isRecommended) as needed, e.g., save it in a ViewModel

            // Proceed to the next step or perform other actions
        }

        return rootView
    }
}
