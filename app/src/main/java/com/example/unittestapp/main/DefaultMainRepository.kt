package com.example.unittestapp.main

import com.example.unittestapp.data.UserDao
import com.example.unittestapp.data.UserProfile
import com.example.unittestapp.utils.Resource
import javax.inject.Inject

/**
 * Created by SARATH on 10-04-2021
 */
class DefaultMainRepository @Inject constructor(
        private val userDao: UserDao
) : MainRepository {

    override fun getUserProfileList(): Resource<List<UserProfile>> {
        val userProfileList = userDao.getUserProfileList()
        return Resource.Success(userProfileList)
    }

    override fun getUserLoginDetail(userName: String): Resource<UserProfile> {
        val userLoginDetail = userDao.getUserLoginDetail(userName)
        return Resource.Success(userLoginDetail)
    }

    override suspend fun insertUserProfile(userProfile: UserProfile) {
        userDao.insertUserProfile(userProfile)
    }

    override suspend fun insertUserData(userName: String, password: String) : Resource<UserProfile> {
        val user = userDao.insertUserData(userName,password)
        return Resource.Success(user)

    }
}