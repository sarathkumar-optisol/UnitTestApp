package com.example.unittestapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by SARATH on 10-04-2021
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM userprofiledata")
    fun getUserProfileList() : List<UserProfile>

    @Query("SELECT * FROM userprofiledata WHERE email = :key")
    fun getUserLoginDetail(key: String) : UserProfile

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(userProfile: UserProfile) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserData(userName : String , password : String) : UserProfile


}