package com.brazhnik.fobres.data.network

import com.brazhnik.fobres.data.network.api.PlaceHolderProfileAPI
import com.brazhnik.fobres.data.network.api.PlaceHolderRatingAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkAPI {

    private lateinit var mInstance: NetworkAPI
    private val url = "http://10.0.2.2:8080/"
    private var retrofit: Retrofit

    init {
        val okHttpClient =
            OkHttpClient().newBuilder()
                .connectTimeout(1500, TimeUnit.MILLISECONDS)
                .readTimeout(1500, TimeUnit.MILLISECONDS)
                .writeTimeout(1500, TimeUnit.MILLISECONDS)
                .build()


        retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .client(okHttpClient)
            .build()
    }

    fun getInstance(): NetworkAPI {
        return mInstance
    }

    fun getJSONRatingAPI(): PlaceHolderRatingAPI {
        return retrofit.create(PlaceHolderRatingAPI::class.java)
    }

    fun getJSONProfileAPI(): PlaceHolderProfileAPI {
        return retrofit.create(PlaceHolderProfileAPI::class.java)
    }
}