package com.konradjurkowski.snackbarkmm

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.StringResource

class SnackBarState {
    var data by mutableStateOf<SnackBarData?>(null)
        private set

    internal var updated by mutableStateOf(0)
        private set

    fun clear() {
        data = null
        updated = 0
    }

    fun showSuccess(
        message: String,
        duration: SnackBarDuration = SnackBarDuration.MEDIUM,
        position: SnackBarPosition = SnackBarPosition.TOP,
    ) {
        showMessage(message, SnackBarType.SUCCESS, duration, position)
    }

    fun showError(
        message: String,
        duration: SnackBarDuration = SnackBarDuration.MEDIUM,
        position: SnackBarPosition = SnackBarPosition.TOP,
    ) {
        showMessage(message, SnackBarType.ERROR, duration, position)
    }

    fun showInfo(
        message: String,
        duration: SnackBarDuration = SnackBarDuration.MEDIUM,
        position: SnackBarPosition = SnackBarPosition.TOP,
    ) {
        showMessage(message, SnackBarType.INFO, duration, position)
    }

    fun showSuccess(
        message: StringResource,
        duration: SnackBarDuration = SnackBarDuration.MEDIUM,
        position: SnackBarPosition = SnackBarPosition.TOP,
    ) {
        showMessage(message, SnackBarType.SUCCESS, duration, position)
    }

    fun showError(
        message: StringResource,
        duration: SnackBarDuration = SnackBarDuration.MEDIUM,
        position: SnackBarPosition = SnackBarPosition.TOP,
    ) {
        showMessage(message, SnackBarType.ERROR, duration, position)
    }

    fun showInfo(
        message: StringResource,
        duration: SnackBarDuration = SnackBarDuration.MEDIUM,
        position: SnackBarPosition = SnackBarPosition.TOP,
    ) {
        showMessage(message, SnackBarType.INFO, duration, position)
    }

    private fun showMessage(
        message: String,
        type: SnackBarType,
        duration: SnackBarDuration,
        position: SnackBarPosition,
    ) {
        this.data = SnackBarData.SnackBarMessageData(message, type, duration, position)
        updated++
    }

    private fun showMessage(
        message: StringResource,
        type: SnackBarType,
        duration: SnackBarDuration,
        position: SnackBarPosition,
    ) {
        this.data = SnackBarData.SnackBarResourceData(message, type, duration, position)
        updated++
    }
}

enum class SnackBarDuration(val durationTime: Long) {
    SHORT(2000L),
    MEDIUM(3500L),
    LONG(5000L),
    EXTRA_LONG(8000L),
}

enum class SnackBarType(val color: Color) {
    SUCCESS(Color(0xFF4A3FD1)),
    ERROR(Color(0xFFD13F51)),
    INFO(Color(0xFFBED5E)),
}

enum class SnackBarPosition {
    TOP,
    BOTTOM,
}
