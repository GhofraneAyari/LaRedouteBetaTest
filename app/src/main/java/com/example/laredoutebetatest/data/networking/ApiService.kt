package com.example.laredoutebetatest.data.networking

import com.example.laredoutebetatest.data.model.NameValue
import com.example.laredoutebetatest.data.model.ReviewFormResponse
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @GET("/request")
    fun getFormData(): Call<ReviewFormResponse>


    @POST("/response")
    fun postUserInputData(@Body data: List<NameValue>): Call<List<NameValue>>

}
