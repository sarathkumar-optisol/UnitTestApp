package com.example.unittestapp.di

import android.content.Context
import androidx.room.Room
import com.example.unittestapp.data.UserDao
import com.example.unittestapp.data.UserDatabase
import com.example.unittestapp.main.DefaultMainRepository
import com.example.unittestapp.main.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by SARATH on 10-04-2021
 */
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): UserDatabase {
        return Room.databaseBuilder(
            appContext,
            UserDatabase::class.java,
            "user_data.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMainRepository(db : UserDao) : MainRepository = DefaultMainRepository(db)

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.getUserDao()
    }

}