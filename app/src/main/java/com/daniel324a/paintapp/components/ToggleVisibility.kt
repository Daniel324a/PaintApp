package com.daniel324a.paintapp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.daniel324a.paintapp.models.LocalPaintProvider

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ToggleVisibility(content: @Composable () -> Unit, show: Boolean = true) {

    val isDark = LocalPaintProvider.current.value.isDark()

    Box {
        AnimatedVisibility(
            visible = !show,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            content = {
                Surface(
                    content = { },
                    color = Color.Black.copy(alpha = if (isDark) 0.5f else 0.1f),
                    modifier = Modifier.fillMaxSize()
                )
            },
        )
        content()
    }
}