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
import com.daniel324a.paintapp.models.LocalPaintProvider

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PaintBottomSheet(shareCallBack: () -> Unit) {
    val displaySnackBar = LocalPaintProvider.current.value.displaySnackBar

    ModalBottomSheetLayout(
        scrimColor = Color.Black,
        content = { Content(displaySnackBar, shareCallBack) },
        sheetContent = { Content(displaySnackBar, shareCallBack) },
        modifier = Modifier.height(355.dp)
    )
}

@Composable
private fun Content(displaySnackBar: (String) -> Unit, shareCallBack: () -> Unit) {

    val isDark = LocalPaintProvider.current.value.isDark()
    val state = rememberScrollState()
    val textColor = if (isDark) Color.LightGray else Color.DarkGray

    Column(
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
            text = "Options",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Light,
            color = textColor
        )
        Spacer(modifier = Modifier.height(15.dp))

        // Options
        PaintOptions(displaySnackBar, shareCallBack)
        Spacer(modifier = Modifier.height(20.dp))

        // Customize Pencil
        Text(
            text = "Customize Pencil",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Light,
            color = textColor
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