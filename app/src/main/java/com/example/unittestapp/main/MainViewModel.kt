package com.example.unittestapp.main

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unittestapp.data.UserProfile
import com.example.unittestapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Created by SARATH on 10-04-2021
 */
class MainViewModel @ViewModelInject constructor(
        private val repository: MainRepository
) : ViewModel() {

    sealed class UserProfileEvent {
        class Success(val result: UserProfile) : UserProfileEvent()
        class Failure(val error: String) : UserProfileEvent()
        object Loading : UserProfileEvent()
        object Empty : UserProfileEvent()
    }

    @ExperimentalCoroutinesApi
    private val _userProfile = MutableStateFlow<UserProfileEvent>(UserProfileEvent.Empty)
    @ExperimentalCoroutinesApi
    val userProfile: StateFlow<UserProfileEvent> = _userProfile

//    fun insertUserProfile(userProfile: UserProfile){
//        viewModelScope.launch(dispatchers.io) {
//            _userProfile.value = UserProfileEvent.Loading
//
//            when (val userProfileResponse = repository.insertUserProfile(userProfile)) {
//                is Resource.Error -> {
//                    _userProfile.value = UserProfileEvent.Failure("Incorrect")
//                    Log.d("MVIEWMODEL", "Incorrect")
//                }
//                is Resource.Success -> {
//                    val data = userProfileResponse.data
//                    Log.d("$data", "ALLUSERDATA")
//                    if (data == null) {
//                        _userProfile.value = UserProfileEvent.Failure("Error")
//                    } else {
//                        _userProfile.value = UserProfileEvent.Success(data)
//                        Log.d("$data", "success")
//                    }
//                }
//            }
//
//        }
//    }


    /**
     * for testing
     */
    fun insertUserProfile(userProfile: UserProfile){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUserProfile(userProfile)
        }
    }
}
