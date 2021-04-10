package com.example.unittestapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by SARATH on 10-04-2021
 */
@Entity(
    tableName ="UserProfileData"
)
data class UserProfile(

    @SerializedName("name")
    var name : String,
    @SerializedName("email")
    var email : String,
    @SerializedName("age")
    var age : Int,
    @SerializedName("password")
    var password : String
){
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int?=null

}