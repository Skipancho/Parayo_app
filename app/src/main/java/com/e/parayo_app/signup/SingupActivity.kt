package com.e.parayo_app.signup

import android.os.Bundle
import android.os.PersistableBundle
import com.e.parayo_app.R
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

class SingupActivity : BaseActivity<SignupViewModel>() {
    override val viewModelType = SignupViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SignupActivityUI(getViewModel()).setContentView(this)
    }
}