package org.marthoma.centreyouth.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.marthoma.centreyouth.data.CentreRepository
import org.marthoma.centreyouth.ui.components.OfficeBearerCard

@Composable
fun LeadershipScreen(
    repository: CentreRepository,
    modifier: Modifier = Modifier
) {
    val leaders = remember { repository.getOfficeBearers() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Centre Office Bearers",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Keezhillam - Munnar Centre Executive Committee 2025–26",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(leaders, key = { it.id.ifBlank { it.nameEn } }) { leader ->
                OfficeBearerCard(bearer = leader)
            }
        }
    }
}
