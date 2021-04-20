package com.example.unittestapp.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewModelScope
import androidx.test.core.app.ApplicationProvider
import com.example.unittestapp.data.UserDatabase
import com.example.unittestapp.data.UserProfile
import com.example.unittestapp.utils.DispatcherProvider
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by SARATH on 10-04-2021
 */
@ExperimentalCoroutinesApi

class MainViewModelTest{


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel
    private  var  dispatchers : DispatcherProvider? = null


    @Before
    fun setup(){
        viewModel = MainViewModel(FakeUserRepository(),dispatchers)


    }

    @Test
    fun `valid userProfile fields , returns true`(){
        val value = viewModel.insertUserProfile(UserProfile("sarath","sarath@gmail.com",23,"sarath@321"))
        GlobalScope.launch(Dispatchers.IO) {
            assertThat(value)
        }


    }
}