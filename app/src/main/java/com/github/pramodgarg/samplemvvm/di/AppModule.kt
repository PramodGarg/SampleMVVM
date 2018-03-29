/*
 * Copyright (C) 2018 Pramod Garg
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   You may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 *   See the License for the specific language governing permissions and limitations under the License.
 */

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