package com.github.pramodgarg.samplemvvm

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.github.pramodgarg.samplemvvm.model.Repo
import com.github.pramodgarg.samplemvvm.network.RetrofitClient
import com.github.pramodgarg.samplemvvm.utils.RxBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

/**
 * Created by pramod on 20/03/18.
 */
class ListRepoViewModel : ViewModel() {
    
    private val compositeDisposable by lazy { CompositeDisposable() }

    val email = ObservableField<String>()
    val isLoading = ObservableBoolean(false)
    var repo = MutableLiveData<List<Repo>>()

    init {
        compositeDisposable.add(RxBinding.toObservable(email)
                .subscribeOn(Schedulers.io())
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { if (it.isNotEmpty()) fetchRepos(it) })
    }

    private fun fetchRepos(userName: String) {
        isLoading.set(true)
        RetrofitClient.getApiInterface().getRepos(userName).enqueue(object : Callback<List<Repo>> {
            override fun onFailure(call: Call<List<Repo>>?, t: Throwable?) {
                isLoading.set(false)
            }

            override fun onResponse(call: Call<List<Repo>>?, response: Response<List<Repo>>?) {
                isLoading.set(false)
                repo.value = response?.body()
            }
        })
    }
}