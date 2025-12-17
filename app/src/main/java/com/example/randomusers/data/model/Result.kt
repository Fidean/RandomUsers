package com.example.randomusers.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "users"
)
@Serializable
data class Result (

    var results : ArrayList<Results>,
    @PrimaryKey
    var info    : Info

)

fun getInfo(): Result {
    return Result(
        results = arrayListOf(Results(
            gender = "female",
            name = Name(
                title = "Mademoiselle",
                first = "Margareta",
                last = "Martinez"
            ),
            location = Location(
                street = Street(
                    number = 9075,
                    name = "Rue des Jardins"
                ),
                city = "Drei Höfe",
                state = "Ticino",
                country = "Switzerland",
                postcode = "7671",
                coordinates = Coordinates(
                    latitude = "-57.3063",
                    longitude = "45.6660"
                ),
                timezone = Timezone(
                    offset = "-8:00",
                    description = "Pacific Time (US & Canada)"
                )
            ),
            email = "margareta.martinez@example.com",
            login = Login(
                uuid = "a42eb3c1-b2d4-41a4-a25f-5e350bd70853",
                username = "greenwolf384",
                password = "bigbob",
                salt = "Tl3pePp3",
                md5 = "e03c92b4d4b758e9f49f57a083016bc8",
                sha1 = "9b8c87fbc0b38c0a35f4442a406627743037290e",
                sha256 = "f42d548da69eaa2d01e56288bf009c4310151e52eef5cce8bdb9d720784c0faf"
            ),
            dob = Dob(
                date = "1994-10-21T13:30:02.341Z",
                age = 31
            ),
            registered = Registered(
                date = "2006-12-01T07:12:55.651Z",
                age = 18
            ),
            phone = "079 405 32 05",
            cell = "076 080 61 40",
            id = Id(
                name = "AVS",
                value = "756.0541.4881.59"
            ),
            picture = Picture(
                large = "https://randomuser.me/api/portraits/women/34.jpg",
                medium = "https://randomuser.me/api/portraits/med/women/34.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/women/34.jpg"
            ),
            nat = "CH"
        )),
        info = Info(
            seed = "00b007be2fffb0f5",
            results = 1,
            page = 1,
            version = "1.4"
        )
    )
}

@Serializable
data class Name (

    var title : String,
    var first : String,
    var last  : String

)

@Serializable
data class Street (

    var number : Int,
    var name   : String

)

@Serializable
data class Coordinates (

    var latitude  : String,
    var longitude : String

)

@Serializable
data class Timezone (

    var offset      : String,
   var description : String

)

@Serializable
data class Location (

    var street      : Street,
    var city        : String,
    var state       : String,
    var country     : String,
    var postcode    : String,
    var coordinates : Coordinates,
    var timezone    : Timezone

)


@Serializable
data class Login (

    var uuid     : String,
    var username : String,
    var password : String,
    var salt     : String,
    var md5      : String,
    var sha1     : String,
    var sha256   : String

)

@Serializable
data class Dob (

    var date : String,
    var age  : Int

)

@Serializable
data class Registered (

    var date : String,
    var age  : Int

)

@Serializable
data class Id (

    var name  : String,
    var value : String?

)

@Serializable
data class Picture (

    var large     : String,
    var medium    : String,
    var thumbnail : String

)

@Serializable
data class Results (

    var gender     : String,
    var name       : Name,
    var location   : Location,
    var email      : String,
    var login      : Login,
    var dob        : Dob,
    var registered : Registered,
    var phone      : String,
    var cell       : String,
    var id         : Id,
    var picture    : Picture,
    var nat        : String

)

@Serializable
data class Info (

    var seed    : String,
    var results : Int,
    var page    : Int,
    var version : String

)