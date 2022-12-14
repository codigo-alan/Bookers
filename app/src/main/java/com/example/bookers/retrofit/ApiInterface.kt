package com.example.bookers.retrofit

import com.example.bookers.models.gsonModels.Data
import com.example.bookers.models.gsonModels.Item
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {

    @GET()
    suspend fun getData(@Url url: String): Response<Data>

    companion object {
        val BASE_URL = "https://www.googleapis.com/books/v1/"

        fun create(): ApiInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }

}