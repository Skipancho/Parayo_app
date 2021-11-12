package com.e.parayo_app.signup

import android.os.Bundle
import com.e.parayo_app.R
import net.codephobia.ankomvvm.components.BaseActivity

class SingupActivity : BaseActivity<SignupViewModel>() {
    override val viewModelType = SignupViewModel::class
}