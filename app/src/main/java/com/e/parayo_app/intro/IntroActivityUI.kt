package com.e.parayo_app.intro

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import com.e.parayo_app.R
import org.jetbrains.anko.*

class IntroActivityUI : AnkoComponent<IntroActivity>{

    override fun createView(ui: AnkoContext<IntroActivity>): View {
        return ui.relativeLayout {
            gravity = Gravity.CENTER

            textView("PARAYO"){
                textSize = 24f
                textColorResource = R.color.purple_200
                typeface = Typeface.DEFAULT_BOLD
            }
        }
    }

}