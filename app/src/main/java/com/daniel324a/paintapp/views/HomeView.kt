package com.daniel324a.paintapp.views

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.daniel324a.paintapp.components.AppLogo
import com.daniel324a.paintapp.ui.theme.PaintAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeView(navController: NavController? = null) {
    // Set up Layout
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colors.background
    )

    //View
    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 25.dp)
                .padding(top = 50.dp)
        ) {

            //Title
            AppLogo()
            Spacer(modifier = Modifier.height(10.dp))

            // Navigate Button
            Button(
                onClick = { navController!!.navigate("/paint") },
                content = { Text("Let's Paint!") }
            )
        }
    }
}

@Preview()
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun homePreview() {
    PaintAppTheme {
        HomeView()
    }
}