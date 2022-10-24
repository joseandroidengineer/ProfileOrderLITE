package com.jge.hingeprofileorderlite.repo

import com.jge.hingeprofileorderlite.objects.People
import com.jge.hingeprofileorderlite.objects.Profile
import retrofit2.Response

interface PeopleRepository {
    suspend fun getConfigProfile():Response<Profile>
    suspend fun getPeople(): Response<People>
}