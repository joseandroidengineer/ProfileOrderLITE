package com.jge.hingeprofileorderlite.dependencyinjection

import com.jge.hingeprofileorderlite.network.NetworkUtil
import com.jge.hingeprofileorderlite.network.PeopleApi
import com.jge.hingeprofileorderlite.viewmodels.ViewModelPerson
import com.jge.hingeprofileorderlite.repo.PeopleRepository
import com.jge.hingeprofileorderlite.repo.PeopleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePeopleApi(): PeopleApi {
        return Retrofit.Builder()
            .baseUrl(NetworkUtil.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(PeopleApi::class.java)

    }

    @Provides
    @Singleton
    fun providePeopleRepository(peopleApi: PeopleApi):PeopleRepository{
        return PeopleRepositoryImpl(peopleApi)
    }

    @Provides
    @Singleton
    fun provideViewModelPerson(peopleRepository: PeopleRepositoryImpl): ViewModelPerson {
        return ViewModelPerson(peopleRepository)
    }
}