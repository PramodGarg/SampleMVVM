package com.github.pramodgarg.samplemvvm.utils.rx

import io.reactivex.Scheduler

/**
 * Created by pramod on 21/03/18.
 */
interface SchedulerProvider {
    /**
     * get background scheduler for making io calls
     */
    fun io(): Scheduler

    /**
     * get main thread scheduler for making ui changes
     */
    fun main(): Scheduler
}