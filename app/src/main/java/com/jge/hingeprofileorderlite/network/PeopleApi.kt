package com.jge.hingeprofileorderlite.network

import com.jge.hingeprofileorderlite.objects.People
import com.jge.hingeprofileorderlite.objects.Profile
import retrofit2.Response
import retrofit2.http.GET

interface PeopleApi {
    @GET("config")
    suspend fun getProfileConfig(): Response<Profile>

    @GET("users")
    suspend fun getPeople(): Response<People>
}