package com.example.randomusers.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.randomusers.data.model.Result
import com.example.randomusers.data.model.Results
import com.example.randomusers.data.model.getInfo
import com.murgupluoglu.flagkit.FlagKit
import java.util.Locale
import java.util.Locale.getDefault

@Composable
fun AllUsersScreen(
    users: List<Result>,
    onButtonClick: () -> Unit,
    onCardClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){

    LazyColumn(
        modifier = modifier
    ) {
        items(users.size) { user ->
            UserCard(
                user = users[user].results[0],
                onClick = {
                    Log.d("Debug", "chose user $user")
                    onCardClick(user)
                },
                onDeleteClick = {
                    onDeleteClick(user)
                },
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            )
        }
    }
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        FilledIconButton(
            onClick = {
                onButtonClick()
            },
            modifier = Modifier.Companion
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
    }

}

@Composable
fun UserCard(
    user: Results,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        onClick = {
            onClick()
        },
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            var expanded by remember { mutableStateOf(false) }
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier.fillMaxSize()
            ) {
                Column {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More options")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Delete") },
                            onClick = { onDeleteClick() }
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.padding(4.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(user.picture.large)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(100.dp).clip(RoundedCornerShape(10.dp))
                )
                Column(
                    modifier = Modifier.padding(start = 4.dp)
                ) {
                    Text(
                        text = "${user.name.first} ${user.name.last}",
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        text = user.phone,
                        modifier = Modifier.padding(4.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Log.d("Debug", "1${user.nat.lowercase(getDefault())}1")
                        Image(
                            painterResource(FlagKit.getResId(user.nat.lowercase(getDefault()))),
                            contentDescription = null
                        )
                        Text(
                            text = user.nat,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun AllUsersScreenPreview(){
    AllUsersScreen(
        users = listOf(getInfo()),
        onButtonClick = {},
        onCardClick = {},
        onDeleteClick = {}
    )
}