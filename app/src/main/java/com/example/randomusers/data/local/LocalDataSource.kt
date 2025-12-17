package com.example.randomusers.data.local

import androidx.lifecycle.LiveData
import com.example.randomusers.data.model.Result
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val userDao: UserDao
) {
    fun getUsers(): LiveData<List<Result>> {
        return userDao.getUsers()
    }

    suspend fun addUser(user: Result) {
        userDao.addUser(user)
    }

    suspend fun deleteUser(user: Result) {
        userDao.deleteUser(user)
    }
}
