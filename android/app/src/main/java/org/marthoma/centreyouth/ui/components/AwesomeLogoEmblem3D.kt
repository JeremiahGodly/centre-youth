package org.marthoma.centreyouth.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.marthoma.centreyouth.R

/**
 * 3D Flying Logo & Synchronous Spotlight Medallion
 * Faithful Jetpack Compose recreation of the official website's interactive 3D emblem.
 *
 * Visual Layers:
 * 1. Animated Aura Glow (pulsing amber-crimson halo)
 * 2. Harmonic Floating Pedestal Shadow (scales inversely with medallion height)
 * 3. Deep Dark Medallion Disc with radial slate gradient (#1E293B -> #0F172A -> #050811)
 * 4. 360° Rotating Spotlight Conic-Gradient Ring with luminous rim halo
 * 5. Official Mar Thoma Emblem floating inside with 3D parallax
 * 6. Domed Crystal Glass Lens Overlay (glossy specular reflection)
 * 7. Diamond Star Specular Glint (pulsing diamond sparkle at top-left rim)
 * 8. Interactive 3D Drag Tilt & Sinusoidal Hover Physics
 */
@Composable
fun AwesomeLogoEmblem3D(
    modifier: Modifier = Modifier,
    medallionSize: Dp = 220.dp,
    enableInteractiveTilt: Boolean = true,
    showAura: Boolean = true,
    showPedestalShadow: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    val localDensity = LocalDensity.current

    // Infinite transitions for smooth continuous motion
    val infiniteTransition = rememberInfiniteTransition(label = "Emblem3DAnimation")

    // 1. Continuous 360° spotlight angle rotation (matches website --spotlight-angle)
    val spotlightAngle by infiniteTransition.animateFloat(
        initialValue = -45f,
        targetValue = 315f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3600, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "spotlightAngle"
    )

    // 2. Harmonic floating vertical hover (translateY) proportional to medallionSize
    val hoverAmplitude = if (medallionSize >= 160.dp) 8f else 4f
    val hoverY by infiniteTransition.animateFloat(
        initialValue = -hoverAmplitude,
        targetValue = hoverAmplitude,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "hoverY"
    )

    // 3. Subtle 3D sinusoidal breathing tilt (rotateY & rotateX)
    val breatheRotateY by infiniteTransition.animateFloat(
        initialValue = -11f,
        targetValue = 11f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "breatheRotateY"
    )

    val breatheRotateX by infiniteTransition.animateFloat(
        initialValue = 4f,
        targetValue = 12f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "breatheRotateX"
    )

    // 4. Diamond specular glint pulse (scale & alpha)
    val glintScale by infiniteTransition.animateFloat(
        initialValue = 0.75f,
        targetValue = 1.35f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glintScale"
    )

    val glintAlpha by infiniteTransition.animateFloat(
        initialValue = 0.55f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glintAlpha"
    )

    // 5. Aura glow breathing scale & intensity
    val auraScale by infiniteTransition.animateFloat(
        initialValue = 0.90f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "auraScale"
    )

    // Interactive user touch drag tilt offsets
    var dragTiltX by remember { mutableFloatStateOf(0f) }
    var dragTiltY by remember { mutableFloatStateOf(0f) }

    val animatedDragTiltX by animateFloatAsState(
        targetValue = dragTiltX,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = "animatedDragTiltX"
    )
    val animatedDragTiltY by animateFloatAsState(
        targetValue = dragTiltY,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = "animatedDragTiltY"
    )

    // Combined 3D angles
    val totalRotateX = breatheRotateX + animatedDragTiltX
    val totalRotateY = breatheRotateY + animatedDragTiltY

    // Precompute px values safely outside Canvas/graphicsLayer
    val hoverYPx = with(localDensity) { hoverY.dp.toPx() }
    val shadowOffsetYPx = with(localDensity) { (medallionSize * 0.12f).toPx() }
    val discElevationPx = with(localDensity) { (medallionSize * 0.11f).toPx() }

    // Colors exactly matched with website CSS
    val goldBright = Color(0xFFFBBF24)
    val goldAmber = Color(0xFFF59E0B)
    val crimsonRose = Color(0xFFE11D48)

    val spotlightStops = remember {
        arrayOf(
            0.0f to goldBright,
            0.15f to goldAmber,
            0.26f to goldAmber.copy(alpha = 0.50f),
            0.40f to Color.Transparent,
            0.60f to Color.Transparent,
            0.75f to Color(0x99000000),
            1.0f to goldBright
        )
    }

    Box(
        modifier = modifier
            .size(medallionSize)
            .then(
                if (enableInteractiveTilt) {
                    Modifier.pointerInput(Unit) {
                        detectDragGestures(
                            onDrag = { change, dragAmount ->
                                change.consume()
                                dragTiltY = (dragTiltY + dragAmount.x * 0.18f).coerceIn(-28f, 28f)
                                dragTiltX = (dragTiltX - dragAmount.y * 0.18f).coerceIn(-20f, 20f)
                            },
                            onDragEnd = {
                                dragTiltX = 0f
                                dragTiltY = 0f
                            },
                            onDragCancel = {
                                dragTiltX = 0f
                                dragTiltY = 0f
                            }
                        )
                    }
                } else Modifier
            )
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClick
                    )
                } else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        // LAYER 1: Pulsing Aura Glow behind the medallion
        if (showAura) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        scaleX = auraScale * 1.28f
                        scaleY = auraScale * 1.28f
                    }
            ) {
                val auraRadius = this.size.minDimension * 0.65f
                drawCircle(
                    brush = Brush.radialGradient(
                        0.0f to goldAmber.copy(alpha = 0.38f),
                        0.45f to crimsonRose.copy(alpha = 0.22f),
                        0.75f to Color.Transparent,
                        center = center,
                        radius = auraRadius
                    ),
                    radius = auraRadius,
                    center = center
                )
            }
        }

        // LAYER 2: Floating Pedestal Shadow on the bottom floor
        if (showPedestalShadow) {
            val normalizedHover = ((hoverY + hoverAmplitude) / (2f * hoverAmplitude)).coerceIn(0f, 1f)
            val shadowScale = 0.82f + normalizedHover * 0.24f
            val shadowAlpha = 0.45f + (1f - normalizedHover) * 0.35f

            Canvas(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(medallionSize * 0.18f)
                    .align(Alignment.BottomCenter)
                    .graphicsLayer {
                        translationY = shadowOffsetYPx
                        scaleX = shadowScale
                    }
            ) {
                drawOval(
                    brush = Brush.radialGradient(
                        0.0f to Color.Black.copy(alpha = 0.65f * shadowAlpha),
                        0.55f to goldAmber.copy(alpha = 0.15f * shadowAlpha),
                        0.85f to Color.Transparent,
                        center = center,
                        radius = this.size.width * 0.5f
                    ),
                    topLeft = Offset.Zero,
                    size = this.size
                )
            }
        }

        // LAYER 3, 4, 5, 6, 7: The 3D Medallion Disc
        Box(
            modifier = Modifier
                .fillMaxSize(0.92f)
                .graphicsLayer {
                    cameraDistance = 18f * localDensity.density
                    translationY = hoverYPx
                    rotationX = totalRotateX
                    rotationY = totalRotateY
                    shadowElevation = discElevationPx
                    shape = CircleShape
                    clip = false
                },
            contentAlignment = Alignment.Center
        ) {
            // 3A. Base Dark Slate Circular Medallion + Soft Emblem Shadow + 360° Spotlight Conic Ring
            Canvas(modifier = Modifier.fillMaxSize()) {
                val discRadius = this.size.minDimension * 0.5f
                val strokeWidthPx = (discRadius * 0.055f).coerceAtLeast(3f)
                val haloWidthPx = strokeWidthPx * 2.5f

                // Deep dark slate radial gradient disc background
                drawCircle(
                    brush = Brush.radialGradient(
                        0.0f to Color(0xFF1E293B),
                        0.55f to Color(0xFF0F172A),
                        1.0f to Color(0xFF050811),
                        center = Offset(this.size.width * 0.38f, this.size.height * 0.32f),
                        radius = discRadius * 1.15f
                    ),
                    radius = discRadius,
                    center = center
                )

                // Soft gold & dark ambient glow behind emblem
                drawCircle(
                    brush = Brush.radialGradient(
                        0.0f to goldAmber.copy(alpha = 0.18f),
                        0.60f to Color.Black.copy(alpha = 0.45f),
                        0.85f to Color.Transparent,
                        center = Offset(center.x, center.y + discRadius * 0.05f),
                        radius = discRadius * 0.8f
                    ),
                    radius = discRadius * 0.8f,
                    center = center
                )

                // Outer Subtle Gold Base Rim
                drawCircle(
                    color = goldAmber.copy(alpha = 0.42f),
                    radius = discRadius - strokeWidthPx / 2f,
                    style = Stroke(width = strokeWidthPx * 0.65f)
                )

                // 3B. Rotating Conic Spotlight Sweep Ring
                rotate(degrees = spotlightAngle, pivot = center) {
                    val sweepBrush = Brush.sweepGradient(
                        *spotlightStops,
                        center = center
                    )

                    // Luminous halo aura around the border
                    drawCircle(
                        brush = sweepBrush,
                        radius = discRadius - strokeWidthPx / 2f,
                        style = Stroke(width = haloWidthPx, cap = StrokeCap.Round),
                        alpha = 0.40f
                    )

                    // Sharp vibrant foreground spotlight ring
                    drawCircle(
                        brush = sweepBrush,
                        radius = discRadius - strokeWidthPx / 2f,
                        style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
                    )
                }

                // Inner Depth Shadow Ring
                drawCircle(
                    brush = Brush.radialGradient(
                        0.82f to Color.Transparent,
                        1.0f to Color.Black.copy(alpha = 0.85f),
                        center = center,
                        radius = discRadius
                    ),
                    radius = discRadius
                )
            }

            // LAYER 5: Official Mar Thoma Emblem Logo — Scaled to 74% with 3D Parallax
            Box(
                modifier = Modifier
                    .fillMaxSize(0.74f)
                    .graphicsLayer {
                        translationX = totalRotateY * 0.45f
                        translationY = -totalRotateX * 0.45f
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "Official Emblem",
                    modifier = Modifier.fillMaxSize()
                )
            }

            // LAYER 6: Domed Crystal Glass Lens Overlay (Angled glossy specular sheen)
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            ) {
                drawCircle(
                    brush = Brush.linearGradient(
                        0.0f to Color.White.copy(alpha = 0.32f),
                        0.30f to Color.White.copy(alpha = 0.10f),
                        0.52f to Color.Transparent,
                        1.0f to Color.Black.copy(alpha = 0.25f),
                        start = Offset(0f, 0f),
                        end = Offset(this.size.width, this.size.height)
                    )
                )
            }

            // LAYER 7: Diamond Star Specular Glint (Top-left rim sparkle)
            Box(
                modifier = Modifier
                    .size(medallionSize * 0.14f)
                    .align(Alignment.TopStart)
                    .offset(x = medallionSize * 0.11f, y = medallionSize * 0.08f)
                    .graphicsLayer {
                        scaleX = glintScale
                        scaleY = glintScale
                        alpha = glintAlpha
                    }
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val glintCenter = center
                    val sparkLength = this.size.minDimension * 0.48f

                    // Radial glow center
                    drawCircle(
                        brush = Brush.radialGradient(
                            0.0f to Color.White,
                            0.35f to goldBright,
                            0.70f to goldAmber.copy(alpha = 0.6f),
                            1.0f to Color.Transparent,
                            center = glintCenter,
                            radius = sparkLength
                        )
                    )

                    // Clean 4-pointed diamond star ray
                    val starPath = Path().apply {
                        moveTo(glintCenter.x, glintCenter.y - sparkLength)
                        quadraticBezierTo(glintCenter.x, glintCenter.y, glintCenter.x + sparkLength, glintCenter.y)
                        quadraticBezierTo(glintCenter.x, glintCenter.y, glintCenter.x, glintCenter.y + sparkLength)
                        quadraticBezierTo(glintCenter.x, glintCenter.y, glintCenter.x - sparkLength, glintCenter.y)
                        quadraticBezierTo(glintCenter.x, glintCenter.y, glintCenter.x, glintCenter.y - sparkLength)
                        close()
                    }
                    drawPath(path = starPath, color = Color.White)
                }
            }
        }
    }
}
