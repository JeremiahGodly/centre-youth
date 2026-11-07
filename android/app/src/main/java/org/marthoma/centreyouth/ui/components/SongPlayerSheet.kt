package org.marthoma.centreyouth.ui.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.marthoma.centreyouth.R
import org.marthoma.centreyouth.data.model.Song

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongPlayerSheet(
    song: Song,
    isPlaying: Boolean,
    currentPositionMs: Long,
    durationMs: Long,
    onPlayPauseToggle: () -> Unit,
    onSeekTo: (Long) -> Unit,
    onSkipNext: () -> Unit,
    onSkipPrevious: () -> Unit,
    onToggleFavorite: () -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = modalBottomSheetState,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Large Emblem / Album Art
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "Centre Logo",
                    modifier = Modifier.size(160.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Title & Transliteration
            Text(
                text = song.titleMl.ifBlank { song.titleEn },
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            if (song.titleMang.isNotBlank() && song.titleMang != song.titleMl) {
                Text(
                    text = song.titleMang,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center
                )
            }

            Text(
                text = "${song.category} • Track #${song.trackNo}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Seekbar
            var sliderPosition by remember { mutableFloatStateOf(0f) }
            var isUserSeeking by remember { mutableStateOf(false) }

            val effectiveProgress = if (isUserSeeking) {
                sliderPosition
            } else if (durationMs > 0) {
                currentPositionMs.toFloat() / durationMs.toFloat()
            } else 0f

            Slider(
                value = effectiveProgress.coerceIn(0f, 1f),
                onValueChange = {
                    isUserSeeking = true
                    sliderPosition = it
                },
                onValueChangeFinished = {
                    isUserSeeking = false
                    if (durationMs > 0) {
                        onSeekTo((sliderPosition * durationMs).toLong())
                    }
                },
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.primary,
                    activeTrackColor = MaterialTheme.colorScheme.primary
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = formatTime(currentPositionMs),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = formatTime(durationMs),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Main Playback Controls
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Favorite
                IconButton(onClick = onToggleFavorite) {
                    Icon(
                        imageVector = if (song.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (song.isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // Previous
                IconButton(onClick = onSkipPrevious) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_action_skip_prev),
                        contentDescription = "Previous",
                        modifier = Modifier.size(32.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }

                // Play / Pause FAB
                FilledIconButton(
                    onClick = onPlayPauseToggle,
                    modifier = Modifier.size(64.dp),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isPlaying) R.drawable.ic_action_pause else R.drawable.ic_action_play
                        ),
                        contentDescription = if (isPlaying) "Pause" else "Play",
                        modifier = Modifier.size(32.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }

                // Next
                IconButton(onClick = onSkipNext) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_action_skip_next),
                        contentDescription = "Next",
                        modifier = Modifier.size(32.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }

                // Share
                IconButton(onClick = {
                    val shareText = "Listen to '${song.titleEn}' (${song.titleMl}) from Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam:\n${song.driveUrl}"
                    val sendIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, shareText)
                    }
                    context.startActivity(Intent.createChooser(sendIntent, "Share Youth Song"))
                }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

private fun formatTime(ms: Long): String {
    if (ms <= 0) return "0:00"
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%d:%02d", minutes, seconds)
}
