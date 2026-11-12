package org.marthoma.centreyouth.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.marthoma.centreyouth.data.CentreRepository
import org.marthoma.centreyouth.data.model.PrayerRequest
import org.marthoma.centreyouth.ui.theme.GoldAccent

@Composable
fun PrayerWallScreen(
    repository: CentreRepository,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var prayers by remember { mutableStateOf(repository.getPrayerRequests()) }
    var selectedCategory by remember { mutableStateOf("All") }
    var isRefreshing by remember { mutableStateOf(false) }
    var showSubmitDialog by remember { mutableStateOf(false) }
    var prayedUpdateTick by remember { mutableIntStateOf(0) }

    val categories = listOf(
        "All",
        "Church & Youth",
        "Healing",
        "Exams / Career",
        "Family",
        "Thanksgiving"
    )

    LaunchedEffect(Unit) {
        isRefreshing = true
        repository.syncPrayerWallFromGoogleSheet()
        prayers = repository.getPrayerRequests()
        isRefreshing = false
    }

    val filteredPrayers = remember(prayers, selectedCategory) {
        if (selectedCategory == "All") prayers
        else prayers.filter { it.category.equals(selectedCategory, ignoreCase = true) }
    }

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Peaceful Hero Banner
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
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
                                    text = "🙏 Centre Prayer Wall",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Text(
                                    text = "കേന്ദ്ര പ്രാർത്ഥനാ മതിൽ • \"Pray for each other\" (James 5:16)",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                                )
                            }

                            IconButton(
                                onClick = {
                                    if (!isRefreshing) {
                                        scope.launch {
                                            isRefreshing = true
                                            repository.syncPrayerWallFromGoogleSheet()
                                            prayers = repository.getPrayerRequests()
                                            isRefreshing = false
                                            Toast.makeText(context, "Prayer Wall updated", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            ) {
                                if (isRefreshing) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(18.dp),
                                        strokeWidth = 2.dp
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.Refresh,
                                        contentDescription = "Refresh Prayers"
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = { showSubmitDialog = true },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Submit a Prayer Request",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            // Category Filter Chips
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories) { cat ->
                        val isSelected = selectedCategory == cat
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedCategory = cat },
                            label = { Text(cat, fontSize = 12.sp) }
                        )
                    }
                }
            }

            if (filteredPrayers.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(28.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("🕊️", fontSize = 36.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "No prayer requests in this category yet.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            } else {
                items(filteredPrayers, key = { it.id.ifBlank { it.request.hashCode() } }) { prayer ->
                    val hasPrayed = remember(prayedUpdateTick, prayer.id) {
                        repository.hasPrayedFor(prayer.id)
                    }
                    PrayerCard(
                        prayer = prayer,
                        hasPrayed = hasPrayed,
                        onPrayClick = {
                            if (!hasPrayed) {
                                scope.launch {
                                    repository.markPrayerAsPrayed(prayer.id)
                                    prayers = repository.getPrayerRequests()
                                    prayedUpdateTick++
                                }
                            }
                        }
                    )
                }
            }
        }
    }

    if (showSubmitDialog) {
        SubmitPrayerRequestDialog(
            repository = repository,
            categories = categories.filter { it != "All" },
            onDismiss = { showSubmitDialog = false },
            onSubmit = { name, parish, category, text ->
                scope.launch {
                    val result = repository.submitPrayerRequest(name, parish, category, text)
                    prayers = repository.getPrayerRequests()
                    showSubmitDialog = false
                    if (result.isSuccess) {
                        Toast.makeText(context, "Prayer shared to Centre Wall!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Saved on device & queued for Sheet", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )
    }
}

@Composable
private fun PrayerCard(
    prayer: PrayerRequest,
    hasPrayed: Boolean,
    onPrayClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (hasPrayed) GoldAccent.copy(alpha = 0.5f) else MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(18.dp)
            ),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Text(
                        text = prayer.category.ifBlank { "Prayer" },
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                if (prayer.date.isNotBlank()) {
                    Text(
                        text = prayer.date,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = prayer.request,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 21.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "— ${prayer.name.ifBlank { "Anonymous" }}",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                    if (prayer.parish.isNotBlank()) {
                        Text(
                            text = prayer.parish,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Surface(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable(enabled = !hasPrayed) { onPrayClick() },
                    shape = CircleShape,
                    color = if (hasPrayed) GoldAccent.copy(alpha = 0.20f) else MaterialTheme.colorScheme.surfaceVariant,
                    border = BorderStroke(
                        1.dp,
                        if (hasPrayed) GoldAccent else MaterialTheme.colorScheme.outlineVariant
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (hasPrayed) "🙏 Praying (${prayer.prayedCount})" else "🙏 Pray (${prayer.prayedCount})",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (hasPrayed) GoldAccent else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SubmitPrayerRequestDialog(
    repository: CentreRepository,
    categories: List<String>,
    onDismiss: () -> Unit,
    onSubmit: (name: String, parish: String, category: String, request: String) -> Unit
) {
    val parishes = remember { listOf("Centre General") + repository.getParishes().map { it.nameEn } }
    var isAnonymous by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf(repository.getPlayerName()) }
    var selectedParish by remember { mutableStateOf(repository.getPlayerParish().ifBlank { "Centre General" }) }
    var parishMenuExpanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf(categories.firstOrNull() ?: "Church & Youth") }
    var requestText by remember { mutableStateOf("") }
    var isSubmitting by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = { if (!isSubmitting) onDismiss() },
        title = {
            Text(
                text = "🙏 Submit Prayer Request",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Post Anonymously",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Switch(
                        checked = isAnonymous,
                        onCheckedChange = { isAnonymous = it }
                    )
                }

                if (!isAnonymous) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Your Name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Parish Selector Dropdown
                Box(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = selectedParish,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Parish") },
                        trailingIcon = {
                            Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { parishMenuExpanded = true }
                    )
                    // Transparent overlay so tapping anywhere opens dropdown
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clickable { parishMenuExpanded = true }
                    )
                    DropdownMenu(
                        expanded = parishMenuExpanded,
                        onDismissRequest = { parishMenuExpanded = false },
                        modifier = Modifier.heightIn(max = 260.dp)
                    ) {
                        parishes.forEach { p ->
                            DropdownMenuItem(
                                text = { Text(p, fontSize = 13.sp) },
                                onClick = {
                                    selectedParish = p
                                    parishMenuExpanded = false
                                }
                            )
                        }
                    }
                }

                Text(
                    text = "Category",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
                LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    items(categories) { cat ->
                        FilterChip(
                            selected = selectedCategory == cat,
                            onClick = { selectedCategory = cat },
                            label = { Text(cat, fontSize = 11.sp) }
                        )
                    }
                }

                OutlinedTextField(
                    value = requestText,
                    onValueChange = { requestText = it },
                    label = { Text("Prayer Request / Praise") },
                    placeholder = { Text("Share what we can uphold in prayer...") },
                    minLines = 3,
                    maxLines = 5,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (requestText.isNotBlank() && !isSubmitting) {
                        isSubmitting = true
                        onSubmit(
                            if (isAnonymous) "Anonymous" else name.ifBlank { "Anonymous" },
                            selectedParish,
                            selectedCategory,
                            requestText
                        )
                    }
                },
                enabled = requestText.isNotBlank() && !isSubmitting
            ) {
                if (isSubmitting) {
                    CircularProgressIndicator(modifier = Modifier.size(16.dp), strokeWidth = 2.dp)
                } else {
                    Text("Submit 🙏")
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
