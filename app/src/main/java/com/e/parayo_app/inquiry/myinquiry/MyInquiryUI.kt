package com.e.parayo_app.inquiry.myinquiry

import android.view.View
import androidx.viewpager.widget.ViewPager
import com.e.parayo_app.R
import com.e.parayo_app.view.borderBottom
import com.google.android.material.tabs.TabLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.viewPager

class MyInquiryUI : AnkoComponent<MyInquiryActivity> {

    lateinit var tabLayout: TabLayout
    lateinit var viewpager: ViewPager

    override fun createView(ui: AnkoContext<MyInquiryActivity>) =
        ui.verticalLayout {
            tabLayout = themedTabLayout(
                R.style.Widget_MaterialComponents_TabLayout
            ){
                bottomPadding = dip(1)
                tabGravity = TabLayout.GRAVITY_FILL
                background = borderBottom(width = dip(1))
            }.lparams(matchParent, wrapContent){
                weight = 0f
            }

            viewpager = viewPager{
                id = View.generateViewId()
            }.lparams(matchParent, matchParent){
                weight = 1f
            }
        }
}