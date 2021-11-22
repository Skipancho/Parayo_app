package com.e.parayo_app.product

import android.app.Application
import android.content.Intent
import com.e.parayo_app.product.registration.ProductRegistrationActivity
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

class ProductMainViewModel(app:Application) : BaseViewModel(app) {
    fun openRegistrationActivity(){
        //TODO. 상품 등록 UI 준비후 실행
        startActivity<ProductRegistrationActivity>{
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
    }
}