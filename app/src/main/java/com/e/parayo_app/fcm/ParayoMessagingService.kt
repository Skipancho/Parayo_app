package com.e.parayo_app.fcm

import com.e.parayo_app.api.ParayoApi
import com.e.parayo_app.common.Prefs
import com.google.firebase.messaging.FirebaseMessagingService
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.warn

class ParayoMessagingService : FirebaseMessagingService(), AnkoLogger {

    override fun onNewToken(fcmToken: String) {
        Prefs.fcmToken = fcmToken
        if(!Prefs.token.isNullOrEmpty() && fcmToken != null){
            runBlocking {
                try {
                    val response = ParayoApi.instance.updateFcmToken(fcmToken)
                    if (!response.success){
                        warn(response.message ?: "토큰 업데이트 실패")
                    }
                } catch (e : Exception){
                    error(e.message ?: "토큰 업데이트 실패", e)
                }
            }
        }
    }
}