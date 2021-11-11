package com.e.parayo_app.api

import com.e.parayo_app.api.response.ApiResponse
import retrofit2.http.GET

interface ParayoApi {

    @GET("/api/v1/hello")
    suspend fun hello(): ApiResponse<String>

    companion object{
        val instance = ApiGenerator()
            .generate(ParayoApi::class.java)
    }
}