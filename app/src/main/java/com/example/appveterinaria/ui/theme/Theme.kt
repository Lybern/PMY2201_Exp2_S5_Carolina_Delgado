package com.example.appveterinaria.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val VeterinariaColorScheme = lightColorScheme(
    primary = EmeraldBrand,
    onPrimary = SurfaceWhite,
    primaryContainer = EmeraldSoftBg,
    onPrimaryContainer = StoneDark,
    secondary = TealBrand,
    onSecondary = SurfaceWhite,
    secondaryContainer = TealBadgeBg,
    onSecondaryContainer = TealTextDark,
    tertiary = AmberBrand,
    onTertiary = SurfaceWhite,
    tertiaryContainer = AmberSoftBg,
    onTertiaryContainer = AmberTextDark,
    background = CanvasCream,
    onBackground = StoneDark,
    surface = SurfaceWhite,
    onSurface = StoneDark,
    surfaceVariant = SurfaceWarmLow,
    onSurfaceVariant = StoneMuted,
    outline = BorderWarm
)

@Composable
fun AppVeterinariaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = VeterinariaColorScheme,
        typography = Typography,
        content = content
    )
}
