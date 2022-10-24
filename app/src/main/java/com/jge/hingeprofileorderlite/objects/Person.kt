package com.jge.hingeprofileorderlite.objects

import com.google.gson.annotations.SerializedName

class Person(
    val id:Int,
    val name:String,
    val gender:String,
    @SerializedName("photo")
    val photoUrl:String,
    val school:String,
    val about:String,
    val hobbies:List<String> )

