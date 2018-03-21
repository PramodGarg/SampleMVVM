package com.github.pramodgarg.samplemvvm.utils.rx

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by pramod on 21/03/18.
 */
class AppSchedulerProvider : SchedulerProvider {

    override fun io() = Schedulers.io()

    override fun main() = AndroidSchedulers.mainThread()
}