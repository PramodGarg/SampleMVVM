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

public class ListRepoViewModel(private val schedulerProvider: SchedulerProvider, private val apiHelper: ApiHelper) : ViewModel() {

    private val compositeDisposable by lazy { CompositeDisposable() }

    val email = ObservableField<String>()
    val isLoading = ObservableBoolean(false)
    var repo = MutableLiveData<List<Repo>>()

    init {
        compositeDisposable.add(RxBinding.toObservable(email)
                .subscribeOn(schedulerProvider.io())
                // wait 1 second after all typing events are fired, then make api request
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(schedulerProvider.main())
                .subscribe { if (it.isNotEmpty()) getRepos(it) })
    }

    /**
     * fetch github repositories for the username entered
     */
    private fun getRepos(userName: String) {
        isLoading.set(true)
        compositeDisposable.add(apiHelper.fetchUserRepos(userName).subscribeOn(Schedulers.io())
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.main())
                .subscribe({ list ->
                    repo.value = list
                    isLoading.set(false)
                }, { error ->
                    isLoading.set(false)
                    Log.d("error", "" + error.message)
                }))
    }

    override fun onCleared() {
        // dispose all observable when viewmodel is to be destroyed to prevent memory leaks
        compositeDisposable.dispose()
        super.onCleared()
    }
}