package com.github.pramodgarg.samplemvvm.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private val BASE_URL = "https://api.github.com/"
        private var retrofit: Retrofit? = null

        @JvmStatic
        fun getApiInterface(): ApiInterface {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(OkHttpClient.Builder().build())
                        .build()
            }
            return retrofit!!.create(ApiInterface::class.java)
        }
    }
}