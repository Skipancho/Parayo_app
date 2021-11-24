package com.e.parayo_app.product.list

import android.app.Application
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

class ProductListViewModel(app : Application) : BaseViewModel(app) {
    fun onClickItem(id : Long?){
        toast("click $id")
    }
}