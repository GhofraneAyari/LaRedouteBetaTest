package com.example.laredoutebetatest.data.model.networking

import com.example.laredoutebetatest.data.model.ReviewFormResponse
import retrofit2.http.GET
import retrofit2.Call

interface ApiService {

    @GET("/request")
    fun getRequest(): Call<ReviewFormResponse>
}