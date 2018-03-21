package com.github.pramodgarg.samplemvvm.ui.di

import android.arch.lifecycle.ViewModelProviders
import com.github.pramodgarg.samplemvvm.data.remote.ApiHelper
import com.github.pramodgarg.samplemvvm.di.annotation.PerActivity
import com.github.pramodgarg.samplemvvm.ui.ListRepoActivity
import com.github.pramodgarg.samplemvvm.ui.viewmodel.ListRepoModelFactory
import com.github.pramodgarg.samplemvvm.ui.viewmodel.ListRepoViewModel
import com.github.pramodgarg.samplemvvm.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by pramod on 21/03/18.
 */

@Module
abstract class ListRepoModule {

    @Module
    companion object {
        @JvmStatic
        @PerActivity
        @Provides
        fun providesViewModel(activity: ListRepoActivity, schedulerProvider: SchedulerProvider,
                              apiHelper: ApiHelper, compositeDisposable: CompositeDisposable): ListRepoViewModel {
            return ViewModelProviders.of(activity, ListRepoModelFactory(schedulerProvider, apiHelper, compositeDisposable))
                    .get(ListRepoViewModel::class.java)
        }
    }
}