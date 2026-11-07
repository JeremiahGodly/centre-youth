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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.marthoma.centreyouth.data.CentreRepository
import org.marthoma.centreyouth.ui.components.ParishCard

@Composable
fun ParishesScreen(
    repository: CentreRepository,
    modifier: Modifier = Modifier
) {
    val parishes = remember { repository.getParishes() }
    var searchQuery by remember { mutableStateOf("") }

    val filteredParishes = remember(searchQuery, parishes) {
        if (searchQuery.isBlank()) {
            parishes
        } else {
            val q = searchQuery.lowercase().trim()
            parishes.filter {
                it.nameEn.lowercase().contains(q) ||
                it.nameMl.lowercase().contains(q) ||
                it.vicarEn.lowercase().contains(q) ||
                it.vicarMl.lowercase().contains(q) ||
                it.placeEn.lowercase().contains(q)
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
            placeholder = { Text("Search 15 Centre parishes or vicars…") },
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
            text = "Total Parishes: ${filteredParishes.size}",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(8.dp))

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
