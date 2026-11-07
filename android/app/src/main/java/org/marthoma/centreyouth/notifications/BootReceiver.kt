package org.marthoma.centreyouth.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null &&
            (intent?.action == Intent.ACTION_BOOT_COMPLETED ||
             intent?.action == Intent.ACTION_MY_PACKAGE_REPLACED)) {
            DailyScriptureWorker.scheduleDailyScripture(context)
        }
    }
}
