package com.brazhnik.fobres.repository.network

import com.brazhnik.fobres.repository.network.api.PlaceHolderProfileAPI
import com.brazhnik.fobres.repository.network.api.PlaceHolderRatingAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkAPI {

    private lateinit var mInstance: NetworkAPI
    private val url = "http://10.0.2.2:8080/"
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .build()

    fun getInstance(): NetworkAPI {
        if (mInstance == null) {
            mInstance = NetworkAPI()
        }
        return mInstance
    }

    fun getJSONRatingAPI(): PlaceHolderRatingAPI {
        return retrofit.create(PlaceHolderRatingAPI::class.java)
    }

    fun getJSONProfileAPI(): PlaceHolderProfileAPI {
        return retrofit.create(PlaceHolderProfileAPI::class.java)
    }
}