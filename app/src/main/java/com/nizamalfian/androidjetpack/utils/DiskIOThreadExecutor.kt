package com.nizamalfian.androidjetpack.utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by nizamalfian on 26/07/2019.
 */
class DiskIOThreadExecutor(private val diskIO:Executor=Executors.newSingleThreadExecutor()):Executor {
    override fun execute(command: Runnable) {
        diskIO.execute(command)
    }
}