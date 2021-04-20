package com.example.unittestapp.ui

import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule


import com.example.unittestapp.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by SARATH on 15-04-2021
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    private lateinit var scenario : ActivityScenario<MainActivity>

    @get:Rule
    var scenarioRule = ActivityTestRule(MainActivity::class.java)


    @Before
    fun setup(){

        scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @After
    fun teardown(){
        scenario.close()
    }

    @Test
    fun wrongPasswordReturnsFalse(){
        val email = "sarath1@gmail.com"
        val password = "sarath321"
        onView(withId(R.id.etUserName)).perform(ViewActions.typeText(email))
        onView(withId(R.id.etPassword)).perform(ViewActions.typeText(password))
        onView(withId(R.id.btnSignIn)).perform(click())

        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btnSignIn)).perform(click())
    }


}
