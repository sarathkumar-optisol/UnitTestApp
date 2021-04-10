package com.example.unittestapp.main

import com.example.unittestapp.data.UserProfile
import com.example.unittestapp.utils.Resource

/**
 * Created by SARATH on 10-04-2021
 */
class FakeUserRepository : MainRepository {

    private val userProfileList = mutableListOf<UserProfile>()

    override fun getUserProfileList(): Resource<List<UserProfile>> {
        return Resource.Success(userProfileList)
    }

    override fun getUserLoginDetail(userName: String): Resource<UserProfile> {
        return Resource.Success(userProfileList[0])
    }

    override suspend fun insertUserProfile(userProfile: UserProfile) {
        userProfileList.add(userProfile)
    }
}