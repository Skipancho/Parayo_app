package com.e.parayo_app.signin

import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.e.parayo_app.R
import com.e.parayo_app.signup.SignupActivity
import net.codephobia.ankomvvm.databinding.bindString
import org.jetbrains.anko.*
import org.jetbrains.anko.design.textInputEditText
import org.jetbrains.anko.design.textInputLayout
import org.jetbrains.anko.sdk27.coroutines.onClick

class SigninActivityUI(
    private val viewModel: SigninViewModel
):AnkoComponent<SigninActivity> {
    override fun createView(ui: AnkoContext<SigninActivity>) =
        ui.linearLayout{
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_VERTICAL
            padding = dip(20)


            textView("Parayo"){
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                textSize = 24f
                typeface = Typeface.DEFAULT_BOLD
                textColorResource = R.color.black
            }.lparams(width = matchParent){
                bottomMargin = dip(50)
            }

            textInputLayout{
                textInputEditText{
                    hint = "Email"
                    setSingleLine()
                    bindString(ui.owner, viewModel.email)
                }
            }.lparams(width = matchParent){
                marginStart = dip(20)
                marginEnd = dip(20)
                bottomMargin = dip(20)
            }

            textInputLayout{
                textInputEditText{
                    hint = "Password"
                    setSingleLine()
                    inputType =  InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    bindString(ui.owner, viewModel.password)
                }
            }.lparams(width = matchParent){
                marginStart = dip(20)
                marginEnd = dip(20)
                bottomMargin = dip(20)
            }

            button("로그인"){
                onClick { viewModel.signin() }
            }.lparams(width = matchParent)

            button("회원가입"){
                backgroundColor = Color.TRANSPARENT
                textColorResource = R.color.purple_200
                onClick { ui.startActivity<SignupActivity>() }
            }
        }

}