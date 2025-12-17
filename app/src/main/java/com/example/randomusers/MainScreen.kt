@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.randomusers

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.randomusers.presentation.MainScreenViewModel
import com.example.randomusers.ui.screens.AllUsersScreen
import com.example.randomusers.ui.screens.CreateUserScreen
import com.example.randomusers.ui.screens.UserScreen
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.util.Locale
import java.util.Locale.getDefault

enum class Screens(){
    USER,
    CREATE_USER,
    ALL_USERS
}

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){

    val users by viewModel.usersList.observeAsState(listOf())
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentState = backStackEntry?.destination?.route ?: Screens.ALL_USERS.name
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },
        topBar = {
            TopAppBar(
                title = {
                    when (currentState){
                        Screens.ALL_USERS.name -> {Text(stringResource(R.string.app_name))}
                        Screens.CREATE_USER.name -> {Text(stringResource(R.string.app_name))}
                        else -> {}
                    }

                },
                navigationIcon = {
                   if (currentState != Screens.ALL_USERS.name){
                        IconButton(
                            onClick = {
                                navController.navigate(Screens.ALL_USERS.name)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                },
                modifier = Modifier
            )
        }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.CREATE_USER.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screens.ALL_USERS.name){
                AllUsersScreen(
                    users = users,
                    onButtonClick = {
                        navController.navigate(Screens.CREATE_USER.name)
                    },
                    onCardClick = {

                        navController.navigate(Screens.USER.name + "/$it")
                    },
                    onDeleteClick = {
                        viewModel.deleteUser(users[it])
                    }
                )
            }
            composable(Screens.CREATE_USER.name){
                CreateUserScreen(
                    onButtonClick = { gender, nat ->
                        Log.d("Debug","mainscreen get")
                        viewModel.addUser(
                            gender.lowercase(getDefault()),
                            nat.lowercase(getDefault())
                        )
                    },
                    showSnackbar = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                it,

                            )
                        }
                    },
                    state = state,
                    modifier = Modifier.padding(8.dp)
                )
            }
            composable(route = (Screens.USER.name + "/{userID}"),
                arguments = listOf(navArgument("userID"){ type = NavType.IntType})
                ) {
                val userId = it.arguments?.getInt("userID")
                Log.d("Debug", "open user $userId")
                UserScreen(
                    user = users[userId!!].results[0]
                )
            }
        }
    }

}

@Preview(
    showBackground = true
)
@Composable
fun MainScreenPreview(){
    MainScreen()
}