package com.e.parayo_app.inquiry.myinquiry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView
import kotlin.reflect.KClass

class MyInquiryActivity : BaseActivity<MyInquiryViewModel>() {

    override val viewModelType = MyInquiryViewModel::class

    private val ui by lazy { MyInquiryUI() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ui.setContentView(this)
        ui.viewpager.adapter = MyInquiryPagerAdapter(supportFragmentManager)
        ui.tabLayout.setupWithViewPager(ui.viewpager)
    }
}