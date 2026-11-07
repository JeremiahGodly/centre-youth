package org.marthoma.centreyouth.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import org.marthoma.centreyouth.MainActivity
import org.marthoma.centreyouth.R

object NotificationHelper {

    const val CHANNEL_DAILY_SCRIPTURE = "channel_daily_scripture"
    const val CHANNEL_CENTRE_EVENTS = "channel_centre_events"
    const val NOTIFICATION_ID_SCRIPTURE = 1001
    const val NOTIFICATION_ID_EVENT = 2001

    fun createNotificationChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.notification_channel_name)
            val descriptionText = context.getString(R.string.notification_channel_desc)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(CHANNEL_DAILY_SCRIPTURE, name, importance).apply {
                description = descriptionText
                enableVibration(true)
            }

            val eventChannel = NotificationChannel(
                CHANNEL_CENTRE_EVENTS,
                "Centre Programs & Announcements",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for new events, camps, kalamela and schedules"
                enableVibration(true)
            }

            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            notificationManager.createNotificationChannel(eventChannel)
        }
    }

    fun showDailyScriptureNotification(
        context: Context,
        title: String,
        message: String,
        verseRef: String
    ) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("navigate_to", "lectionary")
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, title)
            putExtra(Intent.EXTRA_TEXT, "$title\n\"$message\"\n— $verseRef\n(Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam)")
        }
        val sharePendingIntent = PendingIntent.getActivity(
            context,
            1,
            Intent.createChooser(shareIntent, "Share Daily Scripture"),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_DAILY_SCRIPTURE)
            .setSmallIcon(R.drawable.ic_stat_scripture)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("\"$message\"\n\n— $verseRef\nLighted to Lighten")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_stat_scripture, "Share", sharePendingIntent)
            .build()

        try {
            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID_SCRIPTURE, notification)
        } catch (e: SecurityException) {
            // Android 13+ permission not granted
        }
    }

    fun showNewEventNotification(
        context: Context,
        eventTitle: String,
        eventDate: String,
        eventVenue: String
    ) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            102,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val body = if (eventDate.isNotBlank() || eventVenue.isNotBlank()) {
            "$eventDate • $eventVenue\nTap to view specifications and poster."
        } else {
            "Tap to view program specifications, schedule and poster."
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_CENTRE_EVENTS)
            .setSmallIcon(R.drawable.ic_stat_scripture)
            .setContentTitle("📢 New Event: $eventTitle")
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText("📢 $eventTitle\n\n$body"))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        try {
            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID_EVENT, notification)
        } catch (e: SecurityException) {
            // Android 13+ permission not granted
        }
    }
}
