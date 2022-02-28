package com.daniel324a.paintapp.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
) {
    val (provider, update) = LocalPaintProvider.current


    TopAppBar(
        title = { Text(title, style = MaterialTheme.typography.h5, fontWeight = FontWeight.Light) },
        backgroundColor = Color.White,
        elevation = 0.dp,
        actions = {
            for (color in provider.colors!!) {
                SelectColorButton(color, onClick = { update(provider.copy(color = color)) })
                Spacer(modifier = Modifier.width(5.dp))
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() },
                content = { Icon(Icons.Rounded.ArrowBack, null) },
            )
        }
    )
}