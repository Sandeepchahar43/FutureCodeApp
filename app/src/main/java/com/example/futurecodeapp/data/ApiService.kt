package com.example.futurecodeapp.data

import retrofit2.http.GET

interface ApiService {


    @GET("task/user.json")
    suspend fun getUsers():UserResponse
}