package com.konradjurkowski.snackbarkmm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SnackBarKMM(
    modifier: Modifier = Modifier,
    snackBarData: SnackBarData,
    position: SnackBarPosition,
    onCloseClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(snackBarData.snackBarType.color)
            .padding(
                top = if (position == SnackBarPosition.TOP) getStatusBarHeight() else 0.dp,
                bottom = if (position == SnackBarPosition.BOTTOM) getNavigationBarHeight() else 0.dp,
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = snackBarData.getMessage(),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = onCloseClick,
        ) {
            Icon(
                Icons.Default.Close,
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}

@Composable
private fun getNavigationBarHeight(): Dp {
    val insets = WindowInsets.navigationBars
    val density = LocalDensity.current
    return with(density) { insets.getBottom(density).toDp() }
}

@Composable
private fun getStatusBarHeight(): Dp {
    val insets = WindowInsets.systemBars
    val density = LocalDensity.current
    return with(density) { insets.getTop(density).toDp() }
}
