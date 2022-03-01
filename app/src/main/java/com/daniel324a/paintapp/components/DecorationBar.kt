package com.daniel324a.paintapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DecorationBar() {
    Box(
        content = {},
        modifier = Modifier
            .fillMaxWidth(0.35f)
            .height(6.dp)
            .clip(CircleShape)
            .background(Color.LightGray.copy(alpha = 0.6f))
    )
}