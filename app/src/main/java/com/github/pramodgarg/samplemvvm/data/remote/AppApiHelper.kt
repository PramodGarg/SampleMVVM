package com.github.pramodgarg.samplemvvm.data.remote

/**
 * Created by pramod on 21/03/18.
 */
class AppApiHelper : ApiHelper {
    override fun fetchUserRepos(name: String) = RetrofitClient.getApiInterface().getRepos(name)
}