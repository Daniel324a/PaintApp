package com.daniel324a.paintapp.models

import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color


private val def_colors = listOf(Color.Black, Color.Green, Color.Cyan, Color.Red)

data class PaintedPoints(val points: List<Offset>, val color: Color)
data class PaintController(
    val painted: MutableList<PaintedPoints> = mutableListOf(),
    val colors: List<Color> = def_colors,
    val color: Color = def_colors[0],
    val strokeWidth: Float = 10f
)

val LocalPaintProvider =
    compositionLocalOf<MutableState<PaintController>> { error("No Initialized Provider Found") }

@Composable
fun PaintProvider(content: @Composable () -> Unit) {
    val initState = PaintController()
    val provider: MutableState<PaintController> = remember { mutableStateOf(initState) }

    CompositionLocalProvider(
        LocalPaintProvider provides provider,
        content = content
    )
}