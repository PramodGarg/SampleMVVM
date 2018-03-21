package com.github.pramodgarg.samplemvvm.utils.rx

import io.reactivex.Scheduler

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