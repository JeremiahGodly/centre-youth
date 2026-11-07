package org.marthoma.centreyouth.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.marthoma.centreyouth.data.CentreRepository
import org.marthoma.centreyouth.ui.components.OfficeBearerCard
import org.marthoma.centreyouth.ui.theme.GoldAccent

@Composable
fun LeadershipScreen(
    repository: CentreRepository,
    modifier: Modifier = Modifier
) {
    var leaders by remember { mutableStateOf(repository.getOfficeBearers()) }

    // Silently sync latest committee profiles from Google Sheet
    LaunchedEffect(Unit) {
        val result = repository.syncLeadersFromGoogleSheet()
        if (result.isSuccess) {
            leaders = repository.getOfficeBearers()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Centre Office Bearers",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = GoldAccent.copy(alpha = 0.15f)
            ) {
                Text(
                    text = "LIVE",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = GoldAccent,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }

        Text(
            text = "Keezhillam - Munnar Centre Executive Committee",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(leaders, key = { it.id.ifBlank { it.nameEn + it.roleEn } }) { leader ->
                OfficeBearerCard(bearer = leader)
            }
        }
    }
}
