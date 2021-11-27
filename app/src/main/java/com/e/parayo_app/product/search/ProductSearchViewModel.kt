package com.e.parayo_app.product.search

import android.app.Application
import android.content.Intent
import androidx.paging.DataSource
import com.e.parayo_app.api.response.ProductListItemResponse
import com.e.parayo_app.product.detail.ProductDetailActivity
import com.e.parayo_app.product.list.ProductListItemDataSource
import com.e.parayo_app.product.list.ProductListPagedAdapter
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

class ProductSearchViewModel(app : Application)
    :BaseViewModel(app), ProductListPagedAdapter.ProductLiveDataBuilder, ProductListPagedAdapter.OnItemClickListener{

    var keyword : String? = null
    val products = buildPagedList()

    override fun createDataSource() =
        ProductListItemDataSource(null, keyword)

    override fun onClickProduct(productId: Long?) {
        startActivity<ProductDetailActivity>{
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra(ProductDetailActivity.PRODUCT_ID, productId)
        }
    }
}