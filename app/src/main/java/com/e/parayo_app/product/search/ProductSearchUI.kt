package com.e.parayo_app.product.search

import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.parayo_app.product.list.ProductListPagedAdapter
import net.codephobia.ankomvvm.databinding.bindPagedList
import net.codephobia.ankomvvm.databinding.bindVisibility
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ProductSearchUI(
    private val viewModel: ProductSearchViewModel
) : AnkoComponent<ProductSearchActivity>{
    override fun createView(ui: AnkoContext<ProductSearchActivity>) =
        ui.verticalLayout {
            recyclerView{
                layoutManager = LinearLayoutManager(ui.ctx)
                adapter = ProductListPagedAdapter(viewModel)
                lparams(matchParent, matchParent)
                bindVisibility(ui.owner, viewModel.products){
                    it.isNotEmpty()
                }
                bindPagedList(
                    ui.owner,
                    ProductListPagedAdapter(viewModel),
                    viewModel.products
                )
            }
            textView("검색된 상품이 없습니다."){
                gravity = Gravity.CENTER
                bindVisibility(ui.owner, viewModel.products){
                    it.isEmpty()
                }
            }.lparams(wrapContent, matchParent){
                gravity = Gravity.CENTER
            }
        }

}