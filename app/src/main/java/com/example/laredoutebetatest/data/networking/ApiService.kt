package com.example.laredoutebetatest.data.networking

import com.example.laredoutebetatest.data.model.NameValue
import com.example.laredoutebetatest.data.model.ReviewFormResponse
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @GET("/request")
    fun getRequest(): Call<ReviewFormResponse>


    @POST("/post_data")
    fun postRequest(@Body data: List<NameValue>): Call<List<NameValue>>

}
