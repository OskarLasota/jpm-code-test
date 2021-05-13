package com.frezzcoding.jpm.data.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val API_BASE_URL = "https://jsonplaceholder.typicode.com/"
    private var apiService : ApiService? = null

    fun build(): ApiService?{
        val gson = GsonBuilder()
            .setLenient()
            .create()

        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        apiService = retrofit.create(
            ApiService::class.java)

        return apiService
    }

}