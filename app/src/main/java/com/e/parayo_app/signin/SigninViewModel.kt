package com.e.parayo_app.signin

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.e.parayo_app.api.ParayoApi
import com.e.parayo_app.api.request.SigninRequest
import com.e.parayo_app.api.response.ApiResponse
import com.e.parayo_app.api.response.SigninResponse
import com.e.parayo_app.common.Prefs
import com.e.parayo_app.product.ProductMainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error
import java.lang.Exception

class SigninViewModel(app : Application) : BaseViewModel(app) {
    val email = MutableLiveData("")
    val password = MutableLiveData("")

    suspend fun signin(){
        val request = SigninRequest(email.value, password.value)

        if(isNotValidSignin(request))
            return

        try {
            val response = requestSignin(request)
            onSigninResponse(response)
        } catch (e : Exception){
            error("sign-in error", e)
            toast("알 수 없는 오류가 발생했습니다.")
        }

    }

    private fun isNotValidSignin(request: SigninRequest)=
        when{
            request.isNotValidEmail() -> {
                toast("이메일 형식이 정확하지 않습니다.")
                true
            }
            request.isNotValidPassword() -> {
                toast("비밀번호를 다시 확인해주세요.")
                true
            }
            else -> false
        }

    private suspend fun requestSignin(request: SigninRequest)=
        withContext(Dispatchers.IO){
            ParayoApi.instance.signin(request)
        }

    private fun onSigninResponse(response : ApiResponse<SigninResponse>){
        if(response.success && response.data != null){
            Prefs.token = response.data.token
            Prefs.refreshToken = response.data.refreshToken
            Prefs.userName = response.data.userName
            Prefs.userId = response.data.userId

            toast("로그인되었습니다.")
            startActivityAndFinish<ProductMainActivity>()
        } else{
            toast(response.message ?: "알 수 없는 오류가 발생했습니다.")
        }
    }
}