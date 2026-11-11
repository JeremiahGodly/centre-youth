package org.marthoma.centreyouth.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.marthoma.centreyouth.data.BibleQuizBank
import org.marthoma.centreyouth.data.CentreRepository
import org.marthoma.centreyouth.data.model.BibleQuizQuestion
import org.marthoma.centreyouth.data.model.QuizLevelInfo
import kotlin.math.ceil
import kotlin.math.cos
import kotlin.math.sin

private enum class QuizStage {
    LEVEL_HUB,
    PLAYING,
    RESULT
}

@Composable
fun BibleQuizScreen(
    repository: CentreRepository,
    modifier: Modifier = Modifier
) {
    var stage by remember { mutableStateOf(QuizStage.LEVEL_HUB) }
    var activeLevel by remember { mutableIntStateOf(1) }

    // Active play state
    var questions by remember { mutableStateOf<List<BibleQuizQuestion>>(emptyList()) }
    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var isTimedOut by remember { mutableStateOf(false) }
    var isAnswered by remember { mutableStateOf(false) }
    var lastEarnedPoints by remember { mutableIntStateOf(0) }

    var score by remember { mutableIntStateOf(0) }
    var correctCount by remember { mutableIntStateOf(0) }
    var currentStreak by remember { mutableIntStateOf(0) }
    var maxStreak by remember { mutableIntStateOf(0) }
    var earnedStars by remember { mutableIntStateOf(0) }
    var isNewHighScore by remember { mutableStateOf(false) }

    // Saved totals refresh trigger
    var refreshTrigger by remember { mutableIntStateOf(0) }

    fun startLevel(levelNum: Int) {
        activeLevel = levelNum.coerceIn(1, 10)
        // Shuffle choices order cleanly or keep authentic question flow per level
        questions = BibleQuizBank.getQuestionsForLevel(activeLevel)
        currentIndex = 0
        selectedOption = null
        isTimedOut = false
        isAnswered = false
        lastEarnedPoints = 0
        score = 0
        correctCount = 0
        currentStreak = 0
        maxStreak = 0
        stage = QuizStage.PLAYING
    }

    val levelInfo = remember(activeLevel) { BibleQuizBank.getLevelInfo(activeLevel) }
    val primaryGlow = Color(levelInfo.primaryColorHex)
    val secondaryGlow = Color(levelInfo.secondaryColorHex)

    // Animated living neon aurora backdrop
    val infiniteTransition = rememberInfiniteTransition(label = "QuizAurora")
    val auroraShift by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(7000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "auroraShift"
    )
    val rainbowSpin by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(5500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rainbowSpin"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF070416),
                        Color(0xFF10082B),
                        Color(0xFF071124)
                    )
                )
            )
    ) {
        // Vibrant Glowing Aurora Orbs Canvas
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = this.size.width
            val h = this.size.height

            val orb1Color = if (stage == QuizStage.LEVEL_HUB) Color(0xFF8B5CF6) else primaryGlow
            val orb2Color = if (stage == QuizStage.LEVEL_HUB) Color(0xFFEC4899) else secondaryGlow
            val orb3Color = Color(0xFF06B6D4)

            val orb1Center = Offset(w * (0.18f + 0.14f * auroraShift), h * (0.14f + 0.08f * auroraShift))
            drawCircle(
                brush = Brush.radialGradient(
                    0.0f to orb1Color.copy(alpha = 0.28f),
                    0.7f to Color.Transparent,
                    center = orb1Center,
                    radius = w * 0.72f
                ),
                radius = w * 0.72f,
                center = orb1Center
            )

            val orb2Center = Offset(w * (0.85f - 0.12f * auroraShift), h * (0.76f - 0.08f * auroraShift))
            drawCircle(
                brush = Brush.radialGradient(
                    0.0f to orb2Color.copy(alpha = 0.26f),
                    0.7f to Color.Transparent,
                    center = orb2Center,
                    radius = w * 0.75f
                ),
                radius = w * 0.75f,
                center = orb2Center
            )

            val orb3Center = Offset(w * (0.50f + 0.15f * (1f - auroraShift)), h * 0.45f)
            drawCircle(
                brush = Brush.radialGradient(
                    0.0f to orb3Color.copy(alpha = 0.16f),
                    0.7f to Color.Transparent,
                    center = orb3Center,
                    radius = w * 0.60f
                ),
                radius = w * 0.60f,
                center = orb3Center
            )
        }

        // Foreground Stage Switcher
        AnimatedContent(
            targetState = stage,
            transitionSpec = {
                fadeIn(tween(320)) togetherWith fadeOut(tween(240))
            },
            label = "QuizStageContent"
        ) { currentStage ->
            when (currentStage) {
                QuizStage.LEVEL_HUB -> {
                    QuizLevelHubView(
                        repository = repository,
                        refreshTrigger = refreshTrigger,
                        rainbowSpin = rainbowSpin,
                        onSelectLevel = { lvl -> startLevel(lvl) }
                    )
                }

                QuizStage.PLAYING -> {
                    val currentQuestion = questions.getOrNull(currentIndex)
                    if (currentQuestion != null) {
                        ActiveQuizQuestionView(
                            levelInfo = levelInfo,
                            question = currentQuestion,
                            questionNumber = currentIndex + 1,
                            totalQuestions = questions.size,
                            score = score,
                            currentStreak = currentStreak,
                            selectedOption = selectedOption,
                            isTimedOut = isTimedOut,
                            isAnswered = isAnswered,
                            lastEarnedPoints = lastEarnedPoints,
                            rainbowSpin = rainbowSpin,
                            onOptionSelected = { chosenIndex, remainingSec ->
                                if (!isAnswered) {
                                    selectedOption = chosenIndex
                                    isAnswered = true
                                    if (chosenIndex == currentQuestion.correctIndex) {
                                        val nextStreak = currentStreak + 1
                                        currentStreak = nextStreak
                                        if (nextStreak > maxStreak) maxStreak = nextStreak
                                        correctCount++

                                        val levelMultiplier = 1.0f + (levelInfo.level - 1) * 0.25f
                                        val timeBonus = ((remainingSec.toFloat() / levelInfo.timePerQuestionSec.toFloat()) * 60f).toInt()
                                        val streakBonus = (nextStreak - 1) * 20
                                        val earned = ((100 + timeBonus + streakBonus) * levelMultiplier).toInt()
                                        lastEarnedPoints = earned
                                        score += earned
                                    } else {
                                        currentStreak = 0
                                        lastEarnedPoints = 0
                                    }
                                }
                            },
                            onTimedOut = {
                                if (!isAnswered) {
                                    isTimedOut = true
                                    isAnswered = true
                                    currentStreak = 0
                                    lastEarnedPoints = 0
                                }
                            },
                            onNextQuestion = {
                                if (currentIndex + 1 < questions.size) {
                                    currentIndex++
                                    selectedOption = null
                                    isTimedOut = false
                                    isAnswered = false
                                    lastEarnedPoints = 0
                                } else {
                                    val prevBest = repository.getQuizBestScore(activeLevel)
                                    isNewHighScore = score > prevBest && score > 0
                                    earnedStars = repository.saveQuizLevelResult(
                                        level = activeLevel,
                                        score = score,
                                        correctCount = correctCount,
                                        totalCount = questions.size
                                    )
                                    refreshTrigger++
                                    stage = QuizStage.RESULT
                                }
                            },
                            onExitQuiz = { stage = QuizStage.LEVEL_HUB }
                        )
                    }
                }

                QuizStage.RESULT -> {
                    QuizLevelResultView(
                        levelInfo = levelInfo,
                        score = score,
                        correctCount = correctCount,
                        totalQuestions = questions.size,
                        maxStreak = maxStreak,
                        earnedStars = earnedStars,
                        isNewHighScore = isNewHighScore,
                        rainbowSpin = rainbowSpin,
                        onNextLevel = {
                            if (activeLevel < 10) {
                                startLevel(activeLevel + 1)
                            } else {
                                stage = QuizStage.LEVEL_HUB
                            }
                        },
                        onRetryLevel = { startLevel(activeLevel) },
                        onBackToHub = { stage = QuizStage.LEVEL_HUB }
                    )
                }
            }
        }
    }
}

@Composable
private fun QuizLevelHubView(
    repository: CentreRepository,
    refreshTrigger: Int,
    rainbowSpin: Float,
    onSelectLevel: (Int) -> Unit
) {
    val totalStars = remember(refreshTrigger) { repository.getQuizTotalStars() }
    val totalScore = remember(refreshTrigger) { repository.getQuizTotalScore() }

    // Recommend the first level without 3 stars, or level 1
    val recommendedLevel = remember(refreshTrigger) {
        (1..10).firstOrNull { repository.getQuizStars(it) < 3 } ?: 1
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Colorful Glowing Arena Banner
        item {
            val rainbowColors = listOf(
                Color(0xFF06B6D4),
                Color(0xFF8B5CF6),
                Color(0xFFEC4899),
                Color(0xFFF59E0B),
                Color(0xFF10B981),
                Color(0xFF06B6D4)
            )
            val rad = rainbowSpin * (Math.PI / 180.0)
            val borderBrush = Brush.linearGradient(
                colors = rainbowColors,
                start = Offset((cos(rad) * 350f).toFloat() + 350f, (sin(rad) * 200f).toFloat() + 200f),
                end = Offset((-cos(rad) * 350f).toFloat() + 350f, (-sin(rad) * 200f).toFloat() + 200f)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 16.dp,
                        shape = RoundedCornerShape(24.dp),
                        ambientColor = Color(0xFF8B5CF6),
                        spotColor = Color(0xFFEC4899)
                    )
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFF130D2E).copy(alpha = 0.90f))
                    .border(2.dp, borderBrush, RoundedCornerShape(24.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        color = Color(0xFFF59E0B).copy(alpha = 0.18f),
                        border = BorderStroke(1.dp, Color(0xFFFBBF24).copy(alpha = 0.6f)),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "⚡ 120 QUESTIONS • 10 LEVELS ⚡",
                            color = Color(0xFFFDE68A),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 0.8.sp,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Bible Quiz Arena",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Progressive difficulty from Level 1 (Easy) to Level 10 (Ultra Hard) with tighter countdown timers!",
                        color = Color(0xFFCBD5E1),
                        fontSize = 12.5.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 17.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Stars & Total Score Summary Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        ArenaStatPill(
                            label = "STARS EARNED",
                            value = "⭐ $totalStars / 30",
                            accent = Color(0xFFFBBF24),
                            modifier = Modifier.weight(1f)
                        )
                        ArenaStatPill(
                            label = "TOTAL SCORE",
                            value = "🏆 $totalScore",
                            accent = Color(0xFF22D3EE),
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Primary Gradient Play Button
                    Button(
                        onClick = { onSelectLevel(recommendedLevel) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(
                                            Color(0xFF7C3AED),
                                            Color(0xFFEC4899),
                                            Color(0xFFF59E0B)
                                        )
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Play Level $recommendedLevel Now",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        // Section Label
        item {
            Text(
                text = "Select Difficulty Level (1 → 10)",
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // 10 Glowing Level Cards
        items(BibleQuizBank.levels) { lvl ->
            val bestScore = remember(refreshTrigger, lvl.level) {
                repository.getQuizBestScore(lvl.level)
            }
            val stars = remember(refreshTrigger, lvl.level) {
                repository.getQuizStars(lvl.level)
            }
            GlowingLevelCard(
                levelInfo = lvl,
                bestScore = bestScore,
                stars = stars,
                onClick = { onSelectLevel(lvl.level) }
            )
        }
    }
}

@Composable
private fun ArenaStatPill(
    label: String,
    value: String,
    accent: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(accent.copy(alpha = 0.10f))
            .border(1.dp, accent.copy(alpha = 0.35f), RoundedCornerShape(14.dp))
            .padding(vertical = 10.dp, horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            color = accent.copy(alpha = 0.9f),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = value,
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
private fun GlowingLevelCard(
    levelInfo: QuizLevelInfo,
    bestScore: Int,
    stars: Int,
    onClick: () -> Unit
) {
    val c1 = Color(levelInfo.primaryColorHex)
    val c2 = Color(levelInfo.secondaryColorHex)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = c1,
                spotColor = c2
            )
            .clip(RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .border(
                width = 1.5.dp,
                brush = Brush.horizontalGradient(listOf(c1.copy(alpha = 0.85f), c2.copy(alpha = 0.85f))),
                shape = RoundedCornerShape(20.dp)
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF130E29).copy(alpha = 0.92f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Circular Glowing Level Number Badge
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .shadow(8.dp, CircleShape, ambientColor = c1, spotColor = c1)
                    .clip(CircleShape)
                    .background(Brush.linearGradient(listOf(c1, c2))),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "LVL",
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${levelInfo.level}",
                        color = Color.White,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = levelInfo.title,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Surface(
                        color = c1.copy(alpha = 0.22f),
                        border = BorderStroke(1.dp, c1.copy(alpha = 0.65f)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = levelInfo.difficultyLabel,
                            color = c1,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = levelInfo.subtitle,
                    color = Color(0xFF94A3B8),
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "⏱️ ${levelInfo.timePerQuestionSec}s/Q",
                        color = Color(0xFF38BDF8),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "• 12 Qs",
                        color = Color(0xFFCBD5E1),
                        fontSize = 11.sp
                    )
                    if (bestScore > 0) {
                        Text(
                            text = "• Best: $bestScore",
                            color = Color(0xFFFBBF24),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // 3 Stars Rating Column
            Column(horizontalAlignment = Alignment.End) {
                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                    for (i in 1..3) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = if (i <= stars) Color(0xFFFBBF24) else Color(0xFF334155),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Start Level",
                    tint = c2,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
private fun ActiveQuizQuestionView(
    levelInfo: QuizLevelInfo,
    question: BibleQuizQuestion,
    questionNumber: Int,
    totalQuestions: Int,
    score: Int,
    currentStreak: Int,
    selectedOption: Int?,
    isTimedOut: Boolean,
    isAnswered: Boolean,
    lastEarnedPoints: Int,
    rainbowSpin: Float,
    onOptionSelected: (Int, Int) -> Unit,
    onTimedOut: () -> Unit,
    onNextQuestion: () -> Unit,
    onExitQuiz: () -> Unit
) {
    val c1 = Color(levelInfo.primaryColorHex)
    val c2 = Color(levelInfo.secondaryColorHex)

    val totalTimeMs = levelInfo.timePerQuestionSec * 1000L
    var timeLeftMs by remember(question.id) { mutableLongStateOf(totalTimeMs) }

    // Smooth per-question countdown timer (ticks cleanly every 50ms while unanswered)
    LaunchedEffect(question.id, isAnswered) {
        if (!isAnswered) {
            while (timeLeftMs > 0L) {
                delay(50L)
                timeLeftMs = (timeLeftMs - 50L).coerceAtLeast(0L)
            }
            if (timeLeftMs == 0L) {
                onTimedOut()
            }
        }
    }

    val progressFraction = (timeLeftMs.toFloat() / totalTimeMs.toFloat()).coerceIn(0f, 1f)
    val secondsRemaining = ceil(timeLeftMs / 1000.0).toInt().coerceAtLeast(0)
    val isUrgent = !isAnswered && secondsRemaining <= 4

    // Pulse animation when timer enters urgent red zone
    val infiniteTransition = rememberInfiniteTransition(label = "UrgentTimerPulse")
    val urgentScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = if (isUrgent) 1.12f else 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(360, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "urgentScale"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 110.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Top Control & Stats Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = onExitQuiz,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.09f))
                    .border(1.dp, Color.White.copy(alpha = 0.18f), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back to Levels",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }

            // Level Indicator Pill
            Surface(
                color = c1.copy(alpha = 0.18f),
                border = BorderStroke(1.5.dp, Brush.horizontalGradient(listOf(c1, c2))),
                shape = CircleShape
            ) {
                Text(
                    text = "LVL ${levelInfo.level} • ${levelInfo.difficultyLabel.uppercase()}",
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 0.5.sp,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                )
            }

            // Score + Streak Pill
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                if (currentStreak >= 2) {
                    Surface(
                        color = Color(0xFFF97316).copy(alpha = 0.25f),
                        border = BorderStroke(1.dp, Color(0xFFFBBF24)),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "🔥${currentStreak}x",
                            color = Color(0xFFFDE68A),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Surface(
                    color = Color(0xFF1E293B),
                    border = BorderStroke(1.dp, Color(0xFF38BDF8).copy(alpha = 0.6f)),
                    shape = CircleShape
                ) {
                    Text(
                        text = "🏆 $score",
                        color = Color(0xFF38BDF8),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 2. Question Progress Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Question $questionNumber of $totalQuestions",
                color = Color(0xFFCBD5E1),
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = levelInfo.title,
                color = c2,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Glowing Gradient Progress Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.12f))
        ) {
            val barProgress by animateFloatAsState(
                targetValue = questionNumber.toFloat() / totalQuestions.toFloat(),
                animationSpec = tween(400),
                label = "questionBar"
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(barProgress)
                    .fillMaxHeight()
                    .clip(CircleShape)
                    .background(Brush.horizontalGradient(listOf(c1, c2, Color(0xFFFBBF24))))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 3. Glowing Circular Difficulty Countdown Timer
        val timerColor = when {
            isTimedOut -> Color(0xFFEF4444)
            isAnswered -> Color(0xFF10B981)
            isUrgent -> Color(0xFFEF4444)
            secondsRemaining <= (levelInfo.timePerQuestionSec * 0.45f) -> Color(0xFFF59E0B)
            else -> c1
        }

        Box(
            modifier = Modifier
                .size(84.dp)
                .graphicsLayer {
                    scaleX = urgentScale
                    scaleY = urgentScale
                },
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val radius = this.size.minDimension * 0.46f
                val strokeWidth = 7.dp.toPx()

                // Soft glowing aura halo behind ring
                drawCircle(
                    brush = Brush.radialGradient(
                        0.0f to timerColor.copy(alpha = 0.35f),
                        0.85f to Color.Transparent,
                        center = center,
                        radius = radius * 1.4f
                    ),
                    radius = radius * 1.4f,
                    center = center
                )

                // Dim track ring
                drawCircle(
                    color = Color.White.copy(alpha = 0.14f),
                    radius = radius,
                    style = Stroke(width = strokeWidth)
                )

                // Glowing active arc
                val topLeftOffset = Offset(center.x - radius, center.y - radius)
                val arcSize = Size(radius * 2f, radius * 2f)
                drawArc(
                    brush = Brush.sweepGradient(
                        listOf(timerColor, c2, timerColor)
                    ),
                    startAngle = -90f,
                    sweepAngle = 360f * progressFraction,
                    useCenter = false,
                    topLeft = topLeftOffset,
                    size = arcSize,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "${secondsRemaining}s",
                    color = if (isUrgent) Color(0xFFFCA5A5) else Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 4. Colorful Glowing Question Window Card (Animated one-by-one transition)
        AnimatedContent(
            targetState = question,
            transitionSpec = {
                (fadeIn(tween(300)) + slideInHorizontally { width -> width / 3 }) togetherWith
                        (fadeOut(tween(200)) + slideOutHorizontally { width -> -width / 3 })
            },
            label = "QuestionWindowTransition"
        ) { currentQ ->
            Column(modifier = Modifier.fillMaxWidth()) {
                val qRad = rainbowSpin * (Math.PI / 180.0)
                val neonBorder = Brush.linearGradient(
                    colors = listOf(c1, Color(0xFF06B6D4), c2, Color(0xFFFBBF24), c1),
                    start = Offset((cos(qRad) * 350f).toFloat() + 350f, (sin(qRad) * 200f).toFloat() + 200f),
                    end = Offset((-cos(qRad) * 350f).toFloat() + 350f, (-sin(qRad) * 200f).toFloat() + 200f)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 18.dp,
                            shape = RoundedCornerShape(24.dp),
                            ambientColor = c1,
                            spotColor = c2
                        )
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color(0xFF140D32).copy(alpha = 0.94f))
                        .border(2.dp, neonBorder, RoundedCornerShape(24.dp))
                        .padding(22.dp)
                ) {
                    Text(
                        text = currentQ.question,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 25.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                // 5. Four Colorful Choice Buttons (A, B, C, D)
                val optionColors = listOf(
                    Color(0xFF06B6D4), // A: Cyan
                    Color(0xFF8B5CF6), // B: Violet
                    Color(0xFFEC4899), // C: Pink
                    Color(0xFFF59E0B)  // D: Amber Gold
                )
                val optionLetters = listOf("A", "B", "C", "D")

                currentQ.options.forEachIndexed { idx, optionText ->
                    val isThisCorrect = idx == currentQ.correctIndex
                    val isThisSelected = selectedOption == idx

                    val emerald = Color(0xFF10B981)
                    val crimson = Color(0xFFF43F5E)
                    val letterAccent = optionColors[idx % optionColors.size]

                    val cardBg = when {
                        !isAnswered -> Color(0xFF1A143B).copy(alpha = 0.85f)
                        isThisCorrect -> emerald.copy(alpha = 0.24f)
                        isThisSelected && !isThisCorrect -> crimson.copy(alpha = 0.24f)
                        else -> Color(0xFF120E29).copy(alpha = 0.50f)
                    }

                    val borderColor = when {
                        !isAnswered -> letterAccent.copy(alpha = 0.55f)
                        isThisCorrect -> emerald
                        isThisSelected && !isThisCorrect -> crimson
                        else -> Color.White.copy(alpha = 0.10f)
                    }

                    val badgeBg = when {
                        !isAnswered -> letterAccent
                        isThisCorrect -> emerald
                        isThisSelected && !isThisCorrect -> crimson
                        else -> Color(0xFF334155)
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .shadow(
                                elevation = if (isAnswered && (isThisCorrect || isThisSelected)) 12.dp else 4.dp,
                                shape = RoundedCornerShape(16.dp),
                                ambientColor = borderColor,
                                spotColor = borderColor
                            )
                            .clip(RoundedCornerShape(16.dp))
                            .clickable(enabled = !isAnswered) {
                                onOptionSelected(idx, secondsRemaining)
                            }
                            .border(
                                width = if (isAnswered && (isThisCorrect || isThisSelected)) 2.dp else 1.2.dp,
                                color = borderColor,
                                shape = RoundedCornerShape(16.dp)
                            ),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = cardBg)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(CircleShape)
                                    .background(badgeBg),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = optionLetters[idx],
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = optionText,
                                color = if (!isAnswered || isThisCorrect || isThisSelected) Color.White else Color(0xFF94A3B8),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.weight(1f),
                                lineHeight = 20.sp
                            )

                            if (isAnswered && isThisCorrect) {
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Correct",
                                    tint = emerald,
                                    modifier = Modifier.size(22.dp)
                                )
                            } else if (isAnswered && isThisSelected && !isThisCorrect) {
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Wrong",
                                    tint = crimson,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }
                    }
                }

                // 6. Scripture Feedback & Next Question Action
                AnimatedVisibility(
                    visible = isAnswered,
                    enter = fadeIn(tween(250)) + expandVertically(),
                    exit = fadeOut()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 14.dp)
                    ) {
                        val isCorrect = selectedOption == currentQ.correctIndex
                        val bannerColor = when {
                            isTimedOut -> Color(0xFFF59E0B)
                            isCorrect -> Color(0xFF10B981)
                            else -> Color(0xFFF43F5E)
                        }

                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = bannerColor.copy(alpha = 0.16f),
                            border = BorderStroke(1.dp, bannerColor.copy(alpha = 0.55f)),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 14.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = when {
                                        isTimedOut -> "⏰ Time's Up!"
                                        isCorrect -> "✨ Correct! +$lastEarnedPoints pts"
                                        else -> "❌ Incorrect"
                                    },
                                    color = bannerColor,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )

                                Surface(
                                    color = Color(0xFF0F172A).copy(alpha = 0.7f),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text(
                                        text = "📖 ${currentQ.verseRef}",
                                        color = Color(0xFFFDE68A),
                                        fontSize = 11.5.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = onNextQuestion,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            contentPadding = PaddingValues(0.dp),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Brush.horizontalGradient(listOf(c1, c2))),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = if (questionNumber < totalQuestions) "Next Question" else "Finish Level 🏆",
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                    if (questionNumber < totalQuestions) {
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun QuizLevelResultView(
    levelInfo: QuizLevelInfo,
    score: Int,
    correctCount: Int,
    totalQuestions: Int,
    maxStreak: Int,
    earnedStars: Int,
    isNewHighScore: Boolean,
    rainbowSpin: Float,
    onNextLevel: () -> Unit,
    onRetryLevel: () -> Unit,
    onBackToHub: () -> Unit
) {
    val c1 = Color(levelInfo.primaryColorHex)
    val c2 = Color(levelInfo.secondaryColorHex)
    val accuracy = if (totalQuestions > 0) (correctCount * 100 / totalQuestions) else 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(start = 20.dp, end = 20.dp, top = 24.dp, bottom = 110.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Rotating Halo Emblem
        Box(
            modifier = Modifier.size(110.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                rotate(degrees = rainbowSpin, pivot = center) {
                    drawCircle(
                        brush = Brush.sweepGradient(listOf(c1, Color(0xFFFBBF24), c2, c1)),
                        radius = this.size.minDimension * 0.45f,
                        style = Stroke(width = 6.dp.toPx(), cap = StrokeCap.Round)
                    )
                }
                drawCircle(
                    brush = Brush.radialGradient(
                        0.0f to c1.copy(alpha = 0.35f),
                        0.85f to Color.Transparent
                    ),
                    radius = this.size.minDimension * 0.5f
                )
            }
            Text(
                text = when {
                    earnedStars == 3 -> "👑"
                    earnedStars >= 1 -> "🏆"
                    else -> "📖"
                },
                fontSize = 42.sp
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        if (isNewHighScore) {
            Surface(
                color = Color(0xFFF59E0B).copy(alpha = 0.22f),
                border = BorderStroke(1.dp, Color(0xFFFBBF24)),
                shape = CircleShape
            ) {
                Text(
                    text = "✨ NEW HIGH SCORE! ✨",
                    color = Color(0xFFFDE68A),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        Text(
            text = "Level ${levelInfo.level} Complete!",
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "${levelInfo.title} • ${levelInfo.difficultyLabel}",
            color = c2,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Stars Display
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 1..3) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = if (i <= earnedStars) Color(0xFFFBBF24) else Color(0xFF334155),
                    modifier = Modifier.size(42.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Summary Stats Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(16.dp, RoundedCornerShape(22.dp), ambientColor = c1, spotColor = c2)
                .clip(RoundedCornerShape(22.dp))
                .background(Color(0xFF140D32).copy(alpha = 0.92f))
                .border(1.5.dp, Brush.horizontalGradient(listOf(c1, c2)), RoundedCornerShape(22.dp))
                .padding(20.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ResultStatRow("Final Score", "🏆 $score pts", Color(0xFF38BDF8))
                HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
                ResultStatRow("Correct Answers", "$correctCount / $totalQuestions ($accuracy%)", Color(0xFF10B981))
                HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
                ResultStatRow("Best Streak", "🔥 ${maxStreak}x", Color(0xFFFBBF24))
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        // Next Level Primary Button
        if (levelInfo.level < 10) {
            Button(
                onClick = onNextLevel,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Brush.horizontalGradient(listOf(c1, c2))),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Continue to Level ${levelInfo.level + 1} ⚡",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        } else {
            Surface(
                color = Color(0xFFF59E0B).copy(alpha = 0.2f),
                border = BorderStroke(1.dp, Color(0xFFFBBF24)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "👑 You reached Omega Grandmaster (Level 10)!",
                    color = Color(0xFFFDE68A),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(14.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(
                onClick = onRetryLevel,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.2.dp, c1)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("Retry", color = Color.White, fontWeight = FontWeight.Bold)
            }

            OutlinedButton(
                onClick = onBackToHub,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.2.dp, Color.White.copy(alpha = 0.35f))
            ) {
                Text("All Levels", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun ResultStatRow(label: String, value: String, accent: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = Color(0xFF94A3B8),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            color = accent,
            fontSize = 17.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}
