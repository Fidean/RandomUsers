package com.example.randomusers.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.randomusers.R
import com.example.randomusers.data.model.Dob
import com.example.randomusers.data.model.Location
import com.example.randomusers.data.model.Login
import com.example.randomusers.data.model.Name
import com.example.randomusers.data.model.Results
import com.example.randomusers.data.model.getInfo

@Composable
fun UserScreen(
    user: Results,
    modifier: Modifier = Modifier
){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(user.picture.large)
                .build(),
            error = painterResource(R.drawable.outline_error),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(200.dp).clip(CircleShape)
        )
        Text(
            text = "${user.name.first} ${user.name.last}",
            modifier = Modifier.padding(8.dp)
        )
        ElevatedCard(

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(color = Color.Blue)

            ) {
                var selectedIndex by remember { mutableIntStateOf(0) }
                val options = listOf(
                    Icons.Default.AccountCircle,
                    Icons.Default.Phone,
                    Icons.Default.Email,
                    Icons.Default.Place
                )
                Row() {
                    options.forEachIndexed { index, icon ->
                        if (selectedIndex == index) {
                            InfoIcon(
                                icon = icon,
                                onClick = {
                                    selectedIndex = index
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .background(color = Color.White)
                            )
                        } else {
                            InfoIcon(
                                icon = icon,
                                onClick = {
                                    selectedIndex = index
                                },
                                modifier = Modifier
                                    .weight(1f)
                            )
                        }

                    }
                }
                when (selectedIndex) {
                    0 -> {
                        PersonInfo(
                            name = user.name,
                            gender = user.gender,
                            dob = user.dob,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    1 -> {
                        PhoneInfo(
                            phone = user.phone,
                            cell = user.cell,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    2 -> {
                        EmailInfo(
                            email = user.email,
                            login = user.login,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    3 -> {
                        LocationInfo(
                            location = user.location,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun InfoIcon(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier
){
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Composable
fun PersonInfo(
    name: Name,
    gender: String,
    dob: Dob,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.background(color = Color.White)

    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.Blue
                    )
                ) {
                    append("First name")
                }
                append(": ${name.first}")
            },
            modifier = Modifier.padding(4.dp)
        )
        Text(
            buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.Blue
                    )
                ) {
                    append("Last name")
                }
                append(": ${name.last}")
            },
            modifier = Modifier.padding(4.dp)
        )
        Text(
            buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.Blue
                    )
                ) {
                    append("Gender")
                }
                append(": $gender")
            },
            modifier = Modifier.padding(4.dp)
        )
        Text(
            buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.Blue
                    )
                ) {
                    append("Age")
                }
                append(": ${dob.age}")
            },
            modifier = Modifier.padding(4.dp)
        )
        Text(
            buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.Blue
                    )
                ) {
                    append("Date od birth")
                }
                append(": ${dob.date}")
            },
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun PhoneInfo(
    phone: String,
    cell: String,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.background(color = Color.White)

    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.Blue
                    )
                ) {
                    append("Phone")
                }
                append(": $phone")
            },
            modifier = Modifier.padding(4.dp)
        )
        Text(
            buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.Blue
                    )
                ) {
                    append("Cell")
                }
                append(": $cell")
            },
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun EmailInfo(
    email: String,
    login: Login,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.background(color = Color.White)

    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.Blue
                    )
                ) {
                    append("Email")
                }
                append(": $email")
            },
            modifier = Modifier.padding(4.dp)
        )
        Text(
            buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.Blue
                    )
                ) {
                    append("Username")
                }
                append(": ${login.username}")
            },
            modifier = Modifier.padding(4.dp)
        )
        Text(
            buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.Blue
                    )
                ) {
                    append("Password")
                }
                append(": ${login.password}")
            },
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun LocationInfo(
    location: Location,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.background(color = Color.White)

    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.Blue
                    )
                ) {
                    append("Street")
                }
                append(": ${location.street.name}, ${location.street.number}")
            },
            modifier = Modifier.padding(4.dp)
        )
        Text(
            buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.Blue
                    )
                ) {
                    append("City")
                }
                append(": ${location.city}")
            },
            modifier = Modifier.padding(4.dp)
        )
        Text(
            buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.Blue
                    )
                ) {
                    append("Country")
                }
                append(": ${location.country}")
            },
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun UserScreenPreview(){
   UserScreen(
       user = getInfo().results[0]
   )
}