package org.marthoma.centreyouth.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.marthoma.centreyouth.data.CentreRepository
import org.marthoma.centreyouth.data.model.Song

@Composable
fun SongsScreen(
    repository: CentreRepository,
    currentPlayingSong: Song?,
    isPlaying: Boolean,
    onSongSelected: (Song) -> Unit,
    modifier: Modifier = Modifier
) {
    val allSongs = remember { repository.getSongs() }
    val categories = remember { repository.getSongCategories() }

    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }
    var showOnlyFavorites by remember { mutableStateOf(false) }

    val filteredSongs = remember(searchQuery, selectedCategory, showOnlyFavorites, allSongs) {
        allSongs.filter { song ->
            val matchesCategory = selectedCategory == "All" || song.category.equals(selectedCategory, ignoreCase = true)
            val matchesFav = !showOnlyFavorites || song.isFavorite
            val matchesSearch = if (searchQuery.isBlank()) true else {
                val q = searchQuery.lowercase().trim()
                song.titleEn.lowercase().contains(q) ||
                song.titleMl.lowercase().contains(q) ||
                song.titleMang.lowercase().contains(q) ||
                song.category.lowercase().contains(q) ||
                song.trackNo.toString().contains(q)
            }
            matchesCategory && matchesFav && matchesSearch
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
            placeholder = { Text("Search 50 youth songs & hymns…") },
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

        // Categories & Favorites Filter Row
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            item {
                FilterChip(
                    selected = showOnlyFavorites,
                    onClick = { showOnlyFavorites = !showOnlyFavorites },
                    label = { Text("Favorites ❤️") },
                    leadingIcon = {
                        Icon(
                            imageVector = if (showOnlyFavorites) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                )
            }

            items(categories) { cat ->
                FilterChip(
                    selected = selectedCategory == cat,
                    onClick = { selectedCategory = cat },
                    label = { Text(cat) }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Showing ${filteredSongs.size} songs",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Songs List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 120.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(filteredSongs, key = { it.id }) { song ->
                val isThisPlaying = currentPlayingSong?.id == song.id && isPlaying
                val isSelected = currentPlayingSong?.id == song.id

                SongItemCard(
                    song = song,
                    isSelected = isSelected,
                    isPlaying = isThisPlaying,
                    onSelect = { onSongSelected(song) },
                    onToggleFavorite = {
                        repository.toggleFavoriteSong(song.id)
                    }
                )
            }
        }
    }
}

@Composable
fun SongItemCard(
    song: Song,
    isSelected: Boolean,
    isPlaying: Boolean,
    onSelect: () -> Unit,
    onToggleFavorite: () -> Unit
) {
    var isFav by remember(song.isFavorite) { mutableStateOf(song.isFavorite) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onSelect() },
        color = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                else MaterialTheme.colorScheme.surface,
        tonalElevation = if (isSelected) 4.dp else 1.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Track Number Badge
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.surfaceVariant
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isPlaying) {
                    Icon(
                        imageVector = Icons.Default.GraphicEq,
                        contentDescription = "Playing",
                        tint = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary
                    )
                } else {
                    Text(
                        text = "${song.trackNo}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = song.titleMl.ifBlank { song.titleEn },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (song.titleMang.isNotBlank() && song.titleMang != song.titleMl) {
                    Text(
                        text = song.titleMang,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary,
                        maxLines = 1
                    )
                }

                Text(
                    text = song.category.ifBlank { "Centre Sakhyam Audio" },
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Favorite Button
            IconButton(onClick = {
                isFav = !isFav
                onToggleFavorite()
            }) {
                Icon(
                    imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFav) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Play Icon
            IconButton(onClick = onSelect) {
                Icon(
                    imageVector = if (isPlaying) Icons.Default.PauseCircle else Icons.Default.PlayCircle,
                    contentDescription = "Play",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}
