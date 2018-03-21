package com.github.pramodgarg.samplemvvm.data.remote

import javax.inject.Inject

/**
 * Created by pramod on 21/03/18.
 */
class AppApiHelper @Inject constructor() : ApiHelper {
    override fun fetchUserRepos(name: String) = RetrofitClient.getApiInterface().getRepos(name)
}