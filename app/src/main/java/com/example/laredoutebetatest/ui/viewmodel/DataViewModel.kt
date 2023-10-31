package com.example.laredoutebetatest.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.laredoutebetatest.data.model.ReviewFormResponse

class DataViewModel : ViewModel() {
    var reviewFormData: ReviewFormResponse? = null

    fun isIntegerInputField(fieldId: String): Boolean {
        return reviewFormData?.fields?.any { it.id == fieldId && it.type == "IntegerInput" } ?: false
    }

    fun getReviewFormField(fieldId: String): ReviewFormResponse.ReviewField? {
        return reviewFormData?.fields?.find { it.id == fieldId }
}
}