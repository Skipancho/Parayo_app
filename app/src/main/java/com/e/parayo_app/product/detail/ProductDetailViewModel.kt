package com.e.parayo_app.product.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.e.parayo_app.api.ParayoApi
import com.e.parayo_app.api.response.ApiResponse
import com.e.parayo_app.api.response.ProductResponse
import com.e.parayo_app.inquiry.ProductInquiryActivity
import com.e.parayo_app.product.ProductStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.codephobia.ankomvvm.databinding.addAll
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error
import java.lang.Exception
import java.text.NumberFormat

class ProductDetailViewModel(app : Application) : BaseViewModel(app) {

    var productId : Long? = null

    val productName = MutableLiveData("-")
    val description = MutableLiveData("")
    val price = MutableLiveData("-")
    val imageUrls : MutableLiveData<MutableList<String>> = MutableLiveData(mutableListOf())

    fun loadDetail(id : Long) = viewModelScope.launch(Dispatchers.Main){
        try {
            val response = getProduct(id)
            if (response.success && response.data != null){
                updateViewData(response.data)
            }else{
                toast(response.message ?: "알 수 없는 오류가 발생했습니다.")
            }
        }catch (e : Exception){
            toast(e.message ?: "알 수 없는 오류가 발생했습니다.")
        }
    }


    private suspend fun getProduct(id: Long) = try {
        ParayoApi.instance.getProduct(id)
    }catch (e:Exception){
        error("상품 정보를 가져오는 중 오류 발생",e)
        ApiResponse.error<ProductResponse>(
            "상품 정보를 가져오는 중 오류가 발생했습니다."
        )
    }

    private fun updateViewData(product : ProductResponse){
        val commaSeparatePrice =
            NumberFormat.getInstance().format(product.price)
        val soldOutString =
            if(ProductStatus.SOLD_OUT == product.status) "(품절)" else ""

        productId = product.id
        productName.value = product.name
        description.value = product.description
        price.value = "₩${commaSeparatePrice} $soldOutString"
        imageUrls.addAll(product.imagePaths)
    }


    fun openInquiryActivity(){
        startActivity<ProductInquiryActivity>{
            putExtra(ProductInquiryActivity.PRODUCT_ID, productId)
        }
    }

}