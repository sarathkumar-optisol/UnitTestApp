package com.example.unittestapp.main

import com.example.unittestapp.data.UserProfile
import com.example.unittestapp.utils.Resource

/**
 * Created by SARATH on 10-04-2021
 */
interface MainRepository {

    fun getUserProfileList() : Resource<List<UserProfile>>

    fun getUserLoginDetail(userName: String) : Resource<UserProfile>

    suspend fun insertUserProfile(userProfile: UserProfile)

 //   suspend fun insertUserData(userName: String,password : String) : Resource<UserProfile>

}