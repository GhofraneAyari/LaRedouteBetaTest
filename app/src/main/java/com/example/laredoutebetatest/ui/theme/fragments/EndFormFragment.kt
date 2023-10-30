package com.example.laredoutebetatest.ui.theme.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.laredoutebetatest.R
import com.example.laredoutebetatest.data.model.DataCollecting
import com.example.laredoutebetatest.data.model.NameValue

class EndFormFragment : Fragment(), DataCollecting {
    private var collectedUserData: List<NameValue> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_end_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sendButton = view.findViewById<Button>(R.id.sendAvis)

        sendButton.setOnClickListener {
            // Send a POST request with the collected data
            sendUserData(collectedUserData)

            // Close the app or perform any other actions
            activity?.finish()
        }
    }

    override fun onUserDataCollected(data: List<NameValue>) {
        // Handle the collected user data received from other fragments
        // You can store the data for sending when the Send button is clicked
        collectedUserData = data
    }

    override fun sendUserData(userData: List<NameValue>) {
        // Implement your code to send the user data to the server here
    }
}
