package com.yogi.portfolio.portfolio.Utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.yogi.portfolio.R

object CommonNotification {

    private const val CHANNEL_ID = "general_channel"
    private const val CHANNEL_NAME = "General Notifications"

    fun show(context: Context, title: String, message: String, notificationId: Int = System.currentTimeMillis().toInt()
    ) {

        val largeIcon = BitmapFactory.decodeResource(
            context.resources,
            R.mipmap.ic_notification // use launcher icon for test
        )

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create channel (Android 8+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_app) // change icon
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(notificationId, notification)
    }
}