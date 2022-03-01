package com.daniel324a.paintapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PaintBottomSheet() {
    ModalBottomSheetLayout(
        scrimColor = Color.Black,
        content = { Content() },
        sheetContent = { Content() },
        modifier = Modifier.height(355.dp)
    )
}

@Composable
private fun Content() {
    val state = rememberScrollState()

    Column(
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(15.dp)
            .verticalScroll(state)
    ) {
        // Decoration
        DecorationBar()
        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Customize Pencil",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.height(15.dp))

        // Color Picker
        ColorPicker()
        Spacer(modifier = Modifier.height(30.dp))

        // Stroke Slider
        StrokeSlider()
        Spacer(modifier = Modifier.height(20.dp))
    }
}