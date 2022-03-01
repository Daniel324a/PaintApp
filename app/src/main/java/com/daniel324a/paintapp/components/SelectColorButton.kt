package com.daniel324a.paintapp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.daniel324a.paintapp.models.LocalPaintProvider

@Composable
fun SelectColorButton(color: Color) {
    val (provider, update) = LocalPaintProvider.current
    val isWhite = color == Color.White

    val bColor = if (isWhite) Color.LightGray else Color.Transparent

    IconButton(
        onClick = { update(provider.copy(color = color)) },
        content = {
            AnimatedVisibility(visible = provider.color == color) {
                Icon(
                    Icons.Rounded.Edit,
                    contentDescription = null,
                    tint = if (isWhite) Color.DarkGray else Color.White)
            }
        },
        modifier = Modifier
            .clip(CircleShape)
            .background(color)
            .border(2.dp, bColor, CircleShape)
    )
}