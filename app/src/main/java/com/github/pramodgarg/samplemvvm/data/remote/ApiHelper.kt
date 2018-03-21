package com.github.pramodgarg.samplemvvm.data.remote

import com.github.pramodgarg.samplemvvm.data.model.Repo
import io.reactivex.Single

interface ApiHelper {
    /**
     * fetch github repositories for username provided
     */
    fun fetchUserRepos(name: String): Single<List<Repo>>
}