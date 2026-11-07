package org.marthoma.centreyouth.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.marthoma.centreyouth.R
import org.marthoma.centreyouth.data.model.OfficeBearer
import org.marthoma.centreyouth.data.model.Parish
import org.marthoma.centreyouth.data.model.PillarItem
import org.marthoma.centreyouth.data.model.StatItem
import org.marthoma.centreyouth.ui.theme.GoldAccent
import org.marthoma.centreyouth.ui.theme.GoldLight

@Composable
fun StatCard(
    stat: StatItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${stat.value}${stat.suffix}",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stat.labelEn,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2
            )
        }
    }
}

fun getLeaderAvatar(leader: OfficeBearer): Int {
    val name = leader.nameEn.lowercase()
    val role = leader.roleEn.lowercase()
    return when {
        name.contains("james") || role.contains("president") && !role.contains("vice") -> R.drawable.leader_rev_james_george
        name.contains("ashin") || role.contains("secretary") && !role.contains("joint") -> R.drawable.leader_ashin_k_santhosh
        name.contains("aleena") || role.contains("joint") -> R.drawable.leader_aleena_mariya_joy
        name.contains("gibin") || role.contains("treasurer") -> R.drawable.leader_gibin_saji
        name.contains("varghese") || name.contains("sabu") || role.contains("vice") -> R.drawable.leader_varghese_sabu
        else -> R.drawable.app_logo
    }
}

@Composable
fun OfficeBearerCard(
    bearer: OfficeBearer,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showBigPhoto by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { showBigPhoto = true },
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Natural Balanced Avatar with Gold Border (Tap to Enlarge)
            Box(
                modifier = Modifier
                    .size(76.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.5.dp,
                        brush = Brush.linearGradient(listOf(GoldAccent, GoldLight, GoldAccent)),
                        shape = CircleShape
                    )
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .clickable { showBigPhoto = true },
                contentAlignment = Alignment.Center
            ) {
                if (bearer.directImageUrl.isNotBlank()) {
                    AsyncImage(
                        model = bearer.directImageUrl,
                        contentDescription = bearer.nameEn,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                } else {
                    Image(
                        painter = painterResource(id = getLeaderAvatar(bearer)),
                        contentDescription = bearer.nameEn,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
                ) {
                    Text(
                        text = bearer.roleEn.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = bearer.nameEn,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                if (bearer.nameMl.isNotBlank()) {
                    Text(
                        text = bearer.nameMl,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                if (bearer.parishEn.isNotBlank()) {
                    Text(
                        text = bearer.parishEn,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Quick Connect Action Buttons
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (bearer.phone.isNotBlank()) {
                    FilledTonalIconButton(
                        onClick = { dialPhone(context, bearer.phone) },
                        modifier = Modifier.size(38.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Call",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                if (bearer.whatsapp.isNotBlank() || bearer.phone.isNotBlank()) {
                    val rawNum = bearer.whatsapp.ifBlank { bearer.phone }.filter { it.isDigit() }
                    FilledTonalIconButton(
                        onClick = { openWhatsApp(context, rawNum) },
                        modifier = Modifier.size(38.dp),
                        colors = IconButtonDefaults.filledTonalIconButtonColors(
                            containerColor = Color(0xFF25D366).copy(alpha = 0.15f)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "WhatsApp",
                            tint = Color(0xFF25D366),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }

    if (showBigPhoto) {
        FullScreenImageViewerDialog(
            imageUrl = bearer.directImageUrl,
            fallbackResId = getLeaderAvatar(bearer),
            title = bearer.nameEn,
            subtitle = if (bearer.nameMl.isNotBlank()) "${bearer.nameMl} • ${bearer.parishEn}" else bearer.parishEn,
            roleBadge = bearer.roleEn,
            onDismiss = { showBigPhoto = false }
        )
    }
}

@Composable
fun ParishCard(
    parish: Parish,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = parish.nameEn,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    if (parish.nameMl.isNotBlank()) {
                        Text(
                            text = parish.nameMl,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    if (parish.vicarEn.isNotBlank()) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Vicar: ${parish.vicarEn}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    if (parish.phone.isNotBlank()) {
                        FilledTonalIconButton(
                            onClick = { dialPhone(context, parish.phone) },
                            modifier = Modifier.size(38.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "Call Parish",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    FilledTonalIconButton(
                        onClick = { openMaps(context, "${parish.nameEn} Mar Thoma Church") },
                        modifier = Modifier.size(38.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Directions",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PillarCard(
    pillar: PillarItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
        )
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                text = "${pillar.titleEn} (${pillar.titleMl})",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = pillar.descEn,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun dialPhone(context: Context, phoneNumber: String) {
    val clean = phoneNumber.filter { it.isDigit() || it == '+' }
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$clean"))
    context.startActivity(intent)
}

private fun openWhatsApp(context: Context, phone: String) {
    val clean = if (phone.startsWith("91")) phone else "91$phone"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/$clean"))
    context.startActivity(intent)
}

private fun openMaps(context: Context, query: String) {
    val uri = Uri.parse("geo:0,0?q=" + Uri.encode(query))
    val intent = Intent(Intent.ACTION_VIEW, uri)
    context.startActivity(intent)
}
