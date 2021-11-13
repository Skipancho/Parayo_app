package com.e.parayo_app.signup

import android.graphics.Typeface
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.e.parayo_app.R
import net.codephobia.ankomvvm.databinding.bindString
import org.jetbrains.anko.*
import org.jetbrains.anko.design.textInputEditText
import org.jetbrains.anko.design.textInputLayout
import org.jetbrains.anko.sdk27.coroutines.onClick

class SignupActivityUI(
    private  val viewModel: SignupViewModel
) : AnkoComponent<SingupActivity>{
    override fun createView(ui: AnkoContext<SingupActivity>)=
        ui.linearLayout {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            padding = dip(20)

            textView("회원 가입"){
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                textSize = 20f
                typeface = Typeface.DEFAULT_BOLD
                textColorResource = R.color.black
            }.lparams(width = matchParent){
                bottomMargin = dip(20)
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
                    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_PASSWORD
                    bindString(ui.owner, viewModel.password)
                }
            }.lparams(width = matchParent){
                marginStart = dip(20)
                marginEnd = dip(20)
                bottomMargin = dip(20)
            }

            button("회원 가입"){
                onClick { viewModel.signup() }
            }.lparams(width = matchParent){
                marginStart = dip(20)
                marginEnd = dip(20)
            }
        }
}