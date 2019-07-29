package app.interactive.academy.utils

import java.util.concurrent.Executor

/**
 * Created by L
 *
 * on 7/29/2019
 */
class InstantAppExecutors(
    instant1: Executor = Executor { it.run() },
    instant2: Executor = Executor { it.run() },
    instant3: Executor = Executor { it.run() }
) :
    AppExecutors(
        instant1,
        instant2,
        instant3
    )