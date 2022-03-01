package com.daniel324a.paintapp.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.daniel324a.paintapp.models.LocalPaintProvider
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun ColorPicker() {
    val (provider) = LocalPaintProvider.current

    FlowRow(
        mainAxisSpacing = 10.dp,
        crossAxisSpacing = 10.dp,
    ) {
        provider.colors.forEach {
            SelectColorButton(color = it)
        }
    }
}