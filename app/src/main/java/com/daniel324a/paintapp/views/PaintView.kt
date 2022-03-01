package com.daniel324a.paintapp.views

import android.graphics.Bitmap
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.daniel324a.paintapp.components.PaintAppBar
import com.daniel324a.paintapp.components.PaintBottomSheet
import com.daniel324a.paintapp.components.Paintable
import com.daniel324a.paintapp.components.ToggleVisibility
import com.daniel324a.paintapp.models.PaintProvider
import com.daniel324a.paintapp.utils.ShareUtils
import com.daniel324a.paintapp.utils.captureBitmap
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PaintView(navController: NavController) {

    // Clear Layout
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.White
    )

    // Logic
    val state = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    fun toggleSheet() {
        scope.launch {
            state.bottomSheetState.apply {
                if (isCollapsed) expand() else collapse()
            }
        }
    }

    val displaySnackBar: (String) -> Unit = {
        scope.launch {
            state.snackbarHostState.showSnackbar(it, "Close", SnackbarDuration.Short)
        }
    }

    val expanded: Boolean = when (state.bottomSheetState.isExpanded) {
        true -> !state.bottomSheetState.isAnimationRunning
        false -> state.bottomSheetState.isAnimationRunning
    }

    //Layout
    PaintProvider {

        // Share Paintable Extra Logic
        val context = LocalContext.current
        val snapShot: () -> Bitmap = captureBitmap {
            Paintable(Color.White)
        }

        fun shareCanvas() {
            MainScope().launch {
                // Generate Bitmap from Paintable Composable
                val bitmap = snapShot.invoke()

                // Share Image
                ShareUtils.shareImageToOthers(
                    context,
                    "Hey! check out my last creation \uD83C\uDFA8\uD83D\uDD8C️",
                    bitmap
                )
            }
        }

        BottomSheetScaffold(
            scaffoldState = state,
            backgroundColor = Color.White,
            sheetBackgroundColor = Color.White,
            sheetShape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp),
            sheetPeekHeight = 0.dp,
            topBar = {
                PaintAppBar(
                    navController,
                    "Canvas",
                    { toggleSheet() },
                    expanded,
                    { shareCanvas() },
                    displaySnackBar
                )
            },
            sheetContent = { PaintBottomSheet() },
            snackbarHost = {
                SnackbarHost(it) { data ->
                    Snackbar(
                        snackbarData = data,
                        elevation = 0.dp,
                        shape = CircleShape,
                        backgroundColor = Color.Black.copy(alpha = 0.5f),
                        actionColor = Color.Yellow,
                        contentColor = Color.White,
                    )
                }
            },
            content = {
                ToggleVisibility(
                    content = { Paintable() },
                    show = !expanded
                )
            }
        )
    }
}


