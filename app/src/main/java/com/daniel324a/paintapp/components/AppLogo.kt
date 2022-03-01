package com.daniel324a.paintapp.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daniel324a.paintapp.R
import com.daniel324a.paintapp.ui.theme.PaintAppTheme

@Composable
fun AppLogo() {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painterResource(id = R.drawable.app_logo),
            "App Logo",
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colors.onBackground
        )
    }

}

@Preview()
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PreviewAppLogo() {
    PaintAppTheme {
        AppLogo()
    }
}