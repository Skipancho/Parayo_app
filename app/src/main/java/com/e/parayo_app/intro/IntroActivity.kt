package com.e.parayo_app.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.e.parayo_app.R
import com.e.parayo_app.api.ParayoApi
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.setContentView

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_intro)
        val ui = IntroActivityUI()
        ui.setContentView(this)

        runBlocking {
            try {
                val response = ParayoApi.instance.hello()
                Log.d("IntroActivity", response.data!!)
            }catch (e : Exception){
                Log.d("IntroActivity","Hello Api 호출 오류",e)
            }
        }
    }
}