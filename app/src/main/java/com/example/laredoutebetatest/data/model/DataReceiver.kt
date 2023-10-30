package com.example.laredoutebetatest.data.model

interface DataReceiver {
    fun receiveData(reviewField: ReviewFormResponse.ReviewField)
}
