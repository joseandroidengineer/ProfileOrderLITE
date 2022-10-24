package com.jge.hingeprofileorderlite.repo

import com.jge.hingeprofileorderlite.network.PeopleApi
import com.jge.hingeprofileorderlite.objects.People
import com.jge.hingeprofileorderlite.objects.Profile
import retrofit2.Response
import javax.inject.Inject

class PeopleRepositoryImpl  @Inject constructor(
    private val peopleApi: PeopleApi
):PeopleRepository{

    override suspend fun getConfigProfile(): Response<Profile> {
        return peopleApi.getProfileConfig()
    }

    override suspend fun getPeople(): Response<People> {
        return peopleApi.getPeople()
    }
}