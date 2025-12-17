package com.example.randomusers.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.randomusers.presentation.UserState
import okhttp3.internal.connection.RouteSelector
import org.w3c.dom.Text
import javax.annotation.meta.When

val countrys = listOf<String>("AU", "BR", "CA", "CH", "DE", "DK", "ES", "FI", "FR", "GB", "IE", "IN", "IR", "MX", "NL", "NO", "NZ", "RS", "TR", "UA", "US")
val genders = listOf<String>("Male","Female")

@Composable
fun CreateUserScreen(
    onButtonClick: (String, String) -> Unit,
    showSnackbar: (String) -> Unit,
    state: UserState,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        var chosenGender by remember { mutableStateOf(genders[0])}
        var chosenCountry by remember { mutableStateOf(countrys[0]) }
        Text(
            text = "Select gender:",
            modifier = Modifier.padding(8.dp)
        )

        ChoseOption(
            onChose = {
                chosenGender = it
            },
            options = genders,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = Color.Black
                )
                .padding(8.dp)
        )

        Text(
            text = "Select countrys:"
            ,
            modifier = Modifier.padding(8.dp)
        )

        ChoseOption(
            onChose = {
                chosenCountry = it
            },
            options = countrys,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = Color.Black
                )
                .padding(8.dp)
        )

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                when (state) {
                    UserState.Loading -> {
                        Log.d("Debug","loading")
                        CircularProgressIndicator(
                            modifier = Modifier.width(40.dp),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }
                    UserState.Error -> {
                        Log.d("Debug","snackbar")
                        showSnackbar("Something go wrong")
                    }
                    UserState.Succses -> {
                        Log.d("Debug","snackbar")
                        showSnackbar("Create new user")
                    }

                    UserState.Normal -> {
                        Log.d("Debug","normal")
                    }

                }
                FilledTonalButton(
                    onClick = {
                        onButtonClick(chosenGender, chosenCountry)
                    },
                    enabled = (state != UserState.Loading)
                ) {
                    Text(
                        text = "Create"
                    )
                }
            }
        }
    }
}

@Composable
fun ChoseOption(
    onChose: (String) -> Unit,
    options: List<String>,
    modifier: Modifier = Modifier
){
    var text by remember { mutableStateOf(options[0]) }
    var expanded by remember { mutableStateOf(false) }
    Column( modifier = modifier) {
        Row(
            modifier = Modifier.clickable(
                onClick = {
                    expanded = true
                }
            )
        ) {
            Text(
                text = text,
                modifier = Modifier
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach{option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option
                        )
                    },
                    onClick = {
                        text = option
                        onChose(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
