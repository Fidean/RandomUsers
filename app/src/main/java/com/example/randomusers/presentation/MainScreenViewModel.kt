package com.example.randomusers.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomusers.data.model.Result
import com.example.randomusers.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

enum class UserState{
    Normal,
    Succses,
    Loading,
    Error
}

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {

    lateinit var usersList: LiveData<List<Result>>
    val _state = MutableStateFlow(UserState.Normal)
    val state = _state.asStateFlow()

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            usersList = repository.getUsers()
        }
    }

    fun addUser(gender: String, nat: String) {
        Log.d("Debug", "start get")
        _state.value = UserState.Loading
        viewModelScope.launch {
            try {
                Log.d("Debug", "start call")
                repository.getUser(gender, nat)
                _state.value = UserState.Succses
            } catch (e: IOException) {
                Log.d("Debug", "IOException")
                Log.d("Debug", "${e.message}")
                _state.value = UserState.Error
            }catch (e: Exception){
                Log.d("Debug", "Exception")
                Log.d("Debug", "${e.message}")
                _state.value = UserState.Error
            }
        }

        Log.d("Debug", "start call")
        _state.value = UserState.Normal

    }

    fun deleteUser(user: Result){
       viewModelScope.launch {
           repository.deleteUser(user)
       }
    }
}