package com.daniel324a.paintapp.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.daniel324a.paintapp.R
import com.daniel324a.paintapp.models.LocalPaintProvider

@Composable
fun PaintAppBar(
    navController: NavController,
    title: String = stringResource(id = R.string.app_name),
    expanded: Boolean = false,
) {
    val (provider) = LocalPaintProvider.current

    val icon = if (expanded) Icons.Rounded.Close else Icons.Rounded.Palette
    val iconColor = if (provider.isDark()) Color.LightGray else Color.DarkGray


    TopAppBar(
        title = {
            Text(
                title,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Light,
                color = iconColor
            )
        },
        backgroundColor = provider.canvasColor,
        elevation = 5.dp,
        actions = {
            SwitchCanvasButton()

            IconButton(
                onClick = { provider.toggleSheet() },
                content = { Icon(icon, null, tint = Color(0xff039be5)) },
                modifier = Modifier.clip(CircleShape)
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() },
                content = {
                    Icon(Icons.Rounded.ArrowBack,
                        null,
                        tint = iconColor
                    )
                },
                modifier = Modifier.clip(CircleShape)
            )
        }
    )
}
