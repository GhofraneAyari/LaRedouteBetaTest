package com.example.laredoutebetatest.ui.theme.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.laredoutebetatest.R


class ReviewQuestionFragment : Fragment() {
    private var questionText: String? = null
    private var imageResourceId: Int = R.drawable.dummy_image // Default image

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // Retrieve question data based on the question ID or other identifier
            questionText = it.getString(QUESTION_TEXT_KEY)
            imageResourceId = it.getInt(QUESTION_IMAGE_KEY, R.drawable.dummy_image)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_review_question, container, false)

        val questionTextView = view.findViewById<TextView>(R.id.questionTextView)
        val questionImageView = view.findViewById<ImageView>(R.id.questionImageView)
        val validateButton = view.findViewById<Button>(R.id.Validate)

        questionTextView.text = questionText
        questionImageView.setImageResource(imageResourceId)

        // Handle validation button click

        return view
    }

    companion object {
        private const val QUESTION_TEXT_KEY = "question_text"
        private const val QUESTION_IMAGE_KEY = "question_image"

        fun newInstance(questionText: String, imageResourceId: Int): ReviewQuestionFragment {
            val fragment = ReviewQuestionFragment()
            val args = Bundle()
            args.putString(QUESTION_TEXT_KEY, questionText)
            args.putInt(QUESTION_IMAGE_KEY, imageResourceId)
            fragment.arguments = args
            return fragment
        }
    }
}
