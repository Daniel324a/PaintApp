package com.daniel324a.paintapp.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.daniel324a.paintapp.models.LocalPaintProvider

@Composable
fun PaintOptions(displaySnackBar: (String) -> Unit, shareCallBack: () -> Unit) {
    val (provider, update) = LocalPaintProvider.current

    Row {
        IconButton(
            onClick = {
                update(provider.copy(painted = listOf()))
                displaySnackBar("Canvas Cleared!")
            },
            content = {
                Icon(
                    Icons.Rounded.Delete,
                    null,
                    tint = if (provider.isDark()) Color.LightGray else Color.Gray
                )
            },
            modifier = Modifier.clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(5.dp))

        IconButton(
            onClick = shareCallBack,
            content = {
                Icon(
                    Icons.Rounded.Share,
                    null,
                    tint = if (provider.isDark()) Color.LightGray else Color.Gray
                )
            },
            modifier = Modifier.clip(CircleShape)

        )

    }
}