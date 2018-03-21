package com.github.pramodgarg.samplemvvm.utils.rx

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AppSchedulerProvider @Inject constructor() : SchedulerProvider {

    override fun io() = Schedulers.io()

    override fun main() = AndroidSchedulers.mainThread()
}