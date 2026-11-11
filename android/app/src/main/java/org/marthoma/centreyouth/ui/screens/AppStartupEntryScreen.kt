package org.marthoma.centreyouth.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.marthoma.centreyouth.ui.components.AwesomeLogoEmblem3D

/**
 * Super Awesome App Startup Entry Screen
 * Presents a cinematic entrance experience featuring the exact 3D flying emblem
 * from the official website with interactive drag-tilt, rotating spotlight ring,
 * and synchronized typographic revelation.
 */
@Composable
fun AppStartupEntryScreen(
    onEnterApp: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Entrance animation states
    var startAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        startAnimation = true
        // Auto-advance cleanly after 3.2 seconds if user does not tap earlier
        delay(3200)
        onEnterApp()
    }

    // Phase 1: Emblem scale & fade
    val emblemScale by animateFloatAsState(
        targetValue = if (startAnimation) 1.0f else 0.45f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "emblemScale"
    )
    val emblemAlpha by animateFloatAsState(
        targetValue = if (startAnimation) 1.0f else 0.0f,
        animationSpec = tween(600, easing = FastOutSlowInEasing),
        label = "emblemAlpha"
    )

    // Phase 2: Text elements reveal with staggered offset
    val textAlpha by animateFloatAsState(
        targetValue = if (startAnimation) 1.0f else 0.0f,
        animationSpec = tween(800, delayMillis = 350, easing = FastOutSlowInEasing),
        label = "textAlpha"
    )
    val textOffsetY by animateFloatAsState(
        targetValue = if (startAnimation) 0f else 40f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "textOffsetY"
    )

    // Phase 3: Bottom Button reveal
    val buttonAlpha by animateFloatAsState(
        targetValue = if (startAnimation) 1.0f else 0.0f,
        animationSpec = tween(600, delayMillis = 650, easing = FastOutSlowInEasing),
        label = "buttonAlpha"
    )

    // Continuous button border shimmer
    val infiniteTransition = rememberInfiniteTransition(label = "ButtonShimmer")
    val shimmerOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerOffset"
    )

    // Full-bleed edge-to-edge celestial background
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF090D16),
                        Color(0xFF0F172A),
                        Color(0xFF050811)
                    )
                )
            )
    ) {
        // Celestial ambient background glow (full-bleed edge-to-edge)
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Top-left golden glow
            val topCenter = Offset(this.size.width * 0.15f, this.size.height * 0.15f)
            val topRadius = this.size.width * 0.70f
            drawCircle(
                brush = Brush.radialGradient(
                    0.0f to Color(0xFFF59E0B).copy(alpha = 0.18f),
                    0.7f to Color.Transparent,
                    center = topCenter,
                    radius = topRadius
                ),
                radius = topRadius,
                center = topCenter
            )

            // Bottom-right royal purple glow
            val bottomCenter = Offset(this.size.width * 0.85f, this.size.height * 0.78f)
            val bottomRadius = this.size.width * 0.80f
            drawCircle(
                brush = Brush.radialGradient(
                    0.0f to Color(0xFF6B21A8).copy(alpha = 0.22f),
                    0.7f to Color.Transparent,
                    center = bottomCenter,
                    radius = bottomRadius
                ),
                radius = bottomRadius,
                center = bottomCenter
            )
        }

        // Foreground UI safely inset within system bars
        Box(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            // Top Header: Skip pill button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { onEnterApp() },
                    color = Color.White.copy(alpha = 0.08f),
                    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.15f)),
                    shape = CircleShape
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Skip",
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Skip to App",
                            tint = Color.White.copy(alpha = 0.85f),
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }

            // Central Content Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // THE 3D EMBLEM
                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            scaleX = emblemScale
                            scaleY = emblemScale
                            alpha = emblemAlpha
                        },
                    contentAlignment = Alignment.Center
                ) {
                    AwesomeLogoEmblem3D(
                        medallionSize = 210.dp,
                        enableInteractiveTilt = true,
                        showAura = true,
                        showPedestalShadow = true,
                        onClick = { onEnterApp() }
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                // TYPOGRAPHY WITH STAGGERED REVEAL
                Column(
                    modifier = Modifier
                        .graphicsLayer {
                            alpha = textAlpha
                            translationY = textOffsetY
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Gold Pill Badge
                    Surface(
                        color = Color(0xFFF59E0B).copy(alpha = 0.14f),
                        border = BorderStroke(1.dp, Color(0xFFF59E0B).copy(alpha = 0.45f)),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(
                            text = "✝️ Lighted to Lighten",
                            color = Color(0xFFFBBF24),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 5.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Primary App Brand Title
                    Text(
                        text = "Keezhillam-Munnar MTYS",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.2.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // Malayalam Organization Title
                    Text(
                        text = "കീഴില്ലം - മൂന്നാർ സെന്റർ മാർത്തോമ്മാ യുവജനസഖ്യം",
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // Diocese Subtitle
                    Text(
                        text = "Kottayam - Kochi Diocese • Malankara Mar Thoma Syrian Church",
                        color = Color(0xFF94A3B8),
                        fontSize = 11.5.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Feature Chips (Cleanly stacked rows for universal mobile fit)
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        EntryFeatureChip(text = "⛪ 15 Centre Parishes")
                        EntryFeatureChip(text = "🌿 Worship • Study • Witness • Service")
                    }
                }
            }

            // Bottom Action Button: "Enter Sakhyam ✨"
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp, vertical = 24.dp)
                    .graphicsLayer {
                        alpha = buttonAlpha
                    }
            ) {
                val shimmerBrush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFD97706),
                        Color(0xFFFBBF24),
                        Color(0xFFF59E0B),
                        Color(0xFFD97706)
                    ),
                    startX = -300f + shimmerOffset * 1000f,
                    endX = 0f + shimmerOffset * 1000f
                )

                Button(
                    onClick = onEnterApp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .clip(RoundedCornerShape(28.dp))
                        .border(1.5.dp, shimmerBrush, RoundedCornerShape(28.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1E293B)
                    ),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Enter Sakhyam",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = "✨",
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EntryFeatureChip(text: String) {
    Surface(
        color = Color(0xFF1E293B).copy(alpha = 0.75f),
        border = BorderStroke(1.dp, Color(0xFF334155)),
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = text,
            color = Color(0xFFCBD5E1),
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
        )
    }
}
