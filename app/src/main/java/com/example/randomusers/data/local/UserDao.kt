package com.example.randomusers.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.randomusers.data.model.Result


@Dao
interface UserDao{
    @Query("SELECT * from users")
     fun getUsers(): LiveData<List<Result>>

    @Insert
    suspend fun addUser(user: Result)

    @Delete
    suspend fun deleteUser(user: Result)
}