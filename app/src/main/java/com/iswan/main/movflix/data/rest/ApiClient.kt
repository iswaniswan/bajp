package com.iswan.main.movflix.data.rest

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.iswan.main.movflix.data.Config
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val client: OkHttpClient.Builder = OkHttpClient.Builder()

    private val gson: Gson =
        GsonBuilder()
            .setLenient()
            .create()

    private lateinit var retrofit : Retrofit

    fun instance(): ApiInterface {
        retrofit = Retrofit.Builder()
            .baseUrl(Config.TMDB_API_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(ApiInterface::class.java)
    }
}