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
import org.marthoma.centreyouth.data.model.QuizLanguage
import org.marthoma.centreyouth.data.model.QuizLevelInfo
import kotlin.math.ceil
import kotlin.math.cos
import kotlin.math.sin

private enum class QuizStage {
    LEVEL_HUB,
    PLAYING,
    RESULT
}

/**
 * Glowing segmented language toggle button (EN / ML / Both) placed directly in the Quiz Header
 * so players can switch languages at any moment during Level Hub, Gameplay, or Results.
 */
@Composable
fun QuizLanguageTogglePill(
    currentLanguage: QuizLanguage,
    onLanguageSelected: (QuizLanguage) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(Color(0xFF140D32))
            .border(1.dp, Color(0xFF8B5CF6).copy(alpha = 0.6f), CircleShape)
            .padding(3.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        QuizLanguage.entries.forEach { lang ->
            val isSelected = currentLanguage == lang
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .then(
                        if (isSelected) {
                            Modifier.background(
                                Brush.horizontalGradient(
                                    listOf(Color(0xFF7C3AED), Color(0xFFEC4899))
                                )
                            )
                        } else {
                            Modifier.background(Color.Transparent)
                        }
                    )
                    .clickable { onLanguageSelected(lang) }
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = lang.label,
                    color = if (isSelected) Color.White else Color(0xFF94A3B8),
                    fontSize = 11.5.sp,
                    fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun BibleQuizScreen(
    repository: CentreRepository,
    language: QuizLanguage,
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
                        language = language,
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
                            language = language,
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
                        language = language,
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
    language: QuizLanguage,
    refreshTrigger: Int,
    rainbowSpin: Float,
    onSelectLevel: (Int) -> Unit
) {
    val totalStars = remember(refreshTrigger) { repository.getQuizTotalStars() }
    val totalScore = remember(refreshTrigger) { repository.getQuizTotalScore() }
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
                        val badgeText = when (language) {
                            QuizLanguage.EN -> "⚡ 120 QUESTIONS • 10 LEVELS ⚡"
                            QuizLanguage.ML -> "⚡ 120 ചോദ്യങ്ങൾ • 10 ലെവലുകൾ ⚡"
                            QuizLanguage.BOTH -> "⚡ 120 QUESTIONS • 120 ചോദ്യങ്ങൾ ⚡"
                        }
                        Text(
                            text = badgeText,
                            color = Color(0xFFFDE68A),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 0.6.sp,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    when (language) {
                        QuizLanguage.EN -> {
                            Text(
                                text = "Bible Quiz Arena",
                                color = Color.White,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.Center
                            )
                        }
                        QuizLanguage.ML -> {
                            Text(
                                text = "ബൈബിൾ ക്വിസ് അരീന",
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.Center
                            )
                        }
                        QuizLanguage.BOTH -> {
                            Text(
                                text = "Bible Quiz Arena",
                                color = Color.White,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "ബൈബിൾ ക്വിസ് അരീന",
                                color = Color(0xFF38BDF8),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    if (language != QuizLanguage.ML) {
                        Text(
                            text = "Progressive difficulty from Level 1 (Easy) to Level 10 (Ultra Hard) with tighter countdown timers!",
                            color = Color(0xFFCBD5E1),
                            fontSize = 12.5.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 17.sp
                        )
                    }
                    if (language != QuizLanguage.EN) {
                        Text(
                            text = "ലെവൽ 1 (ലളിതം) മുതൽ ലെവൽ 10 (മഹാ കഠിനം) വരെ സമയപരിധിയോടെയുള്ള മത്സരം!",
                            color = if (language == QuizLanguage.BOTH) Color(0xFF94A3B8) else Color(0xFFCBD5E1),
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 17.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        ArenaStatPill(
                            label = when (language) {
                                QuizLanguage.EN -> "STARS EARNED"
                                QuizLanguage.ML -> "നേടിയ നക്ഷത്രങ്ങൾ"
                                QuizLanguage.BOTH -> "STARS • നക്ഷത്രങ്ങൾ"
                            },
                            value = "⭐ $totalStars / 30",
                            accent = Color(0xFFFBBF24),
                            modifier = Modifier.weight(1f)
                        )
                        ArenaStatPill(
                            label = when (language) {
                                QuizLanguage.EN -> "TOTAL SCORE"
                                QuizLanguage.ML -> "ആകെ സ്കോർ"
                                QuizLanguage.BOTH -> "SCORE • സ്കോർ"
                            },
                            value = "🏆 $totalScore",
                            accent = Color(0xFF22D3EE),
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

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
                                    text = when (language) {
                                        QuizLanguage.EN -> "Play Level $recommendedLevel Now"
                                        QuizLanguage.ML -> "ലെവൽ $recommendedLevel തുടങ്ങുക"
                                        QuizLanguage.BOTH -> "Play Level $recommendedLevel • ലെവൽ $recommendedLevel തുടങ്ങുക"
                                    },
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
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
                text = when (language) {
                    QuizLanguage.EN -> "Select Difficulty Level (1 → 10)"
                    QuizLanguage.ML -> "ലെവൽ തിരഞ്ഞെടുക്കുക (1 → 10)"
                    QuizLanguage.BOTH -> "Select Level • ലെവൽ തിരഞ്ഞെടുക്കുക (1 → 10)"
                },
                color = Color.White,
                fontSize = 16.sp,
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
                language = language,
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
            .padding(vertical = 10.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            color = accent.copy(alpha = 0.9f),
            fontSize = 9.5.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.4.sp,
            textAlign = TextAlign.Center
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
    language: QuizLanguage,
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
                        text = if (language == QuizLanguage.ML) "ലെവൽ" else "LVL",
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 8.5.sp,
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
                    val primaryTitle = if (language == QuizLanguage.ML) levelInfo.titleMl else levelInfo.title
                    Text(
                        text = primaryTitle,
                        color = Color.White,
                        fontSize = 15.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Surface(
                        color = c1.copy(alpha = 0.22f),
                        border = BorderStroke(1.dp, c1.copy(alpha = 0.65f)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        val diffTag = when (language) {
                            QuizLanguage.EN -> levelInfo.difficultyLabel
                            QuizLanguage.ML -> levelInfo.difficultyLabelMl
                            QuizLanguage.BOTH -> "${levelInfo.difficultyLabel} • ${levelInfo.difficultyLabelMl}"
                        }
                        Text(
                            text = diffTag,
                            color = c1,
                            fontSize = 9.5.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                if (language == QuizLanguage.BOTH) {
                    Text(
                        text = levelInfo.titleMl,
                        color = Color(0xFFE0F2FE),
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = when (language) {
                        QuizLanguage.EN -> levelInfo.subtitle
                        QuizLanguage.ML -> levelInfo.subtitleMl
                        QuizLanguage.BOTH -> "${levelInfo.subtitle} • ${levelInfo.subtitleMl}"
                    },
                    color = Color(0xFF94A3B8),
                    fontSize = 11.5.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = if (language == QuizLanguage.ML) "⏱️ ${levelInfo.timePerQuestionSec} സെ." else "⏱️ ${levelInfo.timePerQuestionSec}s/Q",
                        color = Color(0xFF38BDF8),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = if (language == QuizLanguage.ML) "• 12 ചോദ്യം" else "• 12 Qs",
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
    language: QuizLanguage,
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

            Surface(
                color = c1.copy(alpha = 0.18f),
                border = BorderStroke(1.5.dp, Brush.horizontalGradient(listOf(c1, c2))),
                shape = CircleShape
            ) {
                val lvlTag = when (language) {
                    QuizLanguage.EN -> "LVL ${levelInfo.level} • ${levelInfo.difficultyLabel.uppercase()}"
                    QuizLanguage.ML -> "ലെവൽ ${levelInfo.level} • ${levelInfo.difficultyLabelMl}"
                    QuizLanguage.BOTH -> "LVL ${levelInfo.level} • ${levelInfo.difficultyLabelMl}"
                }
                Text(
                    text = lvlTag,
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 0.4.sp,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                )
            }

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
                text = when (language) {
                    QuizLanguage.EN -> "Question $questionNumber of $totalQuestions"
                    QuizLanguage.ML -> "ചോദ്യം $questionNumber / $totalQuestions"
                    QuizLanguage.BOTH -> "Q$questionNumber/$totalQuestions • ചോദ്യം $questionNumber"
                },
                color = Color(0xFFCBD5E1),
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = when (language) {
                    QuizLanguage.EN -> levelInfo.title
                    QuizLanguage.ML -> levelInfo.titleMl
                    QuizLanguage.BOTH -> "${levelInfo.title} • ${levelInfo.titleMl}"
                },
                color = c2,
                fontSize = 11.5.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

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

        Spacer(modifier = Modifier.height(14.dp))

        // 3. Glowing Circular Countdown Timer
        val timerColor = when {
            isTimedOut -> Color(0xFFEF4444)
            isAnswered -> Color(0xFF10B981)
            isUrgent -> Color(0xFFEF4444)
            secondsRemaining <= (levelInfo.timePerQuestionSec * 0.45f) -> Color(0xFFF59E0B)
            else -> c1
        }

        Box(
            modifier = Modifier
                .size(80.dp)
                .graphicsLayer {
                    scaleX = urgentScale
                    scaleY = urgentScale
                },
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val radius = this.size.minDimension * 0.46f
                val strokeWidth = 6.5.dp.toPx()

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

                drawCircle(
                    color = Color.White.copy(alpha = 0.14f),
                    radius = radius,
                    style = Stroke(width = strokeWidth)
                )

                val topLeftOffset = Offset(center.x - radius, center.y - radius)
                val arcSize = Size(radius * 2f, radius * 2f)
                drawArc(
                    brush = Brush.sweepGradient(listOf(timerColor, c2, timerColor)),
                    startAngle = -90f,
                    sweepAngle = 360f * progressFraction,
                    useCenter = false,
                    topLeft = topLeftOffset,
                    size = arcSize,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
            }

            Text(
                text = "${secondsRemaining}s",
                color = if (isUrgent) Color(0xFFFCA5A5) else Color.White,
                fontSize = 21.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 4. Colorful Glowing Question Window Card
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
                        .padding(20.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (language != QuizLanguage.ML) {
                            Text(
                                text = currentQ.question,
                                color = Color.White,
                                fontSize = if (language == QuizLanguage.BOTH) 16.5.sp else 18.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 23.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        if (language == QuizLanguage.BOTH) {
                            Spacer(modifier = Modifier.height(10.dp))
                            HorizontalDivider(
                                color = c1.copy(alpha = 0.35f),
                                modifier = Modifier.fillMaxWidth(0.45f)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }

                        if (language != QuizLanguage.EN) {
                            Text(
                                text = currentQ.questionMl,
                                color = if (language == QuizLanguage.BOTH) Color(0xFFE0F2FE) else Color.White,
                                fontSize = if (language == QuizLanguage.BOTH) 14.5.sp else 17.sp,
                                fontWeight = if (language == QuizLanguage.BOTH) FontWeight.SemiBold else FontWeight.Bold,
                                lineHeight = 21.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 5. Four Colorful Choice Buttons (A, B, C, D)
                val optionColors = listOf(
                    Color(0xFF06B6D4), // A: Cyan
                    Color(0xFF8B5CF6), // B: Violet
                    Color(0xFFEC4899), // C: Pink
                    Color(0xFFF59E0B)  // D: Amber Gold
                )
                val optionLetters = listOf("A", "B", "C", "D")

                currentQ.options.forEachIndexed { idx, optionTextEn ->
                    val optionTextMl = currentQ.optionsMl.getOrElse(idx) { optionTextEn }
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
                                .padding(horizontal = 14.dp, vertical = 12.dp),
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

                            Column(modifier = Modifier.weight(1f)) {
                                val textDim = isAnswered && !isThisCorrect && !isThisSelected
                                if (language != QuizLanguage.ML) {
                                    Text(
                                        text = optionTextEn,
                                        color = if (!textDim) Color.White else Color(0xFF94A3B8),
                                        fontSize = 14.5.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        lineHeight = 19.sp
                                    )
                                }
                                if (language != QuizLanguage.EN) {
                                    Text(
                                        text = optionTextMl,
                                        color = when {
                                            textDim -> Color(0xFF64748B)
                                            language == QuizLanguage.BOTH -> Color(0xFFBAE6FD)
                                            else -> Color.White
                                        },
                                        fontSize = if (language == QuizLanguage.BOTH) 13.sp else 14.5.sp,
                                        fontWeight = if (language == QuizLanguage.BOTH) FontWeight.Medium else FontWeight.SemiBold,
                                        lineHeight = 18.sp
                                    )
                                }
                            }

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
                            .padding(top = 12.dp)
                    ) {
                        val isCorrect = selectedOption == currentQ.correctIndex
                        val bannerColor = when {
                            isTimedOut -> Color(0xFFF59E0B)
                            isCorrect -> Color(0xFF10B981)
                            else -> Color(0xFFF43F5E)
                        }

                        val statusMessage = when {
                            isTimedOut -> when (language) {
                                QuizLanguage.EN -> "⏰ Time's Up!"
                                QuizLanguage.ML -> "⏰ സമയം കഴിഞ്ഞു!"
                                QuizLanguage.BOTH -> "⏰ Time's Up! • സമയം കഴിഞ്ഞു!"
                            }
                            isCorrect -> when (language) {
                                QuizLanguage.EN -> "✨ Correct! +$lastEarnedPoints pts"
                                QuizLanguage.ML -> "✨ ശരിയാണ്! +$lastEarnedPoints"
                                QuizLanguage.BOTH -> "✨ Correct! / ശരിയാണ്! +$lastEarnedPoints"
                            }
                            else -> when (language) {
                                QuizLanguage.EN -> "❌ Incorrect"
                                QuizLanguage.ML -> "❌ തെറ്റി"
                                QuizLanguage.BOTH -> "❌ Incorrect • തെറ്റി"
                            }
                        }

                        val refText = when (language) {
                            QuizLanguage.EN -> "📖 ${currentQ.verseRef}"
                            QuizLanguage.ML -> "📖 ${currentQ.verseRefMl}"
                            QuizLanguage.BOTH -> "📖 ${currentQ.verseRef} • ${currentQ.verseRefMl}"
                        }

                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = bannerColor.copy(alpha = 0.16f),
                            border = BorderStroke(1.dp, bannerColor.copy(alpha = 0.55f)),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 14.dp, vertical = 10.dp)
                            ) {
                                Text(
                                    text = statusMessage,
                                    color = bannerColor,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Surface(
                                    color = Color(0xFF0F172A).copy(alpha = 0.75f),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text(
                                        text = refText,
                                        color = Color(0xFFFDE68A),
                                        fontSize = 11.5.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
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
                                    val btnLabel = if (questionNumber < totalQuestions) {
                                        when (language) {
                                            QuizLanguage.EN -> "Next Question"
                                            QuizLanguage.ML -> "അടുത്ത ചോദ്യം"
                                            QuizLanguage.BOTH -> "Next • അടുത്ത ചോദ്യം"
                                        }
                                    } else {
                                        when (language) {
                                            QuizLanguage.EN -> "Finish Level 🏆"
                                            QuizLanguage.ML -> "പൂർത്തിയാക്കുക 🏆"
                                            QuizLanguage.BOTH -> "Finish • പൂർത്തിയാക്കുക 🏆"
                                        }
                                    }
                                    Text(
                                        text = btnLabel,
                                        color = Color.White,
                                        fontSize = 15.5.sp,
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
    language: QuizLanguage,
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
                    text = when (language) {
                        QuizLanguage.EN -> "✨ NEW HIGH SCORE! ✨"
                        QuizLanguage.ML -> "✨ പുതിയ ഉയർന്ന സ്കോർ! ✨"
                        QuizLanguage.BOTH -> "✨ NEW HIGH SCORE • പുതിയ റെക്കോർഡ്! ✨"
                    },
                    color = Color(0xFFFDE68A),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        Text(
            text = when (language) {
                QuizLanguage.EN -> "Level ${levelInfo.level} Complete!"
                QuizLanguage.ML -> "ലെവൽ ${levelInfo.level} പൂർത്തിയായി!"
                QuizLanguage.BOTH -> "Level ${levelInfo.level} Complete! • ലെവൽ ${levelInfo.level} പൂർത്തിയായി!"
            },
            color = Color.White,
            fontSize = if (language == QuizLanguage.BOTH) 20.sp else 25.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )
        Text(
            text = when (language) {
                QuizLanguage.EN -> "${levelInfo.title} • ${levelInfo.difficultyLabel}"
                QuizLanguage.ML -> "${levelInfo.titleMl} • ${levelInfo.difficultyLabelMl}"
                QuizLanguage.BOTH -> "${levelInfo.title} • ${levelInfo.titleMl}"
            },
            color = c2,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

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
                ResultStatRow(
                    label = when (language) {
                        QuizLanguage.EN -> "Final Score"
                        QuizLanguage.ML -> "അന്തിമ സ്കോർ"
                        QuizLanguage.BOTH -> "Score • സ്കോർ"
                    },
                    value = "🏆 $score pts",
                    accent = Color(0xFF38BDF8)
                )
                HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
                ResultStatRow(
                    label = when (language) {
                        QuizLanguage.EN -> "Correct Answers"
                        QuizLanguage.ML -> "ശരിയായ ഉത്തരങ്ങൾ"
                        QuizLanguage.BOTH -> "Correct • ശരിയുത്തരം"
                    },
                    value = "$correctCount / $totalQuestions ($accuracy%)",
                    accent = Color(0xFF10B981)
                )
                HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
                ResultStatRow(
                    label = when (language) {
                        QuizLanguage.EN -> "Best Streak"
                        QuizLanguage.ML -> "മികച്ച സ്ട്രീക്ക്"
                        QuizLanguage.BOTH -> "Streak • സ്ട്രീക്ക്"
                    },
                    value = "🔥 ${maxStreak}x",
                    accent = Color(0xFFFBBF24)
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

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
                        text = when (language) {
                            QuizLanguage.EN -> "Continue to Level ${levelInfo.level + 1} ⚡"
                            QuizLanguage.ML -> "ലെവൽ ${levelInfo.level + 1}-ലേക്ക് കടക്കുക ⚡"
                            QuizLanguage.BOTH -> "Next: Level ${levelInfo.level + 1} • അടുത്ത ലെവൽ ⚡"
                        },
                        color = Color.White,
                        fontSize = 15.5.sp,
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
                    text = when (language) {
                        QuizLanguage.EN -> "👑 You reached Omega Grandmaster (Level 10)!"
                        QuizLanguage.ML -> "👑 നിങ്ങൾ ഒമേഗ മഹാഗുരു (ലെവൽ 10) പൂർത്തിയാക്കി!"
                        QuizLanguage.BOTH -> "👑 Omega Grandmaster (Level 10 • ലെവൽ 10)!"
                    },
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
                Text(
                    text = if (language == QuizLanguage.ML) "വീണ്ടും" else "Retry",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            OutlinedButton(
                onClick = onBackToHub,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.2.dp, Color.White.copy(alpha = 0.35f))
            ) {
                Text(
                    text = if (language == QuizLanguage.ML) "എല്ലാ ലെവലും" else "All Levels",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
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
            fontSize = 13.5.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            color = accent,
            fontSize = 16.5.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}
