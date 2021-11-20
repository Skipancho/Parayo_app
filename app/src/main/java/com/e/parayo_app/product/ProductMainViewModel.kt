package com.e.parayo_app.product

import android.app.Application
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

class ProductMainViewModel(app:Application) : BaseViewModel(app) {
    fun openRegistrationActivity(){
        //TODO. 상품 등록 UI 준비후 실행
        toast("open RegistrationActivity ")
    }
}