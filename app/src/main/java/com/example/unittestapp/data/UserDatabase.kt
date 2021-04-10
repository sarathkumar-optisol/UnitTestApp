package com.example.unittestapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by SARATH on 10-04-2021
 */
@Database(
    entities = [UserProfile::class],

    version = 1
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao() : UserDao

}