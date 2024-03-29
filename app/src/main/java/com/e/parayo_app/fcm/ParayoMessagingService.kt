package com.e.parayo_app.fcm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.e.parayo_app.R
import com.e.parayo_app.api.ParayoApi
import com.e.parayo_app.common.Prefs
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jetbrains.anko.*

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

    override fun onMessageReceived(message: RemoteMessage) {
        message?.data?.let { data ->
            debug(data)
            createNotificationChannelIfNeeded()

            val builder = NotificationCompat
                .Builder(this, "channel.parayo_app.default")
                .setContentTitle(data["title"])
                .setContentText(data["content"])
                .setSmallIcon(R.drawable.logo)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            with(NotificationManagerCompat.from(this)){
                notify(NotificationId.generate(),builder.build())
            }
        }
    }

    private fun createNotificationChannelIfNeeded(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val  channel = NotificationChannel(
                "channel.parayo_app.default",
                "기본 알림",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "기본 알림"
            notificationManager.createNotificationChannel(channel)
        }
    }
}