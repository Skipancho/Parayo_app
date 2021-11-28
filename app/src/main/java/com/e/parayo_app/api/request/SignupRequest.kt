package com.e.parayo_app.api.request

import android.util.Patterns
import com.e.parayo_app.common.Prefs
import java.util.regex.Pattern

class SignupRequest (
    val email : String?,
    val password : String?,
    val name : String?,
    val fcmToken: String? = Prefs.fcmToken
    ){
    /*  유효성 검사
     *  빈 칸 확인, 이메일, 비밀번호, 이름 양식 확인
     */
    fun isNotValidEmail() =
        email.isNullOrBlank()
                || !Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isNotValidPassword()=
        password.isNullOrBlank() || password.length !in 8..20

    fun isNotValidName() =
        name.isNullOrBlank() || name.length !in 2..20
}