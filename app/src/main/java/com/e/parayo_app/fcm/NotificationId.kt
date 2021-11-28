package com.e.parayo_app.fcm

import com.e.parayo_app.common.Prefs
import java.util.concurrent.Semaphore

class NotificationId {
    companion object{
        private val lock = Semaphore(1)
        private var id = Prefs.notificationId

        fun generate() : Int{
            lock.acquire()
            val next = id +1
            id = next
            Prefs.notificationId = next
            lock.release()
            return next
        }
    }
}