package org.marthoma.centreyouth.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.marthoma.centreyouth.R
import org.marthoma.centreyouth.data.CentreRepository
import org.marthoma.centreyouth.data.model.LectionaryEntry
import org.marthoma.centreyouth.ui.theme.GoldAccent
import org.marthoma.centreyouth.ui.theme.GoldDark
import org.marthoma.centreyouth.ui.theme.GoldLight
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun LectionaryScreen(
    repository: CentreRepository,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showOnlyUpcoming by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }

    val upcomingSunday = remember { repository.getUpcomingSunday() }
    val allEntries = remember { repository.getLectionary() }
    val upcomingEntries = remember { repository.getUpcomingLectionaries() }

    val baseEntries = if (showOnlyUpcoming) upcomingEntries else allEntries

    val filteredEntries = remember(searchQuery, baseEntries) {
        if (searchQuery.isBlank()) {
            baseEntries
        } else {
            val q = searchQuery.lowercase().trim()
            baseEntries.filter {
                it.titleEn.lowercase().contains(q) ||
                it.titleMl.lowercase().contains(q) ||
                it.gospelReading.lowercase().contains(q) ||
                it.epistleReading.lowercase().contains(q) ||
                it.firstLesson.lowercase().contains(q) ||
                it.secondLesson.lowercase().contains(q) ||
                it.seasonEn.lowercase().contains(q) ||
                it.themeEn.lowercase().contains(q) ||
                it.date.lowercase().contains(q)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(14.dp))

        // Search Bar with Premium Styling
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search Sunday, gospel or Bible chapter…") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colorScheme.primary)
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Clear")
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(18.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Toggle Filter: "Active & Upcoming" vs "All Sundays"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(
                    selected = showOnlyUpcoming,
                    onClick = { showOnlyUpcoming = true },
                    label = { Text("✨ Upcoming (${upcomingEntries.size})") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        selectedLabelColor = MaterialTheme.colorScheme.primary
                    )
                )

                FilterChip(
                    selected = !showOnlyUpcoming,
                    onClick = { showOnlyUpcoming = false },
                    label = { Text("All (${allEntries.size})") }
                )
            }

            Text(
                text = "${filteredEntries.size} Sundays",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 120.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredEntries, key = { it.id.ifBlank { it.date + it.titleEn } }) { entry ->
                val isUpcoming = entry.date == upcomingSunday?.date
                GlowingLectionaryCard(
                    entry = entry,
                    isUpcoming = isUpcoming,
                    context = context
                )
            }
        }
    }
}

@Composable
fun GlowingLectionaryCard(
    entry: LectionaryEntry,
    isUpcoming: Boolean,
    context: Context
) {
    // If it's upcoming, apply glowing gold border & rich tinted background
    val cardModifier = if (isUpcoming) {
        Modifier
            .fillMaxWidth()
            .border(
                width = 2.5.dp,
                brush = Brush.horizontalGradient(listOf(GoldAccent, GoldLight, GoldAccent)),
                shape = RoundedCornerShape(24.dp)
            )
            .clip(RoundedCornerShape(24.dp))
    } else {
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
    }

    val cardColor = if (isUpcoming) {
        MaterialTheme.colorScheme.surface
    } else {
        MaterialTheme.colorScheme.surface
    }

    Card(
        modifier = cardModifier,
        shape = RoundedCornerShape(if (isUpcoming) 24.dp else 20.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isUpcoming) 6.dp else 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // Upcoming Golden Glow Header Banner
            if (isUpcoming) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = GoldAccent,
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "UPCOMING SUNDAY • ഈ ഞായറാഴ്ച",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    }
                }
            }

            // Top Row: Date Pill & Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (isUpcoming) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Text(
                        text = if (entry.displayDateEn.isNotBlank()) "📅 ${entry.displayDateEn}" else "📅 ${entry.date}",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = if (isUpcoming) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    FilledTonalIconButton(
                        onClick = {
                            val shareText = buildShareText(entry)
                            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_SUBJECT, entry.titleEn)
                                putExtra(Intent.EXTRA_TEXT, shareText)
                            }
                            context.startActivity(Intent.createChooser(sendIntent, "Share Lectionary"))
                        },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    FilledTonalIconButton(
                        onClick = {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("Lectionary", buildShareText(entry))
                            clipboard.setPrimaryClip(clip)
                            Toast.makeText(context, "Readings copied to clipboard", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_nav_lectionary),
                            contentDescription = "Copy",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Liturgical Season (if available)
            if (entry.seasonEn.isNotBlank()) {
                Text(
                    text = "${entry.seasonEn.uppercase()} • ${entry.seasonMl}",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(4.dp))
            }

            // Sunday Title
            Text(
                text = entry.titleEn,
                style = if (isUpcoming) MaterialTheme.typography.headlineSmall else MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = if (isUpcoming) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )

            if (entry.titleMl.isNotBlank()) {
                Text(
                    text = entry.titleMl,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            if (entry.themeEn.isNotBlank()) {
                Spacer(modifier = Modifier.height(6.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                ) {
                    Text(
                        text = "Theme: ${entry.themeEn}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 14.dp),
                color = if (isUpcoming) GoldAccent.copy(alpha = 0.4f) else MaterialTheme.colorScheme.outlineVariant
            )

            // Scripture Readings Table
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                ReadingItem(
                    labelEn = "First Lesson",
                    labelMl = "ഒന്നാം വായന",
                    passage = entry.firstLesson
                )
                ReadingItem(
                    labelEn = "Second Lesson",
                    labelMl = "രണ്ടാം വായന",
                    passage = entry.secondLesson
                )
                ReadingItem(
                    labelEn = "Epistle",
                    labelMl = "ലേഖന വായന",
                    passage = entry.epistleReading
                )
                ReadingItem(
                    labelEn = "Gospel",
                    labelMl = "വിശുദ്ധ സുവിശേഷം",
                    passage = entry.gospelReading,
                    isGospel = true
                )
                if (entry.eveningReading1.isNotBlank()) {
                    ReadingItem(
                        labelEn = "Evening",
                        labelMl = "സന്ധ്യാ വായന",
                        passage = entry.eveningReading1
                    )
                }
                if (entry.eveningReading2.isNotBlank()) {
                    ReadingItem(
                        labelEn = "Evening 2",
                        labelMl = "സന്ധ്യാ വായന 2",
                        passage = entry.eveningReading2
                    )
                }
            }
        }
    }
}

@Composable
private fun ReadingItem(
    labelEn: String,
    labelMl: String,
    passage: String,
    isGospel: Boolean = false
) {
    if (passage.isBlank()) return

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (isGospel) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "$labelEn ($labelMl)",
                    style = MaterialTheme.typography.labelSmall,
                    color = if (isGospel) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = if (isGospel) FontWeight.Bold else FontWeight.Medium
                )
            }

            Surface(
                shape = RoundedCornerShape(8.dp),
                color = if (isGospel) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
            ) {
                Text(
                    text = passage,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isGospel) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                )
            }
        }
    }
}

private fun buildShareText(entry: LectionaryEntry): String {
    return """
        ✝️ ${entry.titleEn} (${entry.titleMl})
        Date: ${entry.displayDateEn.ifBlank { entry.date }}
        ${if (entry.themeEn.isNotBlank()) "Theme: ${entry.themeEn}\n" else ""}
        📖 First Lesson: ${entry.firstLesson}
        📖 Second Lesson: ${entry.secondLesson}
        📜 Epistle: ${entry.epistleReading}
        ✨ Holy Gospel: ${entry.gospelReading}
        ${if (entry.eveningReading1.isNotBlank()) "🌙 Evening: ${entry.eveningReading1}\n" else ""}
        — Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam
        "Lighted to Lighten" (Romans 12:2)
    """.trimIndent()
}
