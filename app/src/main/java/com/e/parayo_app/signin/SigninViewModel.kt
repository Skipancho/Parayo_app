package com.e.parayo_app.signin

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.e.parayo_app.api.ParayoApi
import com.e.parayo_app.api.request.SigninRequest
import com.e.parayo_app.api.response.ApiResponse
import com.e.parayo_app.api.response.SigninResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

class SigninViewModel(app : Application) : BaseViewModel(app) {
    val email = MutableLiveData("")
    val password = MutableLiveData("")

    suspend fun signin(){

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
        if(response.success){
            toast("로그인되었습니다.")
        } else{
            toast(response.message ?: "알 수 없는 오류가 발생했습니다.")
        }
    }
}