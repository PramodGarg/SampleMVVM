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

package com.github.pramodgarg.samplemvvm.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import com.github.pramodgarg.samplemvvm.data.model.Repo
import com.github.pramodgarg.samplemvvm.data.remote.ApiHelper
import com.github.pramodgarg.samplemvvm.utils.rx.RxBinding
import com.github.pramodgarg.samplemvvm.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ListRepoViewModel(
        private val schedulerProvider: SchedulerProvider, private val apiHelper: ApiHelper,
        private val mCompositeDisposable: CompositeDisposable) : ViewModel() {

    val email = ObservableField<String>()
    val isLoading = ObservableBoolean(false)
    var repo = MutableLiveData<List<Repo>>()

    init {
        mCompositeDisposable.add(RxBinding.toObservable(email)
                .subscribeOn(schedulerProvider.io())
                // wait 1 second after all typing events are fired, then make api request
                .debounce(1000, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .observeOn(schedulerProvider.main())
                .subscribe { if (it.isNotEmpty()) getRepos(it) })
    }

    /**
     * fetch github repositories for the username entered
     */
    private fun getRepos(userName: String) {
        mCompositeDisposable.add(apiHelper.fetchUserRepos(userName).subscribeOn(Schedulers.io())
                .subscribeOn(schedulerProvider.io())
                // show progress bar when subscribed
                .doOnSubscribe { isLoading.set(true) }
                .observeOn(schedulerProvider.main())
                .subscribe({ list ->
                    repo.value = list
                    isLoading.set(false)
                }, { error ->
                    repo.value = emptyList()
                    isLoading.set(false)
                    Log.d("error", "" + error.message)
                }))
    }

    override fun onCleared() {
        // dispose all observable when viewmodel is to be destroyed to prevent memory leaks
        mCompositeDisposable.dispose()
        super.onCleared()
    }
}