package org.marthoma.centreyouth.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.marthoma.centreyouth.data.CentreRepository
import org.marthoma.centreyouth.ui.components.ParishCard
import org.marthoma.centreyouth.ui.theme.GoldAccent

@Composable
fun ParishesScreen(
    repository: CentreRepository,
    modifier: Modifier = Modifier
) {
    var parishesList by remember { mutableStateOf(repository.getParishes()) }
    var searchQuery by remember { mutableStateOf("") }

    // Background sync parishes & activities from Google Sheet on screen entry
    LaunchedEffect(Unit) {
        val result = repository.syncParishesFromGoogleSheet()
        if (result.isSuccess) {
            parishesList = repository.getParishes()
        }
    }

    // Always sorted alphabetically A to Z
    val sortedParishes = remember(parishesList) {
        parishesList.sortedBy { it.nameEn.lowercase().trim() }
    }

    val filteredParishes = remember(searchQuery, sortedParishes) {
        if (searchQuery.isBlank()) {
            sortedParishes
        } else {
            val q = searchQuery.lowercase().trim()
            sortedParishes.filter { parish ->
                parish.nameEn.lowercase().contains(q) ||
                parish.nameMl.lowercase().contains(q) ||
                parish.displayPlace.lowercase().contains(q) ||
                parish.displayVicar.lowercase().contains(q) ||
                parish.tag.lowercase().contains(q) ||
                parish.badge.lowercase().contains(q) ||
                parish.activities.any { act ->
                    act.title.lowercase().contains(q) ||
                    act.description.lowercase().contains(q) ||
                    act.venue.lowercase().contains(q)
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search parishes, vicars, or activities…") },
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Parishes (${filteredParishes.size})",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Surface(
                shape = RoundedCornerShape(6.dp),
                color = GoldAccent.copy(alpha = 0.15f)
            ) {
                Text(
                    text = "A – Z Alphabetical",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = GoldAccent,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (filteredParishes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No parishes or activities found matching \"$searchQuery\"",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 100.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredParishes, key = { it.id.ifBlank { it.nameEn } }) { parish ->
                    ParishCard(parish = parish)
                }
            }
        }
    }
}
