package com.daniel324a.paintapp.components

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInteropFilter
import com.daniel324a.paintapp.models.LocalPaintProvider
import com.daniel324a.paintapp.models.PaintedPoints


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Paintable() {
    val (provider, update) = LocalPaintProvider.current
    var temp by remember { mutableStateOf<List<Offset>>(mutableListOf()) }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInteropFilter {
                var reValue = true
                when (it.action) {
                    // On Release
                    MotionEvent.ACTION_UP ->
                        provider.painted
                            .let { prev ->
                                prev.addAll(mutableListOf(PaintedPoints(temp, provider.color)))
                                update(provider.copy(painted = prev))
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
        // On Draw
        provider.painted.forEach {
            drawPoints(
                color = it.color,
                points = it.points,
                pointMode = PointMode.Polygon,
                strokeWidth = provider.strokeWidth,
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