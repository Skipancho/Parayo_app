package com.e.parayo_app.api

import com.e.parayo_app.api.request.SignupRequest
import com.e.parayo_app.api.response.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ParayoApi {

    @GET("/api/v1/hello")
    suspend fun hello(): ApiResponse<String>

    @POST("/api/v1/users")
    suspend fun signup(@Body signupRequest: SignupRequest):ApiResponse<Void>


    companion object{
        val instance = ApiGenerator()
            .generate(ParayoApi::class.java)
    }
}