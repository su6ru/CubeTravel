package com.ci.v1_ci_view.ui.view.application

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

open class CIApplication: Application() {
    companion object{
        final val CHANNEL_ID = "CHANNEL_ID"
        final val CHANNEL_NAME = "CHANNEL_NAME"
    }

    /** 顯示通知欄 */
    fun showNotification(title: String, content: String, icon: Int){
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notificationBuilder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        } else {
            NotificationCompat.Builder(applicationContext)
        }
        notificationBuilder
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(icon)
            .setPriority(Notification.PRIORITY_HIGH)
            .setAutoCancel(false)

        notificationManager.notify(9999,notificationBuilder.build())
    }

}