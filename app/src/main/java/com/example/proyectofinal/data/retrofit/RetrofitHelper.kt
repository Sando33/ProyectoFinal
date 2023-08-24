package com.example.proyectofinal.data.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}