package com.daniel324a.paintapp.views

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.daniel324a.paintapp.components.PaintAppBar
import com.daniel324a.paintapp.components.Paintable
import com.daniel324a.paintapp.models.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun PaintView(navController: NavController) {

    // Clear Layout
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.White
    )

    PaintProvider {
        val (provider, update) = LocalPaintProvider.current

        Scaffold(
            backgroundColor = Color.White,
            topBar = { PaintAppBar(navController, "Canvas") },
            content = { Paintable() },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { update(provider.copy(painted = mutableListOf())) }, //{ navController.popBackStack() },
                    content = { Icon(Icons.Rounded.Delete, "Clear Icon") },
                    backgroundColor = Color.Red,
                    contentColor = Color.White
                )
            }
        )
    }


}


