package com.venser.recipes.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val RecipesDarkColorScheme = darkColorScheme(
    background = Background,
    surface = Background,
    surfaceVariant = SurfaceVariant,
    onBackground = OnBackground,
    onSurface = OnBackground,
    onSurfaceVariant = OnSurfaceVariant,
    outline = Outline,
    primary = AccentLight,
    onPrimary = OnAccentLight,
    secondaryContainer = SurfaceVariant,
    onSecondaryContainer = OnBackground,
)

@Composable
fun RecipesTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = RecipesDarkColorScheme,
        content = content,
    )
}
