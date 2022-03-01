package com.daniel324a.paintapp.components

import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.daniel324a.paintapp.models.LocalPaintProvider

@Composable
fun StrokeSlider() {
    val (provider, update) = LocalPaintProvider.current
    var sliderPosition by remember { mutableStateOf(provider.strokeWidth) }

    Slider(
        value = sliderPosition,
        onValueChange = { sliderPosition = it },
        onValueChangeFinished = { update(provider.copy(strokeWidth = sliderPosition)) },
        valueRange = 10f..50f,
        steps = 5,
        colors = SliderDefaults.colors(
            thumbColor = provider.color,
            activeTrackColor = Color.Gray,
        )
    )

}