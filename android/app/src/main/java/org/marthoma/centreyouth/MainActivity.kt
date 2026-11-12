package org.marthoma.centreyouth

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.OptIn
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.marthoma.centreyouth.data.CentreRepository
import org.marthoma.centreyouth.data.model.AppThemeMode
import org.marthoma.centreyouth.data.model.QuizLanguage
import org.marthoma.centreyouth.data.model.Song
import org.marthoma.centreyouth.service.AudioPlaybackService
import org.marthoma.centreyouth.ui.components.MiniPlayer
import org.marthoma.centreyouth.ui.components.SongPlayerSheet
import org.marthoma.centreyouth.ui.screens.*
import org.marthoma.centreyouth.ui.theme.CentreYouthTheme
import org.marthoma.centreyouth.ui.theme.GoldAccent
import org.marthoma.centreyouth.ui.theme.GoldLight

enum class Screen(val title: String, val iconRes: Int) {
    HOME("Home", R.drawable.ic_stat_scripture),
    QUIZ("Quiz", R.drawable.ic_nav_quiz),
    LECTIONARY("Lectionary", R.drawable.ic_nav_lectionary),
    SONGS("Songs", R.drawable.ic_nav_songs),
    PARISHES("Parishes", R.drawable.ic_nav_church),
    MORE("More", R.drawable.ic_nav_more),
    PRAYER_WALL("Prayer Wall", R.drawable.ic_stat_scripture),
    GALLERY("Centre Gallery", R.drawable.ic_stat_scripture),
    LEADERBOARD("Quiz Leaderboard", R.drawable.ic_nav_quiz),
    SETTINGS("App Settings", R.drawable.ic_stat_scripture),
    LEADERSHIP("Leadership", R.drawable.ic_stat_scripture),
    EVENTS("Events", R.drawable.ic_stat_scripture),
    ABOUT("About", R.drawable.ic_stat_scripture),
    POLICY("Policy", R.drawable.ic_stat_scripture)
}

@OptIn(ExperimentalMaterial3Api::class, UnstableApi::class)
class MainActivity : ComponentActivity() {

    private lateinit var repository: CentreRepository
    private var controllerFuture: ListenableFuture<MediaController>? = null
    private var mediaController: MediaController? = null

    private var currentSongState = mutableStateOf<Song?>(null)
    private var isPlayingState = mutableStateOf(false)
    private var currentPositionState = mutableLongStateOf(0L)
    private var durationState = mutableLongStateOf(0L)
    private var activeScreenState = mutableStateOf(Screen.HOME)
    private var themeModeState = mutableStateOf(AppThemeMode.SYSTEM)

    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        // Permission result handled
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            splashScreenViewProvider.remove()
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        repository = CentreRepository(this)
        themeModeState.value = repository.getThemeMode()
        handleIntent(intent)
        checkNotificationPermission()
        initializeMediaController()

        val hasDirectNavigation = intent?.data != null || intent?.getStringExtra("navigate_to") != null

        setContent {
            val activeThemeMode by themeModeState
            val useDarkTheme = when (activeThemeMode) {
                AppThemeMode.SYSTEM -> isSystemInDarkTheme()
                AppThemeMode.DARK -> true
                AppThemeMode.LIGHT -> false
            }

            CentreYouthTheme(darkTheme = useDarkTheme) {
                var showStartupEntry by rememberSaveable { mutableStateOf(!hasDirectNavigation) }

                Crossfade(
                    targetState = showStartupEntry,
                    animationSpec = tween(durationMillis = 450, easing = FastOutSlowInEasing),
                    label = "StartupCrossfade"
                ) { isStartup ->
                    if (isStartup) {
                        AppStartupEntryScreen(
                            onEnterApp = { showStartupEntry = false }
                        )
                    } else {
                        MainAppScaffold()
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        val data = intent?.data
        val navigateTo = intent?.getStringExtra("navigate_to")
        if (data != null) {
            when (data.host) {
                "quiz" -> activeScreenState.value = Screen.QUIZ
                "lectionary" -> activeScreenState.value = Screen.LECTIONARY
                "songs" -> activeScreenState.value = Screen.SONGS
                "leadership" -> activeScreenState.value = Screen.LEADERSHIP
                "parishes" -> activeScreenState.value = Screen.PARISHES
            }
        } else if (navigateTo != null) {
            when (navigateTo) {
                "quiz" -> activeScreenState.value = Screen.QUIZ
                "lectionary" -> activeScreenState.value = Screen.LECTIONARY
                "songs" -> activeScreenState.value = Screen.SONGS
            }
        }
    }

    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun initializeMediaController() {
        val sessionToken = SessionToken(this, ComponentName(this, AudioPlaybackService::class.java))
        controllerFuture = MediaController.Builder(this, sessionToken).buildAsync()
        controllerFuture?.addListener({
            try {
                mediaController = controllerFuture?.get()
                mediaController?.addListener(object : Player.Listener {
                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        isPlayingState.value = isPlaying
                    }

                    override fun onPlaybackStateChanged(playbackState: Int) {
                        durationState.longValue = mediaController?.duration?.coerceAtLeast(0L) ?: 0L
                    }

                    override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                        val mediaId = mediaItem?.mediaId
                        if (mediaId != null) {
                            currentSongState.value = repository.getSongs().find { it.id == mediaId }
                        }
                    }
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, MoreExecutors.directExecutor())
    }

    private fun playSong(song: Song) {
        currentSongState.value = song
        val controller = mediaController ?: return

        val mediaMetadata = MediaMetadata.Builder()
            .setTitle(song.titleMl.ifBlank { song.titleEn })
            .setArtist(song.category)
            .setDisplayTitle(song.titleEn)
            .build()

        val mediaItem = MediaItem.Builder()
            .setMediaId(song.id)
            .setUri(song.streamUrl.ifBlank { song.driveUrl })
            .setMediaMetadata(mediaMetadata)
            .build()

        controller.setMediaItem(mediaItem)
        controller.prepare()
        controller.play()
    }

    private fun togglePlayPause() {
        val controller = mediaController ?: return
        if (controller.isPlaying) {
            controller.pause()
        } else {
            controller.play()
        }
    }

    private fun seekTo(positionMs: Long) {
        mediaController?.seekTo(positionMs)
    }

    private fun skipNext() {
        val current = currentSongState.value ?: return
        val allSongs = repository.getSongs()
        val currentIndex = allSongs.indexOfFirst { it.id == current.id }
        if (currentIndex != -1 && currentIndex + 1 < allSongs.size) {
            playSong(allSongs[currentIndex + 1])
        } else if (allSongs.isNotEmpty()) {
            playSong(allSongs.first())
        }
    }

    private fun skipPrevious() {
        val current = currentSongState.value ?: return
        val allSongs = repository.getSongs()
        val currentIndex = allSongs.indexOfFirst { it.id == current.id }
        if (currentIndex > 0) {
            playSong(allSongs[currentIndex - 1])
        } else if (allSongs.isNotEmpty()) {
            playSong(allSongs.last())
        }
    }

    @Composable
    private fun MainAppScaffold() {
        var currentScreen by activeScreenState
        var isPlayerSheetVisible by remember { mutableStateOf(false) }
        var quizLanguage by remember { mutableStateOf(repository.getQuizLanguage()) }

        // Periodic progress poller
        LaunchedEffect(isPlayingState.value) {
            while (isPlayingState.value) {
                mediaController?.let {
                    currentPositionState.longValue = it.currentPosition.coerceAtLeast(0L)
                    durationState.longValue = it.duration.coerceAtLeast(0L)
                }
                delay(500)
            }
        }

        val moreSubScreens = setOf(
            Screen.PRAYER_WALL,
            Screen.GALLERY,
            Screen.LEADERBOARD,
            Screen.SETTINGS,
            Screen.LEADERSHIP,
            Screen.EVENTS,
            Screen.ABOUT,
            Screen.POLICY
        )

        BackHandler(enabled = currentScreen == Screen.LECTIONARY || currentScreen in moreSubScreens) {
            currentScreen = if (currentScreen == Screen.LECTIONARY) Screen.HOME else Screen.MORE
        }

        Scaffold(
            topBar = {
                if (currentScreen == Screen.QUIZ) {
                    TopAppBar(
                        title = {
                            Text(
                                text = if (quizLanguage == QuizLanguage.ML) "⚡ ബൈബിൾ ക്വിസ്" else "⚡ Bible Quiz",
                                fontWeight = FontWeight.Bold
                            )
                        },
                        actions = {
                            QuizLanguageTogglePill(
                                currentLanguage = quizLanguage,
                                onLanguageSelected = { newLang ->
                                    quizLanguage = newLang
                                    repository.saveQuizLanguage(newLang)
                                },
                                modifier = Modifier.padding(end = 10.dp)
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color(0xFF070416),
                            titleContentColor = Color.White
                        )
                    )
                } else if (currentScreen != Screen.POLICY) {
                    CenterAlignedTopAppBar(
                        navigationIcon = {
                            if (currentScreen == Screen.LECTIONARY || currentScreen in moreSubScreens) {
                                IconButton(
                                    onClick = {
                                        currentScreen = if (currentScreen == Screen.LECTIONARY) Screen.HOME else Screen.MORE
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        },
                        title = {
                            Text(
                                text = when (currentScreen) {
                                    Screen.HOME -> "Keezhillam-Munnar MTYS"
                                    Screen.QUIZ -> "⚡ Bible Quiz"
                                    Screen.LECTIONARY -> "Liturgical Lectionary"
                                    Screen.SONGS -> "Youth Songs"
                                    Screen.PARISHES -> "Centre Parishes"
                                    Screen.PRAYER_WALL -> "Prayer Wall"
                                    Screen.GALLERY -> "Centre Gallery"
                                    Screen.LEADERBOARD -> "Quiz Leaderboard"
                                    Screen.SETTINGS -> "App Settings"
                                    Screen.LEADERSHIP -> "Leadership"
                                    Screen.EVENTS -> "Events & Camps"
                                    Screen.ABOUT -> "About Sakhyam"
                                    Screen.MORE -> "Explore & More"
                                    Screen.POLICY -> "Policies"
                                },
                                fontWeight = FontWeight.Bold
                            )
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            titleContentColor = MaterialTheme.colorScheme.onBackground
                        )
                    )
                }
            },
            bottomBar = {
                Column {
                    // Floating MiniPlayer
                    MiniPlayer(
                        currentSong = currentSongState.value,
                        isPlaying = isPlayingState.value,
                        onPlayPauseToggle = { togglePlayPause() },
                        onOpenFullPlayer = { isPlayerSheetVisible = true },
                        onClose = {
                            mediaController?.stop()
                            currentSongState.value = null
                        }
                    )

                    // Navigation Bar
                    NavigationBar {
                        val navItems = listOf(
                            Screen.HOME,
                            Screen.QUIZ,
                            Screen.SONGS,
                            Screen.PARISHES,
                            Screen.MORE
                        )

                        navItems.forEach { screen ->
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(id = screen.iconRes),
                                        contentDescription = screen.title,
                                        modifier = Modifier.size(24.dp)
                                    )
                                },
                                label = { Text(screen.title) },
                                selected = currentScreen == screen || (screen == Screen.MORE && currentScreen in moreSubScreens),
                                onClick = { currentScreen = screen }
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (currentScreen == Screen.QUIZ) Color(0xFF070416) else MaterialTheme.colorScheme.background)
                    .padding(innerPadding)
            ) {
                when (currentScreen) {
                    Screen.HOME -> HomeScreen(
                        repository = repository,
                        onNavigateToLectionary = { currentScreen = Screen.LECTIONARY },
                        onNavigateToQuiz = { currentScreen = Screen.QUIZ },
                        onNavigateToSongs = { currentScreen = Screen.SONGS },
                        onNavigateToParishes = { currentScreen = Screen.PARISHES },
                        onNavigateToLeadership = { currentScreen = Screen.LEADERSHIP }
                    )
                    Screen.QUIZ -> BibleQuizScreen(
                        repository = repository,
                        language = quizLanguage
                    )
                    Screen.LECTIONARY -> LectionaryScreen(repository = repository)
                    Screen.SONGS -> SongsScreen(
                        repository = repository,
                        currentPlayingSong = currentSongState.value,
                        isPlaying = isPlayingState.value,
                        onSongSelected = { song -> playSong(song) }
                    )
                    Screen.PARISHES -> ParishesScreen(repository = repository)
                    Screen.PRAYER_WALL -> PrayerWallScreen(repository = repository)
                    Screen.GALLERY -> GalleryScreen(repository = repository)
                    Screen.LEADERBOARD -> LeaderboardScreen(repository = repository)
                    Screen.SETTINGS -> SettingsScreen(
                        repository = repository,
                        currentThemeMode = themeModeState.value,
                        onThemeModeChanged = { themeModeState.value = it },
                        currentQuizLanguage = quizLanguage,
                        onQuizLanguageChanged = { quizLanguage = it }
                    )
                    Screen.LEADERSHIP -> LeadershipScreen(repository = repository)
                    Screen.EVENTS -> EventsScreen(repository = repository)
                    Screen.ABOUT -> AboutScreen(
                        repository = repository,
                        onNavigateToPolicy = { currentScreen = Screen.POLICY }
                    )
                    Screen.POLICY -> PolicyScreen(onBack = { currentScreen = Screen.MORE })
                    Screen.MORE -> MoreMenuScreen(
                        repository = repository,
                        onNavigateToPrayerWall = { currentScreen = Screen.PRAYER_WALL },
                        onNavigateToGallery = { currentScreen = Screen.GALLERY },
                        onNavigateToLeaderboard = { currentScreen = Screen.LEADERBOARD },
                        onNavigateToSettings = { currentScreen = Screen.SETTINGS },
                        onNavigateToLeadership = { currentScreen = Screen.LEADERSHIP },
                        onNavigateToEvents = { currentScreen = Screen.EVENTS },
                        onNavigateToAbout = { currentScreen = Screen.ABOUT },
                        onNavigateToPolicy = { currentScreen = Screen.POLICY }
                    )
                }
            }

            // Full Player Modal Sheet
            if (isPlayerSheetVisible && currentSongState.value != null) {
                SongPlayerSheet(
                    song = currentSongState.value!!,
                    isPlaying = isPlayingState.value,
                    currentPositionMs = currentPositionState.longValue,
                    durationMs = durationState.longValue,
                    onPlayPauseToggle = { togglePlayPause() },
                    onSeekTo = { seekTo(it) },
                    onSkipNext = { skipNext() },
                    onSkipPrevious = { skipPrevious() },
                    onToggleFavorite = {
                        val song = currentSongState.value ?: return@SongPlayerSheet
                        repository.toggleFavoriteSong(song.id)
                    },
                    onDismiss = { isPlayerSheetVisible = false }
                )
            }
        }
    }

    override fun onDestroy() {
        controllerFuture?.let {
            MediaController.releaseFuture(it)
        }
        super.onDestroy()
    }
}

@Composable
fun MoreMenuScreen(
    repository: CentreRepository,
    onNavigateToPrayerWall: () -> Unit,
    onNavigateToGallery: () -> Unit,
    onNavigateToLeaderboard: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToLeadership: () -> Unit,
    onNavigateToEvents: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToPolicy: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var lastSyncedText by remember { mutableStateOf(repository.getLastSyncedFormatted()) }
    var isCheckingSync by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Explore Keezhillam - Munnar Centre",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        // 🟢 Live Cloud Status & Last Synced Indicator
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = GoldAccent.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(18.dp)
                ),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF22C55E)) // Green status dot
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Cloud Sync Active",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = Color(0xFF22C55E).copy(alpha = 0.15f)
                            ) {
                                Text(
                                    text = "AUTO",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF22C55E),
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 1.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(3.dp))

                        Text(
                            text = "Last synced: $lastSyncedText",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // Quick refresh icon
                IconButton(
                    onClick = {
                        if (!isCheckingSync) {
                            coroutineScope.launch {
                                isCheckingSync = true
                                repository.syncProgramsFromGoogleSheet()
                                repository.syncLeadersFromGoogleSheet()
                                repository.syncParishesFromGoogleSheet()
                                repository.syncPrayerWallFromGoogleSheet()
                                repository.syncGalleryFromGoogleSheet()
                                repository.syncLeaderboardFromGoogleSheet()
                                lastSyncedText = repository.getLastSyncedFormatted()
                                isCheckingSync = false
                                Toast.makeText(context, "Checked: Up to date", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    modifier = Modifier.size(36.dp)
                ) {
                    if (isCheckingSync) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            color = GoldAccent,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(2.dp))

        MoreMenuItem(
            title = "Prayer Wall & Requests",
            subtitle = "Submit prayer requests anonymously or by parish",
            iconRes = R.drawable.ic_stat_scripture,
            emojiIcon = "🙏",
            onClick = onNavigateToPrayerWall
        )

        MoreMenuItem(
            title = "Centre Photo Gallery",
            subtitle = "C-SHORE Camp, Kalamela & Google Drive memories",
            iconRes = R.drawable.ic_stat_scripture,
            emojiIcon = "📸",
            onClick = onNavigateToGallery
        )

        MoreMenuItem(
            title = "Quiz Leaderboard",
            subtitle = "Centre-wide Bible Quiz scores synced with Google Sheet",
            iconRes = R.drawable.ic_nav_quiz,
            emojiIcon = "🏆",
            onClick = onNavigateToLeaderboard
        )

        MoreMenuItem(
            title = "App Settings",
            subtitle = "Dark / Light / System theme, notifications & cloud sync",
            iconRes = R.drawable.ic_nav_more,
            emojiIcon = "⚙️",
            onClick = onNavigateToSettings
        )

        MoreMenuItem(
            title = "Leadership & Office Bearers",
            subtitle = "President Rev James George, Secretary, Treasurer & Committee",
            iconRes = R.drawable.ic_nav_more,
            onClick = onNavigateToLeadership
        )

        MoreMenuItem(
            title = "Events & Camps",
            subtitle = "C-SHORE Youth Camp & LUMORA Kalamela 2026",
            iconRes = R.drawable.ic_stat_scripture,
            onClick = onNavigateToEvents
        )

        MoreMenuItem(
            title = "About Sakhyam & Developer",
            subtitle = "Diocese history, motto & dev Jeremiah Godly Varghese",
            iconRes = R.drawable.ic_stat_scripture,
            onClick = onNavigateToAbout
        )

        MoreMenuItem(
            title = "Privacy Policy & Terms",
            subtitle = "Google Play compliance, data safety & zero-tracking policy",
            iconRes = R.drawable.ic_stat_scripture,
            onClick = onNavigateToPolicy
        )
    }
}

@Composable
fun MoreMenuItem(
    title: String,
    subtitle: String,
    iconRes: Int,
    emojiIcon: String? = null,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                if (emojiIcon != null) {
                    Text(text = emojiIcon, fontSize = 21.sp)
                } else {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
