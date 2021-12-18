package com.example.shaadicom

import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("api/?results=10")
    suspend fun fetchUserData(): Response<UserModel>
}