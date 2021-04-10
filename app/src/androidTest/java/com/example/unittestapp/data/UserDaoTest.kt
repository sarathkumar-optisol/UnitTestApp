package com.example.unittestapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by SARATH on 10-04-2021
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
@ExperimentalCoroutinesApi
class UserDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: UserDatabase
    private lateinit var dao: UserDao

    /**
     * @property Before used to setup the required things for the testcase,
     * here it creates a database for each testcase
     */
    /**
     *@see setup , it setups the database
     */
    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getUserDao()
    }

    /**
     * @see After used after executing all testcases ,
     * here used to clear database
     */
    @After
    fun teardown(){
        database.close()
    }


    @Test
    fun insertUserProfile() = runBlockingTest {
        val userProfile = UserProfile("sarathkumar","sarath1231@gmail.com",23,"sarath123")
        dao.insertUserProfile(userProfile)

        val allUserProfile = dao.getUserProfileList()

        assertThat(allUserProfile).contains(userProfile)
    }

    @Test
    fun getAllUserProfiles() = runBlockingTest {
        val userProfile1 = UserProfile("sarath","sarath1@gmail.com",23,"sarath13")
        val userProfile2 = UserProfile("kumar","sarath123@gmail.com",23,"sarath12")
        val userProfile3 = UserProfile("sarathkumar","sarath1231@gmail.com",23,"sarath1234")

        dao.insertUserProfile(userProfile1)
        dao.insertUserProfile(userProfile2)
        dao.insertUserProfile(userProfile3)

        val allUsers = dao.getUserProfileList()

        assertThat(allUsers).containsExactly(userProfile1,userProfile2,userProfile3)


    }

}