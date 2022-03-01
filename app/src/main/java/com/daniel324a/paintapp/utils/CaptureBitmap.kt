package com.daniel324a.paintapp.utils

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.drawToBitmap

@Composable
fun captureBitmap(
    content: @Composable () -> Unit,
): () -> Bitmap {
    val context = LocalContext.current

    // Create view from context
    val composeView = remember { ComposeView(context) }

    // Callback to generate bitmap from view
    fun captureBitmap(): Bitmap {
        return composeView.drawToBitmap()
    }

    // Use a Android native view to build the content composable
    AndroidView(
        factory = {
            composeView.apply {
                setContent {
                    content.invoke()
                }
            }
        }
    )

    // Return the callback
    return ::captureBitmap
}