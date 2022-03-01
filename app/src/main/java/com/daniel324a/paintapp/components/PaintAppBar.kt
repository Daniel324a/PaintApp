package com.daniel324a.paintapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
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
    toggleSheet: () -> Unit,
    expanded: Boolean = false,
    shareCallBack: () -> Unit,
    displaySnackBar: (String) -> Unit,
) {
    val (provider, update) = LocalPaintProvider.current
    val icon = if (expanded) Icons.Rounded.Close else Icons.Rounded.MoreVert

    TopAppBar(
        title = {
            Text(title,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Light)
        },
        backgroundColor = Color.White,
        elevation = 5.dp,
        actions = {
            IconButton(
                onClick = {
                    update(provider.copy(painted = listOf()))
                    displaySnackBar("Canvas Cleared!")
                },
                content = { Icon(Icons.Rounded.Delete, null) },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
            )
            IconButton(
                onClick = shareCallBack,
                content = { Icon(Icons.Rounded.Share, null) },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
            )
            IconButton(
                onClick = { toggleSheet() },
                content = { Icon(icon, null) },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() },
                content = { Icon(Icons.Rounded.ArrowBack, null) },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
            )
        }
    )
}
