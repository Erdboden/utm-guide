package com.example.utmguide.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val BASE_URL = "https://graph.microsoft.com"

    var retrofit: Retrofit? = null

    fun getClient(): ApiClient {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return this
    }


    private fun getOkHttpClient(): OkHttpClient {
        val log = HttpLoggingInterceptor()
        log.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(log)
            .build()
    }

    fun buildGraphApi(): GraphApi = retrofit?.create(GraphApi::class.java)!!
}