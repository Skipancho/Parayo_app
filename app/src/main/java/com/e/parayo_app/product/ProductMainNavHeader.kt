package com.e.parayo_app.product

import android.graphics.Typeface
import android.view.View
import com.e.parayo_app.R
import com.e.parayo_app.common.Prefs
import com.e.parayo_app.view.borderBottom
import org.jetbrains.anko.*

class ProductMainNavHeader : AnkoComponent<View> {
    override fun createView(ui: AnkoContext<View>)=
        ui.verticalLayout {
            padding = dip(20)
            background = borderBottom(width = dip(1))

            imageView(R.drawable.ic_baseline_account_circle_24)
                .lparams(dip(54),dip(54))

            textView(Prefs.userName){
                topPadding = dip(8)
                textSize = 20f
                typeface = Typeface.DEFAULT_BOLD
            }
        }
}