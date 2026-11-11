package org.marthoma.centreyouth.ui.screens

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.marthoma.centreyouth.R
import org.marthoma.centreyouth.data.CentreRepository
import org.marthoma.centreyouth.data.model.ProgramEvent
import org.marthoma.centreyouth.ui.components.AwesomeLogoEmblem3D
import org.marthoma.centreyouth.ui.components.PillarCard
import org.marthoma.centreyouth.ui.components.ProgramDetailSheet
import org.marthoma.centreyouth.ui.components.ProgramEventCard
import org.marthoma.centreyouth.ui.components.StatCard
import org.marthoma.centreyouth.ui.theme.GoldAccent

@Composable
fun HomeScreen(
    repository: CentreRepository,
    onNavigateToLectionary: () -> Unit,
    onNavigateToSongs: () -> Unit,
    onNavigateToParishes: () -> Unit,
    onNavigateToLeadership: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val centreInfo = repository.getCentreInfo()
    val stats = repository.getStats()
    val pillars = repository.getPillars()
    val todayLectionary = repository.getTodayLectionary()

    var selectedProgram by remember { mutableStateOf<ProgramEvent?>(null) }
    var programsList by remember { mutableStateOf(repository.getPrograms()) }

    // Silently fetch newest programs/posters from Google Sheet in background
    LaunchedEffect(Unit) {
        val result = repository.syncProgramsFromGoogleSheet()
        if (result.isSuccess) {
            programsList = repository.getPrograms()
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Hero Header
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AwesomeLogoEmblem3D(
                        medallionSize = 110.dp,
                        enableInteractiveTilt = true,
                        showAura = true,
                        showPedestalShadow = true
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = centreInfo.nameEn,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Text(
                        text = centreInfo.nameMl,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
                    ) {
                        Text(
                            text = "✨ ${centreInfo.mottoEn} • ${centreInfo.dioceseEn}",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }

        // Daily Scripture Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_nav_lectionary),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Daily Scripture",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        IconButton(onClick = {
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "\"${centreInfo.themeQuoteEn}\"\n— ${centreInfo.themeVerse}\n\nKeezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam"
                                )
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "Share Scripture"))
                        }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Share",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "\"${centreInfo.themeQuoteEn}\"",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "— ${centreInfo.themeVerse}",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "\"${centreInfo.themeQuoteMl}\"",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Upcoming Liturgical Lectionary Glance
        if (todayLectionary != null) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onNavigateToLectionary() },
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.5.dp, GoldAccent.copy(alpha = 0.6f)),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "✨ UPCOMING SUNDAY LECTIONARY",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = GoldAccent
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = todayLectionary.titleEn,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            if (todayLectionary.titleMl.isNotBlank()) {
                                Text(
                                    text = todayLectionary.titleMl,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            val gospelText = todayLectionary.gospelReading
                            if (gospelText.isNotBlank()) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "📖 Gospel: $gospelText",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Open Lectionary",
                            tint = GoldAccent
                        )
                    }
                }
            }
        }

        // Quick Actions Grid
        item {
            Text(
                text = "Quick Access",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                QuickActionItem(
                    title = "Lectionary",
                    iconRes = R.drawable.ic_nav_lectionary,
                    onClick = onNavigateToLectionary,
                    modifier = Modifier.weight(1f)
                )
                QuickActionItem(
                    title = "Songs",
                    iconRes = R.drawable.ic_nav_songs,
                    onClick = onNavigateToSongs,
                    modifier = Modifier.weight(1f)
                )
                QuickActionItem(
                    title = "Parishes",
                    iconRes = R.drawable.ic_nav_church,
                    onClick = onNavigateToParishes,
                    modifier = Modifier.weight(1f)
                )
                QuickActionItem(
                    title = "Leaders",
                    iconRes = R.drawable.ic_nav_more,
                    onClick = onNavigateToLeadership,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Live Upcoming Programs & Events (Synced from Google Sheet)
        if (programsList.isNotEmpty()) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Upcoming Programs & Events",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = GoldAccent.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = "LIVE UPDATES",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = GoldAccent,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    programsList.forEach { program ->
                        ProgramEventCard(
                            event = program,
                            onClick = { selectedProgram = program }
                        )
                    }
                }
            }
        }

        // Centre Stats
        if (stats.isNotEmpty()) {
            item {
                Text(
                    text = "Centre at a Glance",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(stats) { stat ->
                        StatCard(stat = stat, modifier = Modifier.width(130.dp))
                    }
                }
            }
        }

        // Four Pillars of Sakhyam
        item {
            Text(
                text = "Four Pillars of Sakhyam",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                pillars.forEach { pillar ->
                    PillarCard(pillar = pillar)
                }
            }
        }
    }

    if (selectedProgram != null) {
        ProgramDetailSheet(
            event = selectedProgram!!,
            onDismiss = { selectedProgram = null }
        )
    }
}

@Composable
fun QuickActionItem(
    title: String,
    iconRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(vertical = 14.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(26.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}
