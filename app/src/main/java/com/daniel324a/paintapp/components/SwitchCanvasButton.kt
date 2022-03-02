package com.daniel324a.paintapp.components


import androidx.compose.animation.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.daniel324a.paintapp.models.LocalPaintProvider
import com.daniel324a.paintapp.ui.theme.DarkBG
import com.daniel324a.paintapp.ui.theme.LightBG


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SwitchCanvasButton() {
        val (provider, update) = LocalPaintProvider.current
    val isDark: Boolean = provider.canvasColor == Color(0xFF212121)

    val newColor = if (isDark) LightBG else DarkBG
    val icon = if (isDark) Icons.Rounded.DarkMode else Icons.Rounded.LightMode
    val iconColor = if (isDark) Color(0xffe91e63) else Color(0xffffc107)

    IconButton(
        onClick = { update(provider.copy(canvasColor = newColor)) },
        content = {
            AnimatedContent(
                targetState = isDark,
                transitionSpec = {
                    if (targetState)
                        slideInVertically { height -> height } + fadeIn() with
                                slideOutVertically { height -> -height } + fadeOut()
                    else {
                        slideInVertically { height -> -height } + fadeIn() with
                                slideOutVertically { height -> height } + fadeOut()
                    }.using(SizeTransform(clip = false))
                },
                content = {
                    Icon(
                        icon,
                        contentDescription = null,
                        tint = iconColor
                    )
                }
            )
        },
        modifier = Modifier.clip(CircleShape)

    )

}