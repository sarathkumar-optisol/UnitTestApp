package com.example.unittestapp.testing


import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * Created by SARATH on 02-04-2021
 */
class RegistrationUtilTest {

    @Test
    fun `empty email and password returns false`() {

        val result = RegistrationUtil.validateUserRegisterInput(
                "",
                ""
        )
        assertThat(result).isFalse()
    }


    @Test
    fun `empty email returns false`(){

        val result = RegistrationUtil.validateUserRegisterInput(
            "",
            "12345678"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`(){

        val result = RegistrationUtil.validateUserRegisterInput(
            "androidtest@gmail.com",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `invalid email and password returns false`(){

        val result = RegistrationUtil.validateUserRegisterInput(
            "AndroidTest@Gmail.com",
            "123456789"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid email and password returns true`(){

        val result = RegistrationUtil.validateUserRegisterInput(
            "androidtest@gmail.com",
            "123456789"
        )
        assertThat(result).isTrue()
    }


}