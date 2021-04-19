package com.example.unittestapp.utils

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by SARATH on 16-04-2021
 */
interface DispatcherProvider {
    val main : CoroutineDispatcher
    val io : CoroutineDispatcher
    val default : CoroutineDispatcher
    val unconfined : CoroutineDispatcher
}