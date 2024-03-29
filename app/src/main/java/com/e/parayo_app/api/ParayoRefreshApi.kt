package com.e.parayo_app.api

import com.e.parayo_app.api.response.ApiResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface ParayoRefreshApi {

    @POST("/api/v1/refresh_token")
    suspend fun refreshToken(
        @Query("grant_type") grantType: String = "refresh_token"
    ):ApiResponse<String>

    companion object{
        val instance = ApiGenerator()
            .generateRefreshClient(ParayoRefreshApi::class.java)
    }
}