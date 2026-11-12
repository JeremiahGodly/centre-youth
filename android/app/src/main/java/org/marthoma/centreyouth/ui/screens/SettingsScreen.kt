package org.marthoma.centreyouth.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.marthoma.centreyouth.data.CentreRepository
import org.marthoma.centreyouth.data.model.AppThemeMode
import org.marthoma.centreyouth.data.model.QuizLanguage
import org.marthoma.centreyouth.notifications.DailyScriptureWorker
import org.marthoma.centreyouth.ui.theme.GoldAccent

@Composable
fun SettingsScreen(
    repository: CentreRepository,
    currentThemeMode: AppThemeMode,
    onThemeModeChanged: (AppThemeMode) -> Unit,
    currentQuizLanguage: QuizLanguage,
    onQuizLanguageChanged: (QuizLanguage) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var notificationsEnabled by remember { mutableStateOf(repository.isDailyNotificationsEnabled()) }
    var lastSyncedText by remember { mutableStateOf(repository.getLastSyncedFormatted()) }
    var isSyncingAll by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Appearance / Theme Selection
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Appearance & Theme",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "തീം തിരഞ്ഞെടുക്കുക (Dark / Light / System)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    val themeOptions = listOf(
                        Triple(AppThemeMode.SYSTEM, "📱", "Follow your phone's dark or light setting"),
                        Triple(AppThemeMode.LIGHT, "☀️", "Bright warm daylight liturgical interface"),
                        Triple(AppThemeMode.DARK, "🌙", "Sleek low-glare deep cosmic mode")
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        themeOptions.forEach { (mode, emoji, desc) ->
                            val isSelected = currentThemeMode == mode
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(14.dp))
                                    .clickable {
                                        repository.saveThemeMode(mode)
                                        onThemeModeChanged(mode)
                                    },
                                shape = RoundedCornerShape(14.dp),
                                color = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f)
                                else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                border = BorderStroke(
                                    width = if (isSelected) 1.5.dp else 1.dp,
                                    color = if (isSelected) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.outlineVariant
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = emoji, fontSize = 22.sp)
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "${mode.label} • ${mode.labelMl}",
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = desc,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    if (isSelected) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = "Selected",
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // 2. Daily Scripture Notifications
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = null,
                            tint = GoldAccent,
                            modifier = Modifier.size(26.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Daily 7:00 AM Scripture",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Receive daily verse & Sunday Gospel reminders",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Switch(
                        checked = notificationsEnabled,
                        onCheckedChange = { enabled ->
                            notificationsEnabled = enabled
                            repository.setDailyNotificationsEnabled(enabled)
                            if (enabled) {
                                DailyScriptureWorker.scheduleDailyScripture(context)
                                Toast.makeText(context, "Daily 7:00 AM reminders enabled", Toast.LENGTH_SHORT).show()
                            } else {
                                DailyScriptureWorker.cancelDailyScripture(context)
                                Toast.makeText(context, "Daily reminders paused", Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }
            }
        }

        // 3. Bible Quiz Language Preference
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Bible Quiz Language",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Default language shown in Bible Quiz Arena",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        QuizLanguage.entries.forEach { lang ->
                            val selected = currentQuizLanguage == lang
                            FilterChip(
                                selected = selected,
                                onClick = {
                                    repository.saveQuizLanguage(lang)
                                    onQuizLanguageChanged(lang)
                                },
                                label = {
                                    Text(
                                        text = when (lang) {
                                            QuizLanguage.EN -> "English (EN)"
                                            QuizLanguage.ML -> "മലയാളം (ML)"
                                            QuizLanguage.BOTH -> "Both"
                                        },
                                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
                                    )
                                },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }

        // 4. Google Sheet Sync Management
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, GoldAccent.copy(alpha = 0.35f), RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Cloud Data & Sheet Sync",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Last synced: $lastSyncedText",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            if (!isSyncingAll) {
                                scope.launch {
                                    isSyncingAll = true
                                    repository.syncProgramsFromGoogleSheet()
                                    repository.syncLeadersFromGoogleSheet()
                                    repository.syncParishesFromGoogleSheet()
                                    repository.syncPrayerWallFromGoogleSheet()
                                    repository.syncGalleryFromGoogleSheet()
                                    repository.syncLeaderboardFromGoogleSheet()
                                    lastSyncedText = repository.getLastSyncedFormatted()
                                    isSyncingAll = false
                                    Toast.makeText(context, "All Centre data synced!", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        if (isSyncingAll) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(18.dp),
                                color = MaterialTheme.colorScheme.onPrimary,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Sync All Cloud Tabs Now", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
