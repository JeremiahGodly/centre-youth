package org.marthoma.centreyouth.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.marthoma.centreyouth.R
import org.marthoma.centreyouth.data.model.ProgramEvent
import org.marthoma.centreyouth.ui.theme.GoldAccent
import org.marthoma.centreyouth.ui.theme.GoldLight

@Composable
fun ProgramEventCard(
    event: ProgramEvent,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showBigPoster by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .clickable { onClick() }
            .border(
                width = 1.5.dp,
                brush = Brush.linearGradient(listOf(GoldAccent.copy(alpha = 0.5f), GoldLight.copy(alpha = 0.8f))),
                shape = RoundedCornerShape(22.dp)
            ),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Poster Image (if available) - Tap to enlarge
            if (event.directImageUrl.isNotBlank()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .clickable { showBigPoster = true }
                ) {
                    AsyncImage(
                        model = event.directImageUrl,
                        contentDescription = event.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    // Category Pill on top of image
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = GoldAccent,
                        modifier = Modifier
                            .padding(12.dp)
                            .align(Alignment.TopStart)
                    ) {
                        Text(
                            text = event.category.uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    // Enlarge Hint Pill
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color.Black.copy(alpha = 0.6f),
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.BottomEnd)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Enlarge",
                                tint = Color.White,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "View Big",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                if (event.directImageUrl.isBlank()) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Text(
                            text = event.category.uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Text(
                    text = event.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                if (event.titleMl.isNotBlank()) {
                    Text(
                        text = event.titleMl,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Date & Time
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Date",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (event.time.isNotBlank()) "${event.date} • ${event.time}" else event.date,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium
                    )
                }

                // Venue
                if (event.venue.isNotBlank()) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Venue",
                            tint = GoldAccent,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = event.venue,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Tap to view specifications & register",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Details",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }

    if (showBigPoster) {
        FullScreenImageViewerDialog(
            imageUrl = event.directImageUrl,
            title = event.title,
            subtitle = if (event.titleMl.isNotBlank()) "${event.titleMl} • ${event.date}" else event.date,
            roleBadge = event.category,
            onDismiss = { showBigPoster = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgramDetailSheet(
    event: ProgramEvent,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBigPoster by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 32.dp)
        ) {
            // Poster image banner - Tap to view full size
            if (event.directImageUrl.isNotBlank()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .clickable { showBigPoster = true }
                ) {
                    AsyncImage(
                        model = event.directImageUrl,
                        contentDescription = event.title,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color.Black.copy(alpha = 0.65f),
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.BottomEnd)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Zoom",
                                tint = Color.White,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Tap for Full Size",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            Surface(
                shape = RoundedCornerShape(8.dp),
                color = GoldAccent
            ) {
                Text(
                    text = event.category.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = event.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            if (event.titleMl.isNotBlank()) {
                Text(
                    text = event.titleMl,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Date, Time, Venue Box
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = if (event.time.isNotBlank()) "${event.date} • ${event.time}" else event.date, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                    }

                    if (event.venue.isNotBlank()) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = GoldAccent, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = event.venue, style = MaterialTheme.typography.bodyMedium)
                        }
                    }

                    if (event.speakers.isNotBlank()) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = event.speakers, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Program Description & Specifications
            if (event.description.isNotBlank()) {
                Text(
                    text = "Program Specifications & Details",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = event.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Action Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (event.registrationUrl.isNotBlank()) {
                    Button(
                        onClick = { openWebUrl(context, event.registrationUrl) },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Register")
                    }
                }

                if (event.contactPhone.isNotBlank()) {
                    OutlinedButton(
                        onClick = { openWhatsAppMessage(context, event.contactPhone, "Hi, I would like to know more about ${event.title}") },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = null, tint = Color(0xFF25D366), modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("WhatsApp")
                    }
                }

                FilledTonalIconButton(
                    onClick = { shareEventNotice(context, event) }
                ) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                }
            }
        }
    }

    if (showBigPoster) {
        FullScreenImageViewerDialog(
            imageUrl = event.directImageUrl,
            title = event.title,
            subtitle = if (event.titleMl.isNotBlank()) "${event.titleMl} • ${event.date}" else event.date,
            roleBadge = event.category,
            onDismiss = { showBigPoster = false }
        )
    }
}

private fun openWebUrl(context: Context, url: String) {
    try {
        val target = if (!url.startsWith("http://") && !url.startsWith("https://")) "https://$url" else url
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(target)))
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

private fun openWhatsAppMessage(context: Context, rawPhone: String, message: String) {
    try {
        val digits = rawPhone.filter { it.isDigit() }
        val phoneWithCountry = if (digits.length == 10) "91$digits" else digits
        val uri = Uri.parse("https://api.whatsapp.com/send?phone=$phoneWithCountry&text=${Uri.encode(message)}")
        context.startActivity(Intent(Intent.ACTION_VIEW, uri))
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

private fun shareEventNotice(context: Context, event: ProgramEvent) {
    val text = """
        📢 *${event.title}*
        ${if (event.titleMl.isNotBlank()) event.titleMl + "\n" else ""}
        📅 *Date:* ${event.date} ${event.time}
        📍 *Venue:* ${event.venue}
        ${if (event.speakers.isNotBlank()) "🎙️ *Speakers:* " + event.speakers + "\n" else ""}
        📝 *Details:*
        ${event.description}
        
        ${if (event.registrationUrl.isNotBlank()) "🔗 *Register here:* " + event.registrationUrl else ""}
        
        _Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam_
    """.trimIndent()

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, event.title)
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(Intent.createChooser(intent, "Share Event Notice"))
}
