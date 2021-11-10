package com.e.parayo_app.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.parayo_app.R
import org.jetbrains.anko.setContentView

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_intro)
        val ui = IntroActivityUI()
        ui.setContentView(this)
    }
}