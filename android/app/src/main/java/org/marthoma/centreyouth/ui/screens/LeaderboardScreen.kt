package org.marthoma.centreyouth.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.marthoma.centreyouth.data.CentreRepository
import org.marthoma.centreyouth.data.model.LeaderboardEntry
import org.marthoma.centreyouth.ui.theme.GoldAccent

@Composable
fun LeaderboardScreen(
    repository: CentreRepository,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var entries by remember { mutableStateOf(repository.getLeaderboard()) }
    var isSyncing by remember { mutableStateOf(false) }
    var showSubmitDialog by remember { mutableStateOf(false) }

    val myScore = remember { repository.getQuizTotalScore() }
    val myStars = remember { repository.getQuizTotalStars() }
    val myHighestLevel = remember { repository.getHighestQuizLevel() }

    LaunchedEffect(Unit) {
        isSyncing = true
        repository.syncLeaderboardFromGoogleSheet()
        entries = repository.getLeaderboard()
        isSyncing = false
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Player Personal Score Summary + Submit to Google Sheet CTA
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        1.5.dp,
                        Brush.horizontalGradient(listOf(Color(0xFF7C3AED), Color(0xFFF59E0B))),
                        RoundedCornerShape(22.dp)
                    ),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF130D2E)
                )
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "🏆 Centre Quiz Leaderboard",
                                color = Color(0xFFFDE68A),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Text(
                                text = "സെന്റർ ബൈബിൾ ക്വിസ് റാങ്കിംഗ് (Google Sheet)",
                                color = Color(0xFFCBD5E1),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        IconButton(
                            onClick = {
                                if (!isSyncing) {
                                    scope.launch {
                                        isSyncing = true
                                        repository.syncLeaderboardFromGoogleSheet()
                                        entries = repository.getLeaderboard()
                                        isSyncing = false
                                        Toast.makeText(context, "Leaderboard refreshed", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        ) {
                            if (isSyncing) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(18.dp),
                                    strokeWidth = 2.dp,
                                    color = GoldAccent
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "Refresh Leaderboard",
                                    tint = Color.White
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color.White.copy(alpha = 0.07f))
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        MyStatChip("Your Score", "$myScore pts", Color(0xFF38BDF8))
                        MyStatChip("Stars", "⭐ $myStars/300", Color(0xFFFBBF24))
                        MyStatChip("Max Level", "Lvl $myHighestLevel", Color(0xFF34D399))
                        MyStatChip("Streak", "🔥 ${repository.getDailyStreak().coerceAtLeast(1)}d", Color(0xFFFB923C))
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = {
                            if (myScore <= 0) {
                                Toast.makeText(
                                    context,
                                    "Play at least 1 Bible Quiz level first to earn a score!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                showSubmitDialog = true
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GoldAccent
                        )
                    ) {
                        Text(
                            text = "Submit / Update My Score to Leaderboard",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        if (entries.isEmpty()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("👑", fontSize = 40.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Be the first from your parish on the Centre Leaderboard!",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Complete any Bible Quiz level and tap 'Submit / Update My Score' above.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        } else {
            // Top 3 Podium Cards
            val topThree = entries.take(3)
            item {
                Text(
                    text = "Top Champions",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    topThree.forEachIndexed { idx, entry ->
                        LeaderboardRankCard(rank = idx + 1, entry = entry)
                    }
                }
            }

            if (entries.size > 3) {
                item {
                    Text(
                        text = "Centre Standings",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
                itemsIndexed(entries.drop(3)) { idx, entry ->
                    LeaderboardRankCard(rank = idx + 4, entry = entry)
                }
            }
        }
    }

    if (showSubmitDialog) {
        SubmitScoreDialog(
            repository = repository,
            score = myScore,
            stars = myStars,
            onDismiss = { showSubmitDialog = false },
            onSubmit = { name, parish ->
                scope.launch {
                    val result = repository.submitQuizLeaderboardScore(name, parish)
                    entries = repository.getLeaderboard()
                    showSubmitDialog = false
                    val msg = if (result.isSuccess) {
                        "Score published to Centre Leaderboard!"
                    } else {
                        "Saved locally (Sheet sync will retry automatically)"
                    }
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}

@Composable
private fun MyStatChip(label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, color = Color(0xFF94A3B8), fontSize = 11.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = value, color = color, fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
    }
}

@Composable
private fun LeaderboardRankCard(rank: Int, entry: LeaderboardEntry) {
    val medal = when (rank) {
        1 -> "👑"
        2 -> "🥈"
        3 -> "🥉"
        else -> "#$rank"
    }
    val highlightColor = when (rank) {
        1 -> Color(0xFFFBBF24)
        2 -> Color(0xFF94A3B8)
        3 -> Color(0xFFD97706)
        else -> MaterialTheme.colorScheme.outlineVariant
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = if (rank <= 3) 1.5.dp else 1.dp,
                color = highlightColor.copy(alpha = if (rank <= 3) 0.75f else 0.5f),
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (rank == 1) highlightColor.copy(alpha = 0.08f) else MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(highlightColor.copy(alpha = 0.16f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = medal,
                    fontSize = if (rank <= 3) 20.sp else 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = if (rank <= 3) Color.Unspecified else MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = entry.name.ifBlank { "Sakhyam Member" },
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = entry.parish.ifBlank { "Keezhillam - Munnar Centre" },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${entry.score} pts",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = if (rank == 1) GoldAccent else MaterialTheme.colorScheme.primary
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFBBF24),
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "${entry.stars} • Lvl ${entry.highestLevel}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun SubmitScoreDialog(
    repository: CentreRepository,
    score: Int,
    stars: Int,
    onDismiss: () -> Unit,
    onSubmit: (name: String, parish: String) -> Unit
) {
    val parishes = remember { repository.getParishes().map { it.nameEn } + "Other / Guest" }
    var name by remember { mutableStateOf(repository.getPlayerName()) }
    var selectedParish by remember {
        mutableStateOf(repository.getPlayerParish().ifBlank { parishes.firstOrNull() ?: "Centre Youth" })
    }
    var parishMenuOpen by remember { mutableStateOf(false) }
    var isSubmitting by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = { if (!isSubmitting) onDismiss() },
        title = {
            Text(text = "🏆 Submit Score to Leaderboard", fontWeight = FontWeight.Bold)
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "Score: $score pts • ⭐ $stars / 300 Stars",
                    style = MaterialTheme.typography.labelLarge,
                    color = GoldAccent,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Your Full Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Box(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = selectedParish,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Your Parish") },
                        trailingIcon = { Icon(Icons.Default.KeyboardArrowDown, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clickable { parishMenuOpen = true }
                    )
                    DropdownMenu(
                        expanded = parishMenuOpen,
                        onDismissRequest = { parishMenuOpen = false },
                        modifier = Modifier.heightIn(max = 260.dp)
                    ) {
                        parishes.forEach { parish ->
                            DropdownMenuItem(
                                text = { Text(parish, fontSize = 13.sp) },
                                onClick = {
                                    selectedParish = parish
                                    parishMenuOpen = false
                                }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank() && !isSubmitting) {
                        isSubmitting = true
                        onSubmit(name, selectedParish)
                    }
                },
                enabled = name.isNotBlank() && !isSubmitting
            ) {
                if (isSubmitting) {
                    CircularProgressIndicator(modifier = Modifier.size(16.dp), strokeWidth = 2.dp)
                } else {
                    Text("Publish Score")
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss, enabled = !isSubmitting) {
                Text("Cancel")
            }
        }
    )
}
