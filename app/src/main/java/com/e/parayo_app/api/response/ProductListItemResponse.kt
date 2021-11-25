package com.e.parayo_app.api.response

data class ProductListItemResponse(
    val id : Long,
    val name : String,
    val description : String,
    val price : Int,
    val status : String,
    val sellerId : Long,
    val imagePaths : List<String>
)