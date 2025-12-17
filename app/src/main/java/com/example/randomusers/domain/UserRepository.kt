package com.example.randomusers.domain

import androidx.lifecycle.LiveData
import com.example.randomusers.data.model.Result


interface UserRepository {
    fun getUsers(): LiveData<List<Result>>
    suspend fun getUser(gender : String, nat : String)
    suspend fun deleteUser(user: Result)
}