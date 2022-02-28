package com.daniel324a.paintapp.components

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
fun SelectColorButton(color: Color, onClick: () -> Unit) {
    val (provider) = LocalPaintProvider.current

    val bColor =
        if (provider.color!! == color) color.copy(alpha = 0.3f) else Color.Transparent

    IconButton(
        onClick = onClick,
        content = { Icon(Icons.Rounded.Edit, contentDescription = null, tint = color) },
        modifier = Modifier
            .clip(CircleShape)
            .background(color.copy(alpha = 0.1f))
            .border(3.dp, bColor, CircleShape)
    )
}