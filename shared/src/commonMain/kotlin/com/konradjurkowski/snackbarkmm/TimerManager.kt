package com.konradjurkowski.snackbarkmm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerManager {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private var job: Job? = null

    fun scheduleTimer(
        visibilityDuration: Long,
        onTimerTriggered: () -> Unit
    ) {
        cancelTimer()

        job = scope.launch {
            delay(visibilityDuration)
            onTimerTriggered()
        }
    }

    fun cancelTimer() {
        job?.cancel()
        job = null
    }
}
