package org.marthoma.centreyouth.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PurpleLight,
    onPrimary = DarkBg,
    primaryContainer = PurpleDark,
    onPrimaryContainer = PurpleLight,
    secondary = GoldAccent,
    onSecondary = DarkBg,
    secondaryContainer = GoldDark,
    onSecondaryContainer = GoldLight,
    tertiary = TealAccent,
    background = DarkBg,
    onBackground = DarkTextPrimary,
    surface = DarkSurface,
    onSurface = DarkTextPrimary,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkTextSecondary
)

private val LightColorScheme = lightColorScheme(
    primary = PurplePrimary,
    onPrimary = LightSurface,
    primaryContainer = PurpleLight,
    onPrimaryContainer = LightSurface,
    secondary = GoldAccent,
    onSecondary = LightSurface,
    secondaryContainer = GoldLight,
    onSecondaryContainer = GoldDark,
    tertiary = TealAccent,
    background = LightBg,
    onBackground = LightTextPrimary,
    surface = LightSurface,
    onSurface = LightTextPrimary,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = LightTextSecondary
)

@Composable
fun CentreYouthTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
