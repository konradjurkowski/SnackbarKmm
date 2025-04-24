package com.konradjurkowski.snackbarkmm

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun rememberSnackBarState(): SnackBarState {
    return remember { SnackBarState() }
}

@Composable
fun ContentWithSnackBar(
    snackBarState: SnackBarState,
    snackBar: (@Composable (SnackBarData) -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        content()
        SnackBarComponent(snackBarState = snackBarState, snackBar = snackBar)
    }
}

@Composable
internal fun SnackBarComponent(
    snackBarState: SnackBarState,
    snackBar: (@Composable (SnackBarData) -> Unit)? = null,
) {
    var showMessageBar by remember { mutableStateOf(false) }
    val timerManager = remember { TimerManager() }

    LaunchedEffect(snackBarState.updated) {
        showMessageBar = false
        timerManager.cancelTimer()
        if (snackBarState.data == null) return@LaunchedEffect

        delay(100)
        showMessageBar = true
        timerManager.scheduleTimer(
            visibilityDuration = snackBarState.data?.duration?.durationTime
                ?: SnackBarDuration.MEDIUM.durationTime,
            onTimerTriggered = {
                showMessageBar = false
            }
        )
    }

    snackBarState.data?.let { data ->
        AnimatedVisibility(
            visible = showMessageBar,
            enter = slideInVertically(
                initialOffsetY = { if (data.position.isTop()) -it else it },
            ),
            exit = slideOutVertically(
                targetOffsetY = { if (data.position.isTop()) -it else it },
            ),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = if (data.position.isTop()) Arrangement.Top else Arrangement.Bottom,
            ) {
                if (snackBar != null) {
                    Box(
                        modifier = Modifier
                            .clickable {
                                showMessageBar = false
                                timerManager.cancelTimer()
                            },
                        content = { snackBar(data) },
                    )
                } else {
                    SnackBarKMM(
                        snackBarData = data,
                        onCloseClick = {
                            showMessageBar = false
                            timerManager.cancelTimer()
                        }
                    )
                }
            }
        }
    }
}
