package org.marthoma.centreyouth.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PolicyScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Privacy Policy", "Terms of Service")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(tabs[selectedTab]) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        val url = if (selectedTab == 0)
                            "https://jeremiahgodly.github.io/centre-youth/privacy-policy.html"
                        else
                            "https://jeremiahgodly.github.io/centre-youth/terms.html"
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    }) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "Open in browser")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                if (selectedTab == 0) {
                    // Privacy Policy
                    item {
                        PolicySection(
                            title = "1. Introduction & Overview",
                            content = "Keezhillam-Munnar MTYS ('the App') is the official mobile application for Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam (Kottayam - Kochi Diocese, Malankara Mar Thoma Syrian Church). We are dedicated to maintaining strict privacy standards for all church members, youth, and visitors."
                        )
                    }

                    item {
                        PolicySection(
                            title = "2. Developer & Contact",
                            content = "Developed and maintained by Jeremiah Godly Varghese.\nEmail: jrmhgodly@gmail.com\nWebsite: https://jeremiahgodly.github.io/centre-youth"
                        )
                    }

                    item {
                        PolicySection(
                            title = "3. Zero Personal Data Collection",
                            content = "The App does NOT collect, harvest, sell, or share any personal identity information. No user registration, email sign-in, or account creation is required. All liturgical lectionary texts, songs, and directory contacts are available without tracking."
                        )
                    }

                    item {
                        PolicySection(
                            title = "4. Permissions Disclosure & Usage",
                            content = "• POST_NOTIFICATIONS (Android 13+): Used solely to deliver optional daily morning scripture verses and liturgical Sunday reminders at 7:00 AM. Users can enable or disable this anytime in app settings or device settings.\n\n• FOREGROUND_SERVICE_MEDIA_PLAYBACK: Used exclusively to play Centre Youth songs and audio files continuously in the background when the user navigates away or turns off the screen.\n\n• INTERNET & ACCESS_NETWORK_STATE: Used solely to stream audio tracks from the official Sakhyam Google Drive media repository."
                        )
                    }

                    item {
                        PolicySection(
                            title = "5. Children's Privacy (COPPA Compliance)",
                            content = "The App complies with the Children's Online Privacy Protection Act (COPPA) and Google Play Families Policies. The app does not knowingly collect any data from children under 13. All content is faith-based, family-friendly, and educational."
                        )
                    }

                    item {
                        PolicySection(
                            title = "6. Local Device Storage",
                            content = "The App stores user preferences (e.g. favorite songs list, preferred language, theme) strictly on your local device using Android SharedPreferences. This data never leaves your device and is removed if the app is uninstalled."
                        )
                    }
                } else {
                    // Terms of Service
                    item {
                        PolicySection(
                            title = "1. Acceptance of Terms",
                            content = "By downloading, installing, or using the Keezhillam-Munnar MTYS application, you agree to comply with and be bound by these Terms of Service. If you do not agree, please do not use the application."
                        )
                    }

                    item {
                        PolicySection(
                            title = "2. Permitted Use & Ecclesiastical Community",
                            content = "The App is provided free of charge for ecclesiastical, liturgical, devotional, and fellowship purposes under the auspices of Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam. You may use it for personal prayer, church lectionary reading, youth camp activities, and fellowship."
                        )
                    }

                    item {
                        PolicySection(
                            title = "3. Intellectual Property & Church Heritage",
                            content = "All liturgical texts, Mar Thoma lectionary readings, logos, and emblems are the spiritual and intellectual heritage of the Malankara Mar Thoma Syrian Church and Keezhillam - Munnar Centre Yuvajana Sakhyam. Application code and mobile interface are developed by Jeremiah Godly Varghese."
                        )
                    }

                    item {
                        PolicySection(
                            title = "4. Audio Content & Third-Party Services",
                            content = "Youth praise tracks and choral recordings are provided for spiritual enrichment and camp rehearsals. Users may not sell or exploit these recordings commercially."
                        )
                    }

                    item {
                        PolicySection(
                            title = "5. Disclaimer of Warranties",
                            content = "The App is provided 'as is' without warranties of any kind. While every effort is made to ensure accurate liturgical dates and lectionary readings, the church's printed Kalpana and Sabha Almanac remain the primary ecclesiastical authority."
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PolicySection(title: String, content: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
