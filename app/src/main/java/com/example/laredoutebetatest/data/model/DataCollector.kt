package com.example.laredoutebetatest.data.model

class DataCollector(private val listener: DataCollecting) {

    fun collectAndSendData(name: String, value: String) {
        val nameValue = NameValue(name, value)
        listener.onUserDataCollected(listOf(nameValue))
    }
}
