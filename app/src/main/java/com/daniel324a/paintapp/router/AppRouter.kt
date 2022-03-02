package com.daniel324a.paintapp.router

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.daniel324a.paintapp.views.HomeView
import com.daniel324a.paintapp.views.PaintView
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AppRouter() {
    // Router Controller
    val navController = rememberNavController()

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colors.background
    )


    // Routes
    NavHost(navController = navController, startDestination = "/paint") {
        composable("/home") {
            HomeView(navController)
        }
        composable("/paint") {
            PaintView(navController)
        }
    }
}