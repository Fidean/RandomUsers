package com.example.randomusers.data.remote

import com.example.randomusers.data.model.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("api")
    suspend fun getUser(
        @Query("gender") gender : String,
        @Query("nat") nat : String
                        ): Result

}