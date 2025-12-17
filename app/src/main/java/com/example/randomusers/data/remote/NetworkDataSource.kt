package com.example.randomusers.data.remote

import com.example.randomusers.data.model.Result
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getUser(gender : String, nat : String): Result{
        return apiService.getUser(gender,nat)
    }
}