package com.example.laredoutebetatest.data.model

interface DataCollecting {
    fun onUserDataCollected(data: List<NameValue>)
    fun sendUserData(data: List<NameValue>)
}