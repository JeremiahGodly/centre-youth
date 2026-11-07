package org.marthoma.centreyouth

import android.app.Application
import org.marthoma.centreyouth.notifications.DailyScriptureWorker
import org.marthoma.centreyouth.notifications.NotificationHelper

class CentreYouthApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Create Android Notification Channels
        NotificationHelper.createNotificationChannels(this)

        // Schedule Daily Scripture Notification (7:00 AM)
        DailyScriptureWorker.scheduleDailyScripture(this)
    }
}
