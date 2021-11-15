package com.e.parayo_app.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.parayo_app.common.Prefs
import com.e.parayo_app.product.ProductMainActivity
import com.e.parayo_app.signin.SigninActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_intro)
        val ui = IntroActivityUI()
        ui.setContentView(this)

        /*runBlocking {
            try {
                val response = ParayoApi.instance.hello()
                Log.d("IntroActivity", response.data!!)
            }catch (e : Exception){
                Log.d("IntroActivity","Hello Api 호출 오류",e)
            }
        }*/

        GlobalScope.launch {
            delay(1500)
            if(Prefs.token.isNullOrEmpty()){
                startActivity<SigninActivity>()
            } else{
                startActivity<ProductMainActivity>()
            }
            finish()
        }
    }
}