package com.example.shaadicom

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class Network {
    val baseUrl = "https://randomuser.me/"

    object RetrofitHelper {
        val baseUrl = "https://randomuser.me/"


        fun getInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                // we need to add converter factory to
                // convert JSON object to Java object
                .build()
        }
    }
    private fun provideHttpLogger(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY)

    private fun provideOKHttp(logger: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()

        with(okHttpClient) {
            addInterceptor(logger)
            callTimeout(60L, TimeUnit.SECONDS)
            readTimeout(60L, TimeUnit.SECONDS)
            writeTimeout(60L, TimeUnit.SECONDS)
            connectTimeout(60L, TimeUnit.SECONDS)
        }
        return okHttpClient.build()
    }
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).client(provideOKHttp(
            provideHttpLogger())).addConverterFactory(GsonConverterFactory.create()).build()

    }

    private fun provideAPIService() :APIService{

        return  provideRetrofitInstance().create(APIService::class.java)
    }

    fun provideEmployeeRepository() = Repository(provideAPIService())


}