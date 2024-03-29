package com.e.parayo_app.api

import com.e.parayo_app.api.request.InquiryRequest
import com.e.parayo_app.api.request.ProductRegistrationRequest
import com.e.parayo_app.api.request.SigninRequest
import com.e.parayo_app.api.request.SignupRequest
import com.e.parayo_app.api.response.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

//메인 Api
interface ParayoApi {

    @GET("/api/v1/hello")
    suspend fun hello(): ApiResponse<String>

    /*
    *   param : SignupRequest를 인자로 받아 해당 주소로 post
    *   return : ApiResponse 로 응답을 받는다.
    */
    @POST("/api/v1/users")
    suspend fun signup(@Body signupRequest: SignupRequest):ApiResponse<Void>

    /*
    *   param : SigninRequest를 인자로 받아 해당 주소로 post
    *   return : ApiResponse로 SigninResponse형태의 응답을 받는다.
    */
    @POST("/api/v1/signin")
    suspend fun signin(@Body signinRequest: SigninRequest):ApiResponse<SigninResponse>

    @Multipart
    @POST("/api/v1/product_images")
    suspend fun uploadProductImages(
        @Part images: MultipartBody.Part
    ): ApiResponse<ProductImageUploadResponse>

    @POST("/api/v1/products")
    suspend fun registerProduct(
        @Body request: ProductRegistrationRequest
    ): ApiResponse<Response<Void>>

    @GET("/api/v1/products")
    suspend fun getProducts(
        @Query("productId") productId : Long,
        @Query("categoryId") categoryId : Int?,
        @Query("direction") direction : String,
        @Query("keyword") keyword : String? = null
    ): ApiResponse<List<ProductListItemResponse>>

    @GET("/api/v1/products/{id}")
    suspend fun getProduct(
        @Path("id") id : Long
    ):ApiResponse<ProductResponse>

    @PUT("/api/v1/users/fcm-token")
    suspend fun updateFcmToken(
        fcmToken : String
    ):ApiResponse<Response<Void>>

    @GET("/api/v1/inquiries")
    suspend fun getInquiries(
        @Query("inquiryId") inquiryId : Long,
        @Query("productId") productId: Long? = null,
        @Query("requestUserId") requestUserId : Long? = null,
        @Query("productOwnerId") productOwnerId : Long? = null,
        @Query("direction") direction: String //prev, next
    ): ApiResponse<List<InquiryResponse>>

    @POST("/api/v1/inquiries")
    suspend fun registerInquiry(
        @Body request : InquiryRequest
    ): ApiResponse<Response<Void>>

    companion object{
        val instance = ApiGenerator()
            .generate(ParayoApi::class.java)
    }
}