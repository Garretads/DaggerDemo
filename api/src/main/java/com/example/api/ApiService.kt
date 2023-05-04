package com.example.api

import com.example.api.domain.Articles
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("v2/everything?q=android")
    suspend fun allArticles(): Articles

}



fun buildApiService(apiKey: String): ApiService {

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val authorizedRequest = chain.request().newBuilder()
                .addHeader(HEADER_API_KEY, apiKey)
                .build()
            chain.proceed(authorizedRequest)
        }
        .build()

    val json = Json {
        ignoreUnknownKeys = true
    }


    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(json.asConverterFactory(MediaType.parse("application/json")!!))
        .build()

    return retrofit.create(ApiService::class.java)

}

private const val BASE_URL = "https://newsapi.org/"
private const val HEADER_API_KEY = "X-Api-Key"
