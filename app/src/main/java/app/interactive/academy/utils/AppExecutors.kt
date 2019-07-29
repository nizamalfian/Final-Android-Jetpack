package app.interactive.academy.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by nizamalfian on 26/07/2019.
 */
open class AppExecutors(
    val diskIO:Executor = DiskIOThreadExecutor(),
    val networkIO:Executor = Executors.newFixedThreadPool(THREAD_COUNT),
    val mainThread:Executor = MainThreadExecutor()) {

    companion object{
        const val THREAD_COUNT=3
        private class MainThreadExecutor:Executor{
            private val mainThreadHandler = Handler(Looper.getMainLooper())

            override fun execute(command: Runnable) {
                mainThreadHandler.post(command)
            }
        }
    }
}