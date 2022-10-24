package com.jge.hingeprofileorderlite.objects

import com.google.gson.annotations.SerializedName

class People(
    @SerializedName("users")
    val listOfPeople: List<Person>)

