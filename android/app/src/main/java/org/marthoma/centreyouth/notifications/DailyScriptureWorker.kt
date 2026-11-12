package org.marthoma.centreyouth.notifications

import android.content.Context
import androidx.work.*
import org.marthoma.centreyouth.data.CentreRepository
import java.util.Calendar
import java.util.concurrent.TimeUnit

class DailyScriptureWorker(
    private val appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val repo = CentreRepository(appContext)
            if (!repo.isDailyNotificationsEnabled()) {
                return Result.success()
            }
            val upcoming = repo.getUpcomingSunday()
            val centreInfo = repo.getCentreInfo()

            val title = upcoming?.titleEn ?: "Daily Scripture Reflection"
            val verse = if (upcoming != null && upcoming.gospelReading.isNotBlank()) {
                "Holy Gospel: ${upcoming.gospelReading}\n${upcoming.themeEn}"
            } else {
                centreInfo.themeQuoteEn
            }
            val ref = if (upcoming != null && upcoming.gospelReading.isNotBlank()) upcoming.gospelReading else centreInfo.themeVerse

            NotificationHelper.showDailyScriptureNotification(
                context = appContext,
                title = title,
                message = verse,
                verseRef = ref
            )

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }

    companion object {
        private const val WORK_NAME = "DailyScriptureWork"

        fun scheduleDailyScripture(context: Context) {
            val currentDate = Calendar.getInstance()
            val dueDate = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 7)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                if (before(currentDate)) {
                    add(Calendar.HOUR_OF_DAY, 24)
                }
            }

            val initialDelay = dueDate.timeInMillis - currentDate.timeInMillis

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .setRequiresBatteryNotLow(true)
                .build()

            val dailyWorkRequest = PeriodicWorkRequestBuilder<DailyScriptureWorker>(24, TimeUnit.HOURS)
                .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                dailyWorkRequest
            )
        }

        fun cancelDailyScripture(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
        }
    }
}
