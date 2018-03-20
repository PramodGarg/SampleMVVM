package com.github.pramodgarg.samplemvvm.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by pramod on 20/03/18.
 */
class RetrofitClient {
    companion object {
        private val BASE_URL = "https://api.github.com/"
        private var retrofit: Retrofit? = null

        @JvmStatic
        fun getApiInterface(): ApiInterface {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(OkHttpClient.Builder().build())
                        .build()
            }
            return retrofit!!.create(ApiInterface::class.java)
        }
    }
}