package com.daniel324a.paintapp.components

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInteropFilter
import com.daniel324a.paintapp.models.LocalPaintProvider
import com.daniel324a.paintapp.models.PaintedPoints
import com.daniel324a.paintapp.utils.Logger


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Paintable(background: Color = Color.Transparent) {
    val (provider, update) = LocalPaintProvider.current
    var temp by remember { mutableStateOf<List<Offset>>(mutableListOf()) }

    Surface(
        color = background,
        modifier = Modifier
            .fillMaxSize()
            .pointerInteropFilter {
                var reValue = true
                when (it.action) {
                    // On Release
                    MotionEvent.ACTION_UP ->
                        provider.painted
                            .let { prev ->
                                val newList =
                                    prev + PaintedPoints(
                                        temp,
                                        provider.color,
                                        provider.strokeWidth
                                    )
                                update(provider.copy(painted = newList))
                                temp = listOf()
                            }

                    // On Move
                    MotionEvent.ACTION_MOVE -> temp += Offset(it.x, it.y)
                    // On Touch
                    MotionEvent.ACTION_DOWN -> temp += Offset(it.x, it.y)

                    //Filter Other Gestures
                    else -> reValue = false
                }
                reValue
            }
    ) {
        Canvas(Modifier.fillMaxSize()) {
            // On Draw
            provider.painted.forEach {
                drawPoints(
                    color = it.color,
                    points = it.points,
                    pointMode = PointMode.Polygon,
                    strokeWidth = it.strokeWidth,
                    cap = StrokeCap.Round,
                )
            }

            if (temp.isNotEmpty())
                drawPoints(
                    points = temp,
                    pointMode = PointMode.Polygon,
                    strokeWidth = provider.strokeWidth,
                    cap = StrokeCap.Round,
                    color = provider.color,
                )
        }
    }
}