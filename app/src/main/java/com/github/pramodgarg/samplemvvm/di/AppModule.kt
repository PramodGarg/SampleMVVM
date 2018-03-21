package com.github.pramodgarg.samplemvvm.di

import com.github.pramodgarg.samplemvvm.data.remote.ApiHelper
import com.github.pramodgarg.samplemvvm.data.remote.AppApiHelper
import com.github.pramodgarg.samplemvvm.ui.ListRepoActivity
import com.github.pramodgarg.samplemvvm.ui.viewmodel.ListRepoModelFactory
import com.github.pramodgarg.samplemvvm.utils.rx.AppSchedulerProvider
import com.github.pramodgarg.samplemvvm.utils.rx.SchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesCompositeDisposable() = CompositeDisposable()

        @JvmStatic
        @Singleton
        @Provides
        fun providesViewModelFactory(schedulerProvider: SchedulerProvider,
                                     apiHelper: ApiHelper, compositeDisposable: CompositeDisposable): ListRepoModelFactory {
            return ListRepoModelFactory(schedulerProvider, apiHelper, compositeDisposable)
        }
    }

    @Singleton
    @Binds
    abstract fun providesSchedulers(scheduleProvider: AppSchedulerProvider): SchedulerProvider

    @Singleton
    @Binds
    abstract fun providesApiHelper(apiHelper: AppApiHelper): ApiHelper
}