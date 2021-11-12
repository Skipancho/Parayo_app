package com.e.parayo_app.signup

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.e.parayo_app.api.ParayoApi
import com.e.parayo_app.api.request.SignupRequest
import com.e.parayo_app.api.response.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error


class SignupViewModel(app: Application): BaseViewModel(app) {
    // 데이터 바인딩
    val email = MutableLiveData("")
    val name = MutableLiveData("")
    val password = MutableLiveData("")

    // 회원가입 함수
    // requestSignup() 에서 코루틴이 시작되므로 suspend 함수로 선언
    suspend fun signup(){
        // request 생성
        val request = SignupRequest(email.value,password.value,name.value)
        // 유효성 검사 결과 확인
        if(isNotValidSignup(request))
            return

        try {
            // 요청에 대한 응답 받기
            val response = requestSignup(request)
            // 응답에 대한 action
            onSignupResponse(response)
        }catch (e:Exception){
            error("sign-up error",e)
            toast("오류가 발생했습니다.")
        }
    }
    // 유효성 검사
    private fun isNotValidSignup(signupRequest: SignupRequest) =
        when{
            signupRequest.isNotValidEmail() -> {
                toast("이메일 형식이 정확하지 않습니다.")
                true
            }
            signupRequest.isNotValidPassword() -> {
                toast("비밀번호는 8자 이상 20자 이하로 입력해주세요.")
                true
            }
            signupRequest.isNotValidName() -> {
                toast("이름은 2자 이상 20자 이하로 입력해주세요.")
                true
            }
            else -> false
        }


    /* api 호출, request를 보내고 response를 받아온다.
     * withContext 코루틴 빌더를 이용해 비동기로 네트워크 요청을 실행
     * 코루틴이 시작되므로 suspend 함수로 선언
     */
    private suspend fun requestSignup(request: SignupRequest) =
        withContext(Dispatchers.IO){
            ParayoApi.instance.signup(request)
        }
    /* 응답에 대한 적절한 action을 실행하는 함수
    */
    private fun onSignupResponse(response: ApiResponse<Void>){
        if(response.success){
            toast("회원 가입이 완료되었습니다. 로그인 후 이용해주세요.")
            finishActivity()
        }else{
            toast(response.message ?: "알 수 없는 오류가 발생했습니다.")
        }
    }

}