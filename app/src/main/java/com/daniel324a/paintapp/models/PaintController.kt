package com.daniel324a.paintapp.models

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.daniel324a.paintapp.ui.theme.DarkBG
import com.daniel324a.paintapp.ui.theme.LightBG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


private val def_colors = listOf(
    Color.Black,
    Color.White,
    Color.LightGray,
    Color.Gray,
    Color(0xfff44336),
    Color(0xffe91e63),
    Color(0xff9c27b0),
    Color(0xff673ab7),
    Color(0xff3f51b5),
    Color(0xff2196f3),
    Color(0xff03a9f4),
    Color(0xff00bcd4),
    Color(0xff009688),
    Color(0xff4caf50),
    Color(0xff8bc34a),
    Color(0xffcddc39),
    Color(0xffffeb3b),
    Color(0xffffc107),
)

data class PaintedPoints(val points: List<Offset>, val color: Color, val strokeWidth: Float)
data class PaintController @ExperimentalMaterialApi constructor(
    val colors: List<Color> = def_colors,
    val painted: List<PaintedPoints> = listOf(),
    val color: Color = def_colors[0],
    val strokeWidth: Float = 30f,
    val canvasColor: Color = Color.White,
    val bottomSheetScaffoldState: BottomSheetScaffoldState,
    val scope: CoroutineScope,
    var toggleSheet: () -> Unit = {},
    var displaySnackBar: (String) -> Unit = {},
) {
    fun isDark(): Boolean {
        return canvasColor == DarkBG
    }

}

val LocalPaintProvider =
    compositionLocalOf<MutableState<PaintController>> { error("No Initialized Provider Found") }

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PaintProvider(content: @Composable () -> Unit) {
    val state = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    val initColor = if (isSystemInDarkTheme()) DarkBG else LightBG

    val toggleSheet: () -> Unit =
        {
            scope.launch {
                state.bottomSheetState.apply {
                    if (isCollapsed) expand() else collapse()
                }
            }
        }

    val displaySnackbar: (String) -> Unit = {
        scope.launch {
            state.snackbarHostState.showSnackbar(
                it,
                "Close",
                SnackbarDuration.Short
            )
        }
    }

    val initState = PaintController(
        canvasColor = initColor,
        bottomSheetScaffoldState = state,
        scope = scope,
        toggleSheet = toggleSheet,
        displaySnackBar = displaySnackbar
    )
    val provider: MutableState<PaintController> = remember { mutableStateOf(initState) }

    CompositionLocalProvider(
        LocalPaintProvider provides provider,
        content = content
    )
}