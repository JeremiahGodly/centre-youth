package org.marthoma.centreyouth.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.marthoma.centreyouth.ui.components.AwesomeLogoEmblem3D

/**
 * Pure 3D Animated Emblem Startup Screen
 * Animates ONLY the 3D flying emblem logo on a deep cosmic backdrop — zero text,
 * zero extra UI clutter — matching the website's showcase emblem before entering the app.
 */
@Composable
fun AppStartupEntryScreen(
    onEnterApp: () -> Unit,
    modifier: Modifier = Modifier
) {
    var startAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        startAnimation = true
        // Showcase the 3D emblem animation cleanly for ~2.4 seconds before entering
        delay(2400)
        onEnterApp()
    }

    val emblemScale by animateFloatAsState(
        targetValue = if (startAnimation) 1.0f else 0.55f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "startupEmblemScale"
    )
    val emblemAlpha by animateFloatAsState(
        targetValue = if (startAnimation) 1.0f else 0.0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
        label = "startupEmblemAlpha"
    )

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
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onEnterApp
            ),
        contentAlignment = Alignment.Center
    ) {
        // Subtle ambient golden/crimson cosmic glow behind the 3D emblem
        Canvas(modifier = Modifier.fillMaxSize()) {
            val topCenter = Offset(this.size.width * 0.25f, this.size.height * 0.28f)
            drawCircle(
                brush = Brush.radialGradient(
                    0.0f to Color(0xFFF59E0B).copy(alpha = 0.14f),
                    0.75f to Color.Transparent,
                    center = topCenter,
                    radius = this.size.width * 0.65f
                ),
                radius = this.size.width * 0.65f,
                center = topCenter
            )

            val bottomCenter = Offset(this.size.width * 0.78f, this.size.height * 0.72f)
            drawCircle(
                brush = Brush.radialGradient(
                    0.0f to Color(0xFF6B21A8).copy(alpha = 0.16f),
                    0.75f to Color.Transparent,
                    center = bottomCenter,
                    radius = this.size.width * 0.70f
                ),
                radius = this.size.width * 0.70f,
                center = bottomCenter
            )
        }

        // ONLY the 3D animated emblem logo centered in full clarity
        Box(
            modifier = Modifier.graphicsLayer {
                scaleX = emblemScale
                scaleY = emblemScale
                alpha = emblemAlpha
            },
            contentAlignment = Alignment.Center
        ) {
            AwesomeLogoEmblem3D(
                medallionSize = 240.dp,
                enableInteractiveTilt = true,
                showAura = true,
                showPedestalShadow = true,
                onClick = onEnterApp
            )
        }
    }
}
