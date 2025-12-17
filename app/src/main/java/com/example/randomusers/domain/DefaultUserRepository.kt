package com.example.randomusers.domain

import androidx.lifecycle.LiveData
import com.example.randomusers.data.local.LocalDataSource
import com.example.randomusers.data.model.Result
import com.example.randomusers.data.remote.NetworkDataSource
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
): UserRepository {
    override fun getUsers(): LiveData<List<Result>> {
        return localDataSource.getUsers()
    }

    override suspend fun getUser(gender : String, nat : String) {
        val user = networkDataSource.getUser(gender, nat)
        localDataSource.addUser(user)
    }

    override suspend fun deleteUser(user: Result) {
        localDataSource.deleteUser(user)
    }

}