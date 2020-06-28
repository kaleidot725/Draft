package jp.kaleidot725.emomemo.ui.controller

import android.os.Handler
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class SafetyHandler(private val runnable: Runnable) : DefaultLifecycleObserver {
    private var handler: Handler? = null
    private val isRunning: Boolean get() = (handler != null)

    fun postDelayed(delayMs: Long) {
        if (isRunning) {
            handler?.removeCallbacks(runnable)
            handler = null
        }

        handler = Handler()
        handler?.postDelayed(runnable, delayMs)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        handler?.removeCallbacks(runnable)
        handler = null
    }
}
