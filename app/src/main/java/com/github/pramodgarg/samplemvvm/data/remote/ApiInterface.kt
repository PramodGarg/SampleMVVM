package com.github.pramodgarg.samplemvvm.data.remote

import com.github.pramodgarg.samplemvvm.data.model.Repo
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("/users/{userId}/repos")
    fun getRepos(@Path("userId") userName: String): Single<List<Repo>>
}