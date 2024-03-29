package com.e.parayo_app.product

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.e.parayo_app.R
import com.e.parayo_app.product.list.ProductListPagerAdapter
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

class ProductMainActivity : BaseActivity<ProductMainViewModel>() {

    override val viewModelType = ProductMainViewModel::class
    private val ui by lazy { ProductMainUI(getViewModel()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)
        ui.viewpager.adapter =
            ProductListPagerAdapter(supportFragmentManager)
        ui.tabLayout.setupWithViewPager(ui.viewpager)
        setupDrawerListener()
    }

    private fun setupDrawerListener(){
        val toggle = ActionBarDrawerToggle(
            this,
            ui.drawerLayout,
            ui.toolBar,
            R.string.open_drawer_description,
            R.string.close_drawer_description
        )
        ui.drawerLayout.addDrawerListener(toggle)

        toggle.syncState()
    }
}