package com.example.randomusers.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.randomusers.data.model.Info
import com.example.randomusers.data.model.Result
import com.example.randomusers.data.model.Results
import kotlinx.serialization.json.Json

class ResultConverter {
    @TypeConverter
    fun stringToResult(string: String): ArrayList<Results> = Json.decodeFromString(string)

    @TypeConverter
    fun resultToString(result: ArrayList<Results>): String = Json.encodeToString(result)

    @TypeConverter
    fun stringToInfo(string: String): Info = Json.decodeFromString(string)

    @TypeConverter
    fun infoToString(result: Info): String = Json.encodeToString(result)
}

@Database(entities = [Result::class],version = 1)
@TypeConverters(ResultConverter::class)
abstract class UserRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}