package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val KarkaAcademyColorScheme = lightColorScheme(
  primary = NavyPrimary,
  onPrimary = PureWhite,
  primaryContainer = TealSoftBg,
  onPrimaryContainer = NavyDark,
  secondary = TealPrimary,
  onSecondary = PureWhite,
  secondaryContainer = TealSoftBg,
  onSecondaryContainer = TealDark,
  tertiary = TealAccent,
  onTertiary = NavyDark,
  background = SlateBackground,
  onBackground = DarkText,
  surface = SlateSurface,
  onSurface = DarkText,
  surfaceVariant = SlateBackground,
  onSurfaceVariant = DarkTextSecondary,
  outline = SlateBorder,
  outlineVariant = SlateBorder
)

@Composable
fun KarkaTheme(
  content: @Composable () -> Unit
) {
  MaterialTheme(
    colorScheme = KarkaAcademyColorScheme,
    typography = Typography,
    content = content
  )
}
