package com.brazhnik.fobres.data.network

import com.brazhnik.fobres.data.SharedData.Companion.TIME_WAIT_RESPONSE_API
import com.brazhnik.fobres.data.network.api.*
import com.google.gson.GsonBuilder
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
                .connectTimeout(TIME_WAIT_RESPONSE_API.toLong(), TimeUnit.MILLISECONDS)
                .readTimeout(TIME_WAIT_RESPONSE_API.toLong(), TimeUnit.MILLISECONDS)
                .writeTimeout(TIME_WAIT_RESPONSE_API.toLong(), TimeUnit.MILLISECONDS)
                .build()


        val gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
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

    fun getJSONImageAPI(): PlaceHolderImageAPI {
        return retrofit.create(PlaceHolderImageAPI::class.java)
    }

    fun getJSONLoginAPI(): PlaceHolderLoginAPI {
        return retrofit.create(PlaceHolderLoginAPI::class.java)
    }

    fun getJSONRegistrationAPI(): PlaceHolderRegistrationAPI {
        return retrofit.create(PlaceHolderRegistrationAPI::class.java)
    }
}