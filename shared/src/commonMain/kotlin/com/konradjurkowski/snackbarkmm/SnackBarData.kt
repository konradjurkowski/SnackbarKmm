package com.konradjurkowski.snackbarkmm

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

sealed class SnackBarData(
    open val snackBarType: SnackBarType = SnackBarType.SUCCESS,
    open val duration: SnackBarDuration = SnackBarDuration.MEDIUM,
    open val position: SnackBarPosition = SnackBarPosition.TOP,
) {
    data class SnackBarMessageData(
        val message: String,
        override val snackBarType: SnackBarType = SnackBarType.SUCCESS,
        override val duration: SnackBarDuration = SnackBarDuration.MEDIUM,
        override val position: SnackBarPosition = SnackBarPosition.TOP,
    ) : SnackBarData(snackBarType, duration)

    data class SnackBarResourceData(
        val messageResId: StringResource,
        override val snackBarType: SnackBarType = SnackBarType.SUCCESS,
        override val duration: SnackBarDuration = SnackBarDuration.MEDIUM,
        override val position: SnackBarPosition = SnackBarPosition.TOP,
    ) : SnackBarData(snackBarType, duration)
}

@Composable
fun SnackBarData.getMessage(): String {
    return when (this) {
        is SnackBarData.SnackBarMessageData -> message
        is SnackBarData.SnackBarResourceData -> stringResource(messageResId)
    }
}
