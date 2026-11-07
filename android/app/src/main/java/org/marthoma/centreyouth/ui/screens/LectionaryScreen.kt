package org.marthoma.centreyouth.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.marthoma.centreyouth.data.CentreRepository
import org.marthoma.centreyouth.data.model.LectionaryEntry

@Composable
fun LectionaryScreen(
    repository: CentreRepository,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val allEntries = remember { repository.getLectionary() }
    var searchQuery by remember { mutableStateOf("") }

    val filteredEntries = remember(searchQuery, allEntries) {
        if (searchQuery.isBlank()) {
            allEntries
        } else {
            val q = searchQuery.lowercase().trim()
            allEntries.filter {
                it.titleEn.lowercase().contains(q) ||
                it.titleMl.lowercase().contains(q) ||
                it.gospel.lowercase().contains(q) ||
                it.epistle.lowercase().contains(q) ||
                it.lesson1.lowercase().contains(q) ||
                it.date.lowercase().contains(q)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search Sunday or Bible reading…") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Clear")
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Total Sundays: ${filteredEntries.size}",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredEntries, key = { it.id.ifBlank { it.date + it.titleEn } }) { entry ->
                LectionaryCard(entry = entry, context = context)
            }
        }
    }
}

@Composable
fun LectionaryCard(
    entry: LectionaryEntry,
    context: Context
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            // Header: Date & Share
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (entry.date.isNotBlank()) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Text(
                            text = "📅 ${entry.date}",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Row {
                    IconButton(onClick = {
                        val shareText = buildShareText(entry)
                        val sendIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_SUBJECT, entry.titleEn)
                            putExtra(Intent.EXTRA_TEXT, shareText)
                        }
                        context.startActivity(Intent.createChooser(sendIntent, "Share Liturgical Lectionary"))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    IconButton(onClick = {
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("Lectionary", buildShareText(entry))
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(context, "Readings copied to clipboard", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = "Copy",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Sunday Title
            Text(
                text = entry.titleEn,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            if (entry.titleMl.isNotBlank()) {
                Text(
                    text = entry.titleMl,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Divider(
                modifier = Modifier.padding(vertical = 12.dp),
                color = MaterialTheme.colorScheme.outlineVariant
            )

            // Scripture Passages
            ReadingRow(label = "First Lesson (ഒന്നാം വായന)", passage = entry.lesson1)
            ReadingRow(label = "Second Lesson (രണ്ടാം വായന)", passage = entry.lesson2)
            ReadingRow(label = "Epistle (ലേഖനം)", passage = entry.epistle)
            ReadingRow(label = "Gospel (സുവിശേഷം)", passage = entry.gospel, isGospel = true)

            if (entry.evening1.isNotBlank()) {
                ReadingRow(label = "Evening Reading (സന്ധ്യാ വായന)", passage = entry.evening1)
            }
        }
    }
}

@Composable
private fun ReadingRow(label: String, passage: String, isGospel: Boolean = false) {
    if (passage.isBlank()) return
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = if (isGospel) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = if (isGospel) FontWeight.Bold else FontWeight.Normal
        )
        Text(
            text = passage,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (isGospel) FontWeight.Bold else FontWeight.Medium,
            color = if (isGospel) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}

private fun buildShareText(entry: LectionaryEntry): String {
    return """
        ✝️ ${entry.titleEn} (${entry.titleMl})
        Date: ${entry.date}
        
        📖 First Lesson: ${entry.lesson1}
        📖 Second Lesson: ${entry.lesson2}
        📜 Epistle: ${entry.epistle}
        ✨ Gospel: ${entry.gospel}
        🌙 Evening: ${entry.evening1}
        
        — Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam
    """.trimIndent()
}
