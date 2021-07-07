package com.vaibhavmojidra.retrofitdemokotlin

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {

    companion object{
        private const val BASE_URL="https://jsonplaceholder.typicode.com"
        val interceptor=HttpLoggingInterceptor().apply {
            this.level=HttpLoggingInterceptor.Level.BODY // to get logs of network operations
        }
        val client=OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(30,TimeUnit.SECONDS) //sets the time out for connect server default is 10 secs
                .readTimeout(20,TimeUnit.SECONDS) //sets the time out for reading data
                .writeTimeout(25,TimeUnit.SECONDS) //sets the time out for writing data
        }.build()
        fun getRetrofitInstance():Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}