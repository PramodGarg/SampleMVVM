package com.github.pramodgarg.samplemvvm.network

import com.github.pramodgarg.samplemvvm.model.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/users/{userId}/repos")
    abstract fun getRepos(@Path("userId") userName: String): Call<List<Repo>>
}