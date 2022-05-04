package com.brazhnik.fobres.repository.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkAPI {

    private var mInstance: NetworkAPI? = null
    private val BASE_URL = "http://10.0.2.2:8080/"
    private var retrofit: Retrofit? = null

    init {
        retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .build()
    }

    fun getInstance(): NetworkAPI? {
        if (mInstance == null) {
            mInstance = NetworkAPI()
        }
        return mInstance
    }

    fun getJSONApi(): PlaceHolderAPI? {
        return retrofit!!.create(PlaceHolderAPI::class.java)
    }
}