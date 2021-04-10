package com.example.unittestapp.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.unittestapp.data.UserProfile
import com.google.common.truth.Truth

import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by SARATH on 10-04-2021
 */
class MainViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup(){
        viewModel = MainViewModel(FakeUserRepository())
    }

    @Test
    fun `valid userProfile fields , returns true`(){
        val value = viewModel.insertUserProfile(UserProfile("sarath","sarath@gmail.com",23,"sarath@321"))

        Truth.assertThat(value)
    }
}